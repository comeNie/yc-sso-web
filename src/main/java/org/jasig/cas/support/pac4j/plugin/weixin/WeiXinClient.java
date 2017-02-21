package org.jasig.cas.support.pac4j.plugin.weixin;

import org.jasig.cas.support.pac4j.plugin.common.ThirdLoginConfigUtil;
import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpCommunicationException;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.pac4j.oauth.profile.JsonHelper;
import org.scribe.model.OAuthConfig;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.opt.data.api.user.interfaces.ILoginSV;
import com.ai.opt.data.api.user.param.ThirdUserQueryRequest;
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 此类用于处理CAS与微信的OAUTH通信
 * @author b2c021
 *
 */
public class WeiXinClient extends BaseOAuth20Client<WeiXinProfile> {

    private final static WeiXinAttributesDefinition WEI_XIN_ATTRIBUTES = new WeiXinAttributesDefinition();

    @Autowired
	private ILoginSV iLoginSV;

    public WeiXinClient(){
    	String callbackurl=ThirdLoginConfigUtil.getCallBackUrl();
    	String appid=ThirdLoginConfigUtil.getWeixinConfig().getAppid();
    	String secret=ThirdLoginConfigUtil.getWeixinConfig().getSecret();
    	setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
    	/*setKey("wxe5a67ee0fb5e181f");
        setSecret("25bd1d5a4ac75618a7ed2cbd46417800");
        setCallbackUrl("http://ssotest.yeecloud.com/login");*/
    }

    public WeiXinClient(final String key, final String secret){
//        setKey(key);
//        setSecret(secret);
    }

    @Override
    protected BaseClient<OAuthCredentials, WeiXinProfile> newClient() {
        WeiXinClient newClient = new WeiXinClient();
        return newClient;
    }

    @Override
    protected void internalInit() {
        WeiXinApi20 api = new WeiXinApi20();
        this.service = new WeiXinOAuth20ServiceImpl(api, new OAuthConfig(this.key, this.secret, this.callbackUrl,SignatureType.Header, null, null),
                                                                        this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }

    @Override
    protected String getProfileUrl() {
        // eg.google2Client:return "https://www.googleapis.com/oauth2/v2/userinfo";
        return "https://api.weixin.qq.com/sns/userinfo";
    }

    @Override
    protected WeiXinProfile extractUserProfile(String body) {
        WeiXinProfile weiXinProfile = new WeiXinProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (null != json) {
            for(final String attribute : WEI_XIN_ATTRIBUTES.getPrincipalAttributes()){
                weiXinProfile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
            String openId = (String) weiXinProfile.getAttributes().get("openid");
            String weixinUsername="WEIXIN_"+openId;
			/** 绑定账号到系统 */
			UcMembers ucMembers=new UcMembers();
			ucMembers.setUsersource(ThirdUserConstants.UserSource.WEIXIN);
			ucMembers.setThirduid(openId);
			ucMembers.setUsername(weixinUsername);
			String uid=iLoginSV.bindThirdUser(ucMembers);
			weiXinProfile.addAttribute("userId", uid);
			weiXinProfile.addAttribute("loginName", weixinUsername);
			weiXinProfile.addAttribute("username", weixinUsername);
			weiXinProfile.addAttribute("domainname", "CN");
			weiXinProfile.setId(uid);
        }
        return weiXinProfile;
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