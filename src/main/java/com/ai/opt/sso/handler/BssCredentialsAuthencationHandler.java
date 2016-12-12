package com.ai.opt.sso.handler;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.login.CredentialException;
import javax.security.auth.login.LoginException;
import javax.validation.constraints.NotNull;

import org.jasig.cas.Message;
import org.jasig.cas.authentication.BasicCredentialMetaData;
import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.handler.NoOpPrincipalNameTransformer;
import org.jasig.cas.authentication.handler.PasswordEncoder;
import org.jasig.cas.authentication.handler.PlainTextPasswordEncoder;
import org.jasig.cas.authentication.handler.PrincipalNameTransformer;
import org.jasig.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.authentication.support.PasswordPolicyConfiguration;
import org.springframework.util.StringUtils;

import com.ai.opt.base.exception.RPCSystemException;
import com.ai.opt.data.api.user.param.UserLoginResponse;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sso.exception.CaptchaErrorException;
import com.ai.opt.sso.exception.CaptchaIsNullException;
import com.ai.opt.sso.exception.CaptchaOutTimeException;
import com.ai.opt.sso.exception.EmailNotExistException;
import com.ai.opt.sso.exception.PasswordErrorException;
import com.ai.opt.sso.exception.PasswordIsNullException;
import com.ai.opt.sso.exception.PhoneNotExistException;
import com.ai.opt.sso.exception.SystemErrorException;
import com.ai.opt.sso.exception.UsernameIsNullException;
import com.ai.opt.sso.principal.BssCredentials;
import com.ai.opt.sso.service.LoadAccountService;
import com.ai.opt.sso.util.RegexUtils;
import com.ai.opt.uac.web.constants.Constants;
import com.ai.opt.uac.web.constants.Constants.Register;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.util.StringUtil;

public final class BssCredentialsAuthencationHandler extends AbstractPreAndPostProcessingAuthenticationHandler{

	
	
	
	@Resource
	private LoadAccountService loadAccountService;
	@NotNull
	private PasswordEncoder passwordEncoder;

	@NotNull
	private PrincipalNameTransformer principalNameTransformer;
	private PasswordPolicyConfiguration passwordPolicyConfiguration;
	
	public LoadAccountService getLoadAccountService() {
		return loadAccountService;
	}
	public void setLoadAccountService(LoadAccountService loadAccountService) {
		this.loadAccountService = loadAccountService;
	}
	public BssCredentialsAuthencationHandler(){
		this.passwordEncoder = new PlainTextPasswordEncoder();
		this.principalNameTransformer = new NoOpPrincipalNameTransformer();
	}
	@Override
	public boolean supports(Credential credentials) {
		return credentials!=null&&(BssCredentials.class.isAssignableFrom(credentials.getClass()));
	}

