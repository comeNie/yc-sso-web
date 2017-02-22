package org.jasig.cas.support.pac4j.plugin.qq;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.ProxyOAuth20ServiceImpl;

import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 用于添加获取ACCESS_TOKEN与用户信息添加参数并请求QQ
 * @author b2c021
 *
 */
public class QQOAuth20ServiceImpl extends ProxyOAuth20ServiceImpl {

    public QQOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config, int connectTimeout, int readTimeout, String proxyHost, int proxyPort) {
        super(api, config, connectTimeout, readTimeout, proxyHost, proxyPort);
    }

    /**
     * 获取account_token的http请求参数添加
     */
    @Override
    public Token getAccessToken(final Token requestToken, final Verifier verifier) {
        final OAuthRequest request = new ProxyOAuthRequest(this.api.getAccessTokenVerb(),
                                                           this.api.getAccessTokenEndpoint(), this.connectTimeout,
                                                           this.readTimeout, this.proxyHost, this.proxyPort);
        request.addBodyParameter("client_id", this.config.getApiKey());
        request.addBodyParameter("client_secret", this.config.getApiSecret());
        request.addBodyParameter(OAuthConstants.CODE, verifier.getValue());
        request.addBodyParameter(OAuthConstants.REDIRECT_URI, this.config.getCallback());
        request.addBodyParameter("grant_type", "authorization_code");
        final Response response = request.send();
        //==开始转换qq的返回格式
        //由于QQ的返回信息response.getBody()不是json格式，此处需要转换下
        //QQ的response.getBody()返回示例：access_token=E0EBCC06EEDB8F0E3908B0C198AA11BE&expires_in=7776000&refresh_token=00203DE1DBFE9C9D45EEBAE6C0C5861B
        //需要转换为如下格式：{"access_token":"E0EBCC06EEDB8F0E3908B0C198AA11BE","expires_in":"7776000","refresh_token":"refresh_token"}
        String body=response.getBody();
        JSONObject jsonBody=new JSONObject();
        if(!StringUtil.isBlank(body)){
        	String[] bodyArr=body.split("&");
        	for(String keyvalue : bodyArr){
        		String[] keyValuePair=keyvalue.split("=");
        		String key=keyValuePair[0];
        		String value=keyValuePair[1];
        		jsonBody.put(key, value);
        	}
        }
        //==结束转换qq的返回格式
        return this.api.getAccessTokenExtractor().extract(JSON.toJSONString(jsonBody));
    }

    @Override
    public void signRequest(final Token accessToken, final OAuthRequest request) {
        request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
        request.addQuerystringParameter("oauth_consumer_key", this.config.getApiKey());
    }
}
