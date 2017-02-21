package org.jasig.cas.support.pac4j.plugin.sinaweibo;

import org.jasig.cas.support.pac4j.plugin.common.ThirdLoginConfigUtil;
import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpCommunicationException;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.scribe.model.OAuthConfig;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.data.api.user.interfaces.ILoginSV;
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 此类用于处理CAS与新浪微博的OAUTH通信
 * @author b2c021
 *
 */
public class SinaWeiboClient extends BaseOAuth20Client<SinaWeiboProfile> {

    private final static SinaWeiboAttributesDefinition SINA_WEIBO_ATTRIBUTES = new SinaWeiboAttributesDefinition();

    @Autowired
	private ILoginSV iLoginSV;

    public SinaWeiboClient(){
    	String callbackurl=ThirdLoginConfigUtil.getCallBackUrl();
    	String appid=ThirdLoginConfigUtil.getSinaWeiboConfig().getAppid();
    	String secret=ThirdLoginConfigUtil.getSinaWeiboConfig().getSecret();
    	setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
    	/*setKey("606577360");
        setSecret("2547eb81b19310ffbda5f83043817136");
        setCallbackUrl("http://ssotest.yeecloud.com/login");*/
    }

    public SinaWeiboClient(final String key, final String secret){
        //setKey(key);
        //setSecret(secret);
    }

    @Override
    protected BaseClient<OAuthCredentials, SinaWeiboProfile> newClient() {
        SinaWeiboClient newClient = new SinaWeiboClient();
        return newClient;
    }

    @Override
    protected void internalInit() {
        SinaWeiboApi20 api = new SinaWeiboApi20();
        this.service = new SinaWeiboOAuth20ServiceImpl(api, new OAuthConfig(this.key, this.secret, this.callbackUrl,SignatureType.Header, null, null),
                                                                        this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }

    @Override
    protected String getProfileUrl() {
        return "https://api.weibo.com/2/users/show.json";
    }

    @Override
    protected SinaWeiboProfile extractUserProfile(String body) {
        SinaWeiboProfile sinaWeiboProfile = new SinaWeiboProfile();
        JSONObject json=JSON.parseObject(body);
        System.out.println("json="+json.toString());
        if (null != json) {
            for(final String attribute : SINA_WEIBO_ATTRIBUTES.getPrincipalAttributes()){
                sinaWeiboProfile.addAttribute(attribute, json.getString(attribute));
            }
            String sinaWeiboId = (String) sinaWeiboProfile.getAttributes().get("id");
			String sinaWeiboUsername="SINAWEIBO_"+sinaWeiboId;
            /** 绑定账号到系统 */
			UcMembers ucMembers=new UcMembers();
			ucMembers.setUsersource(ThirdUserConstants.UserSource.SINA);
			ucMembers.setThirduid(sinaWeiboId);
			ucMembers.setUsername(sinaWeiboUsername);
			String uid=iLoginSV.bindThirdUser(ucMembers);
			sinaWeiboProfile.addAttribute("userId", uid);
			sinaWeiboProfile.addAttribute("loginName", sinaWeiboUsername);
			sinaWeiboProfile.addAttribute("username", sinaWeiboUsername);
			sinaWeiboProfile.addAttribute("domainname", "CN");
			sinaWeiboProfile.setId(uid);
        }
        return sinaWeiboProfile;
    }

    /**
     * 需求state元素
     */
    @Override
    protected boolean requiresStateParameter() {
        return false;
    }

    @Override // Cancelled 取消
    protected boolean hasBeenCancelled(WebContext context) {
        return false;
    }

    @Override
    protected String sendRequestForData(final Token accessToken, final String dataUrl) {
        logger.debug("accessToken : {} / dataUrl : {}", accessToken, dataUrl);
        final long t0 = System.currentTimeMillis();
        final ProxyOAuthRequest request = createProxyRequest(dataUrl);
        this.service.signRequest(accessToken, request);

        final Response response = request.send();
        final int code = response.getCode();
        final String body = response.getBody();
        final long t1 = System.currentTimeMillis();
        logger.debug("Request took : " + (t1 - t0) + " ms for : " + dataUrl);
        logger.debug("response code : {} / response body : {}", code, body);
        if (code != 200) {
            logger.error("Failed to get data, code : " + code + " / body : " + body);
            throw new HttpCommunicationException(code, body);
        }
        return body;
    }

}