	@Override
	protected HandlerResult doAuthentication(final Credential credentials)
			throws GeneralSecurityException, PreventedException {
		
		//获取配置中心登录失败有效时间
		String errorNumTimeOut="";
		try {
			errorNumTimeOut = CCSClientFactory.getDefaultConfigClient().get("/errorNumTimeOut");
		} catch (ConfigException e) {
			logger.error("从配置中心获取登录失败次数失败");
		}
		
		Integer timoutNum = null;
		if(StringUtils.hasText(errorNumTimeOut)){
			timoutNum = Integer.valueOf(errorNumTimeOut);
		}
		//获取配置中心登录失败次数
		String errorNumConfig="";
		try {
			errorNumConfig = CCSClientFactory.getDefaultConfigClient().get("/errorNum");
		} catch (ConfigException e) {
			logger.error("从配置中心获取登录失败次数失败");
		}
		
		Integer errorNumber = null;
		if(StringUtils.hasText(errorNumConfig)){
			errorNumber = Integer.valueOf(errorNumConfig);
		}	
		logger.debug("开始认证用户凭证credentials");
		if(credentials == null){
			logger.info("用户凭证credentials为空");
			throw new LoginException("Credentials is null");
		}
		BssCredentials bssCredentials = (BssCredentials) credentials;
		final String username = bssCredentials.getUsername();
		final String pwdFromPage = bssCredentials.getPassword();

	    final String sessionId = bssCredentials.getSessionId();
	    
	    ICacheClient jedis = MCSClientFactory.getCacheClient("com.ai.opt.uac.cache.logincount.cache");

	    String requestIp =jedis.get(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+bssCredentials.getId());  
	    Integer errorNum =0;
	   if( (boolean)jedis.exists(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp)){
		   errorNum =Integer.valueOf( jedis.get(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp));
	   }
	 
	    

	    boolean captchaShow = true;
	    if(errorNum<=errorNumber){
	    	captchaShow = false;
	    }
		//用户名非空校验
		if(!StringUtils.hasText(username)){
			logger.error("请输入手机号码或邮箱地址");
			throw new UsernameIsNullException();
		}
		//密码非空校验
		if(!StringUtils.hasText(pwdFromPage)){
			logger.error("密码为空！");
			throw new PasswordIsNullException();
		}
		
		if( captchaShow==true){
			final String captchaCode = bssCredentials.getCaptchaCode().toLowerCase();
		
			 // 验证码非空校验
	        if (!StringUtils.hasText(captchaCode)) {
	            logger.error("请输入验证码");
	            throw new CaptchaIsNullException();
	        }
	
	        ICacheClient iCacheClient = MCSClientFactory.getCacheClient(Register.CACHE_NAMESPACE);
	        // 生成的校验码
	        String cookieCaptcha = iCacheClient.get(Register.CACHE_KEY_VERIFY_PICTURE + sessionId)
	                .toLowerCase();
	        // 校验图片是否失效
	        if (StringUtil.isBlank(cookieCaptcha)) {
	            throw new CaptchaOutTimeException();
	        }
	        // 校验验证码
	        
	        logger.error("cookieCaptcha=="+cookieCaptcha);
	        logger.error("bssCredentials=="+bssCredentials.getCaptchaCode().toLowerCase());
	        System.out.println("cookieCaptcha=="+cookieCaptcha);
	        System.out.println("bssCredentials=="+bssCredentials.getCaptchaCode().toLowerCase());
	        if (!cookieCaptcha.equals(bssCredentials.getCaptchaCode().toLowerCase())) {
	            throw new CaptchaErrorException();
	        }
		}
		
		UserLoginResponse user = null;
		try {
			long dubboStart=System.currentTimeMillis();
			logger.error("====开始执行doAuthentication中的loadAccountService.loadAccount服务，当前时间戳："+dubboStart);
			user = loadAccountService.loadAccount(bssCredentials);
			long dubboEnd=System.currentTimeMillis();
			logger.error("====完成执行doAuthentication中的loadAccountService.loadAccount服务，当前时间戳："+dubboEnd+",用时:"+(dubboEnd-dubboStart)+"毫秒");
	    	
//			 if(SSOConstants.ACCOUNT_LOGIN_FLAG.equals(user.getLoginFlag())){
//					//账号不允许登录
//					logger.error("账号不允许登录");
//					throw new AccountNotAllowLoginException();
//				}
			if(user == null||StringUtil.isBlank(user.getUserId())){
				if(RegexUtils.checkIsPhone(bssCredentials.getUsername())){
					logger.error("手机号码未注册");
					throw new PhoneNotExistException();
				}
				else if(RegexUtils.checkIsEmail(bssCredentials.getUsername())){
					logger.error("邮箱未绑定");
					throw new EmailNotExistException();
				}/*else if(SSOConstants.ACCOUNT_LOGIN_FLAG.equals(user.getLoginFlag())){
					//账号不允许登录
					logger.error("账号不允许登录");
					throw new AccountNotAllowLoginException();
				}*/
				/*else if(SSOConstants.ACCOUNT_DEL_FLAG.equals(user.getDelFlag())){
					//账号已删除
					logger.error("账号已删除");
					throw new AccountNameNotExistException();
				}*/
/*				else{
					logger.error("账号未注册，密码错误");
					
					ICacheClient jedis = MCSClientFactory.getCacheClient("com.ai.opt.uac.cache.logincount.cache");
					 String requestIp =jedis.get(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+bssCredentials.getId());  
					if(!jedis.exists(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp)){
						jedis.set(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp, 0 + "");  	
					}
					jedis.incrBy(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp, 1);
					logger.debug("【"+user.getLoginName()+"】 登录失败，目前失败次数为："+jedis.get(Constants.LOGIN_LOST_COUNT_KEY+":"+user.getLoginName()));
				
					throw new AccountNameNotExistException();
				}*/
			}
			
			
			String dbPwd=user.getLoginPassword();
			String salt=user.getSalt();
			logger.info("【dbPwd】="+dbPwd);
			logger.info("【pwdFromPage】="+pwdFromPage);
			String encryPwdFromPage=Md5Utils.md5(Md5Utils.md5(pwdFromPage).concat(salt));
			if(!encryPwdFromPage.equals(dbPwd)){
				//密码不对
				logger.error("密码错误！");


				
				if(!jedis.exists(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp)){

					jedis.setex(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp,timoutNum.intValue() ,0 + "");  	
				}
				jedis.incrBy(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp, 1);
				logger.debug("【"+user.getLoginName()+"】 登录失败，目前失败次数为："+jedis.get(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp));
				throw new PasswordErrorException();
			}
//			if(!SSOConstants.ACCOUNT_ACITVE_STATE.equals(user.getState())){
//				//密码不对
//				throw new CredentialException("账号状态异常");
//			}
//			Date currentDate=new Date();
//			Date acitveDate=user.getEffectiveDate();
//			Date inactiveDate=user.getExpiryDate();
//			if(acitveDate!=null&&currentDate.before(acitveDate)){
//				throw new CredentialException("账号未生效");
//			}
//			if(inactiveDate!=null&&inactiveDate.before(currentDate)){
//				throw new CredentialException("账号已失效");
//			}
			
			//BeanUtils.copyProperties(bssCredentials, user);
			bssCredentials.setTenantId(user.getTenantId());
			bssCredentials.setUserId(user.getUserId());
			bssCredentials.setMobile(user.getMobile());
			bssCredentials.setEmail(user.getEmail());
			bssCredentials.setLoginName(user.getLoginName());
			bssCredentials.setDomainname(user.getDomainname());
		}
		/*catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("从user拷贝属性到bssCredentials出错",e);
			throw new SystemErrorException();
		}*/
		catch (RPCSystemException e) {
			logger.error("调用查询账户服务（Dubbo）失败",e);
			throw new CredentialException("系统错误");
		}/*catch(AccountNotAllowLoginException e){
			logger.error("该用户被冻结",e);
			throw new AccountNotAllowLoginException();
		}*/
		catch (Exception e) {
			logger.error("系统异常",e);
		
			if(!jedis.exists(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp)){
				jedis.setex(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp,timoutNum, 0 + "");  	
			}
			jedis.incrBy(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp, 1);
			logger.debug("【"+user.getLoginName()+"】 登录失败，目前失败次数为："+jedis.get(Constants.LOGIN_LOST_COUNT_KEY+":"+user.getLoginName()));
			throw new SystemErrorException();
		}
		logger.info("用户 [" + username + "] 认证成功。");

		jedis.del(CustomLoginFlowUrlHandler.CAS_REDIS_PREFIX+requestIp);  	
        return creatHandlerResult(bssCredentials, new SimplePrincipal(username),null);
	}

