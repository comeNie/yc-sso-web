package org.jasig.cas.support.pac4j.plugin.qq;

import org.jasig.cas.support.pac4j.plugin.common.ThirdLoginConfigUtil;
import org.jasig.cas.support.pac4j.plugin.qq.openid.OpenID;
import org.jasig.cas.support.pac4j.plugin.qq.openid.QQConnectException;
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
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * 此类用于处理CAS与微信的OAUTH通信
 * @author b2c021
 *
 */
public class QQClient extends BaseOAuth20Client<QQProfile> {

    private final static QQAttributesDefinition QQ_ATTRIBUTES = new QQAttributesDefinition();

    @Autowired
	private ILoginSV iLoginSV;

    public QQClient(){
    	String callbackurl=ThirdLoginConfigUtil.getCallBackUrl();
    	String appid=ThirdLoginConfigUtil.getQQConfig().getAppid();
    	String secret=ThirdLoginConfigUtil.getQQConfig().getSecret();
    	setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
    	/*setKey("101378897");
        setSecret("1dc5a602743f62a60ddf66b1cd2e20b7");
        setCallbackUrl("http://ssotest.yeecloud.com/login");*/
        /*setKey("101383604");
        setSecret("1dc5a602743f62a60ddf66b1cd2e20b7");
        setCallbackUrl("http://waptest.yeecloud.com/login");*/
    }

    public QQClient(final String key, final String secret){
//        setKey(key);
//        setSecret(secret);
    }

    @Override
    protected BaseClient<OAuthCredentials, QQProfile> newClient() {
        QQClient newClient = new QQClient();
        return newClient;
    }

    @Override
    protected void internalInit() {
        QQApi20 api = new QQApi20();
        this.service = new QQOAuth20ServiceImpl(api, new OAuthConfig(this.key, this.secret, this.callbackUrl,SignatureType.Header, null, null),
                                                                        this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }

    @Override
    protected String getProfileUrl() {
        // eg.google2Client:return "https://www.googleapis.com/oauth2/v2/userinfo";
        return "https://graph.qq.com/user/get_user_info";
    }

    @Override
    protected QQProfile extractUserProfile(String body) {
        QQProfile qqProfile = new QQProfile();
        JSONObject json=JSON.parseObject(body);
        if (null != json) {
            for(final String attribute : QQ_ATTRIBUTES.getPrincipalAttributes()){
                qqProfile.addAttribute(attribute, json.getString(attribute));
            }
            String openId = (String) qqProfile.getAttributes().get("openid");
            String qqUsername="QQ_"+openId;
			/** 绑定账号到系统 */
			UcMembers ucMembers=new UcMembers();
			ucMembers.setUsersource(ThirdUserConstants.UserSource.QQ);
			ucMembers.setThirduid(openId);
			ucMembers.setUsername(qqUsername);
			String uid=iLoginSV.bindThirdUser(ucMembers);
			qqProfile.addAttribute("userId", uid);
			qqProfile.addAttribute("loginName", qqUsername);
			qqProfile.addAttribute("username", qqUsername);
			qqProfile.addAttribute("domainname", "CN");
			qqProfile.setId(uid);
        }
        return qqProfile;
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
        //=======开始  QQ的openId需要单独获取===============
        String openIdUrl="https://graph.qq.com/oauth2.0/me";
        OpenID openIDObj = new OpenID(accessToken.getToken(),openIdUrl);
		String openID="";
		try {
			openID = openIDObj.getUserOpenID();
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
        request.addQuerystringParameter("openid", openID);
        //=======结束  QQ的openId需要单独获取===============
        
        
        
        final Response response = request.send();
        final int code = response.getCode();
        //======开始 将openid 手动加入responsebody，便于extractUserProfile提取openid======
        String bodyRaw = response.getBody();
        JSONObject jsonBody=JSONObject.parseObject(bodyRaw);
        jsonBody.put("openid", openID);
        //======结束 将openid 手动加入responsebody，便于extractUserProfile提取openid======
        final String body = JSON.toJSONString(jsonBody);
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