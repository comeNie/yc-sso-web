package org.jasig.cas.support.pac4j.plugin.baidu;

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
public class BaiduClient extends BaseOAuth20Client<BaiduProfile> {

    private final static BaiduAttributesDefinition BAIDU_ATTRIBUTES = new BaiduAttributesDefinition();

    @Autowired
	private ILoginSV iLoginSV;

    public BaiduClient(){
    	String callbackurl=ThirdLoginConfigUtil.getCallBackUrl();
    	String appid=ThirdLoginConfigUtil.getBaiduConfig().getAppid();
    	String secret=ThirdLoginConfigUtil.getBaiduConfig().getSecret();
    	setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
    	/*setKey("bt3CnVDOjdbZcYUaX59ZExXc");
        setSecret("l0gM1BvAcY2L7PFr3xuG3rki2mKk305S");
        setCallbackUrl("http://ssotest.yeecloud.com/login");*/
    }

    public BaiduClient(final String key, final String secret){
        //setKey(key);
        //setSecret(secret);
    }

    @Override
    protected BaseClient<OAuthCredentials, BaiduProfile> newClient() {
        BaiduClient newClient = new BaiduClient();
        return newClient;
    }

    @Override
    protected void internalInit() {
        BaiduApi20 api = new BaiduApi20();
        this.service = new BaiduOAuth20ServiceImpl(api, new OAuthConfig(this.key, this.secret, this.callbackUrl,SignatureType.Header, null, null),
                                                                        this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }

    @Override
    protected String getProfileUrl() {
        return "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";
    }

    @Override
    protected BaiduProfile extractUserProfile(String body) {
        BaiduProfile baiduProfile = new BaiduProfile();
        JSONObject json=JSON.parseObject(body);
        System.out.println("json="+json.toString());
        if (null != json) {
            for(final String attribute : BAIDU_ATTRIBUTES.getPrincipalAttributes()){
                baiduProfile.addAttribute(attribute, json.getString(attribute));
            }
            String baiduUid = (String) baiduProfile.getAttributes().get("userid");
			String baiduUsername="BAIDU_"+baiduUid;
            /** 绑定账号到系统 */
			UcMembers ucMembers=new UcMembers();
			ucMembers.setUsersource(ThirdUserConstants.UserSource.BAIDU);
			ucMembers.setThirduid(baiduUid);
			ucMembers.setUsername(baiduUsername);
			String uid=iLoginSV.bindThirdUser(ucMembers);
			baiduProfile.addAttribute("userId", uid);
			baiduProfile.addAttribute("loginName", baiduUsername);
			baiduProfile.addAttribute("username", baiduUsername);
			baiduProfile.addAttribute("domainname", "CN");
			baiduProfile.setId(uid);
        }
        return baiduProfile;
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