	private HandlerResult creatHandlerResult(BssCredentials bssCredentials,
			SimplePrincipal simplePrincipal, List<Message> warnings) {
		return new HandlerResult(this, new BasicCredentialMetaData(bssCredentials), simplePrincipal, warnings);
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public PrincipalNameTransformer getPrincipalNameTransformer() {
		return principalNameTransformer;
	}

	public void setPrincipalNameTransformer(
			PrincipalNameTransformer principalNameTransformer) {
		this.principalNameTransformer = principalNameTransformer;
	}

	public PasswordPolicyConfiguration getPasswordPolicyConfiguration() {
		return passwordPolicyConfiguration;
	}

	public void setPasswordPolicyConfiguration(
			PasswordPolicyConfiguration passwordPolicyConfiguration) {
		this.passwordPolicyConfiguration = passwordPolicyConfiguration;
	}

	   public static class Md5Utils {
   	    /**
   		 * 使用md5的算法进行加密
   		 */
   		public static String md5(String plainText) {
   			byte[] secretBytes = null;
   			try {
   				secretBytes = MessageDigest.getInstance("md5").digest(
   						plainText.getBytes());
   			} catch (NoSuchAlgorithmException e) {
   				throw new RuntimeException("没有md5这个算法！");
   			}
   			String md5code = new BigInteger(1, secretBytes).toString(16);
   			for (int i = 0; i < 32 - md5code.length(); i++) {
   				md5code = "0" + md5code;
   			}
   			return md5code;
   		}
   	}
	
}
