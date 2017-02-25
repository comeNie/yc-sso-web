package org.jasig.cas.support.pac4j.plugin.facebook;

import com.ai.opt.data.api.user.interfaces.ILoginSV;
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jasig.cas.support.pac4j.plugin.common.ThirdLoginConfigUtil;
import org.jasig.cas.support.pac4j.plugin.sinaweibo.SinaWeiboApi20;
import org.jasig.cas.support.pac4j.plugin.sinaweibo.SinaWeiboAttributesDefinition;
import org.jasig.cas.support.pac4j.plugin.sinaweibo.SinaWeiboOAuth20ServiceImpl;
import org.jasig.cas.support.pac4j.plugin.sinaweibo.SinaWeiboProfile;
import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpCommunicationException;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.scribe.model.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 此类用于处理CAS与facebook的OAUTH通信
 * @author b2c021
 *
 */
public class FaceBookClient extends BaseOAuth20Client<FaceBookProfile> {

    private final static FaceBookAttributesDefinition FACEBOOK_ATTRIBUTES = new FaceBookAttributesDefinition();

    @Autowired
	private ILoginSV iLoginSV;

    public FaceBookClient(){
    	String callbackurl=ThirdLoginConfigUtil.getCallBackUrl();
    	String appid=ThirdLoginConfigUtil.getFaceBookConfig().getAppid();
    	String secret=ThirdLoginConfigUtil.getFaceBookConfig().getSecret();
    	setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
    	/*setKey("606577360");
        setSecret("2547eb81b19310ffbda5f83043817136");
        setCallbackUrl("http://ssotest.yeecloud.com/login");*/
    }

    public FaceBookClient(final String key, final String secret){
        //setKey(key);
        //setSecret(secret);
    }

    @Override
    protected BaseClient<OAuthCredentials, FaceBookProfile> newClient() {
        FaceBookClient newClient = new FaceBookClient();
        return newClient;
    }

    @Override
    protected void internalInit() {
        FaceBookApi20 api = new FaceBookApi20();
        this.service = new FaceBookOAuth20ServiceImpl(api
                , new OAuthConfig(this.key, this.secret, this.callbackUrl,SignatureType.Header, null, null)
                ,this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }

    @Override
    protected String getProfileUrl() {
        return "https://graph.facebook.com/v2.8/me";
    }

    @Override
    protected FaceBookProfile extractUserProfile(String body) {
        FaceBookProfile faceBookProfile = new FaceBookProfile();
        JSONObject json=JSON.parseObject(body);
        System.out.println("json="+json.toString());
        if (null != json) {
            for(final String attribute : FACEBOOK_ATTRIBUTES.getPrincipalAttributes()){
                faceBookProfile.addAttribute(attribute, json.getString(attribute));
            }
            String thirdUid = (String) faceBookProfile.getAttributes().get("id");
			String thirdName="FACEBOOK_"+thirdUid;
            /** 绑定账号到系统 */
			UcMembers ucMembers=new UcMembers();
			ucMembers.setUsersource(ThirdUserConstants.UserSource.SINA);
			ucMembers.setThirduid(thirdUid);
			ucMembers.setUsername(thirdName);
			String uid=iLoginSV.bindThirdUser(ucMembers);
            faceBookProfile.addAttribute("userId", uid);
            faceBookProfile.addAttribute("loginName", thirdName);
            faceBookProfile.addAttribute("username", thirdName);
            faceBookProfile.addAttribute("domainname", "CN");
            faceBookProfile.setId(uid);
        }
        return faceBookProfile;
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