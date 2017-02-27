package org.jasig.cas.support.pac4j.plugin.facebook;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.*;
import org.scribe.oauth.ProxyOAuth20ServiceImpl;
import org.scribe.utils.OAuthEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于添加获取ACCESS_TOKEN与用户信息添加参数并请求facebook
 * @author liutong
 *
 */
public class FaceBookOAuth20ServiceImpl extends ProxyOAuth20ServiceImpl {

    public FaceBookOAuth20ServiceImpl(DefaultApi20 api, OAuthConfig config, int connectTimeout, int readTimeout, String proxyHost, int proxyPort) {
        super(api, config, connectTimeout, readTimeout, proxyHost, proxyPort);
    }

    /**
     * 获取account_token的http请求参数添加
     */
    @Override
    public Token getAccessToken(final Token requestToken, final Verifier verifier) {
    	String urlAccessTokenEndpoint=String.format(
    			this.api.getAccessTokenEndpoint(), 
    			this.config.getApiKey(),
    			this.config.getApiSecret(),
    			verifier.getValue(),
    			OAuthEncoder.encode(this.config.getCallback()));
        final OAuthRequest request = new ProxyOAuthRequest(
                this.api.getAccessTokenVerb(),urlAccessTokenEndpoint,
                this.connectTimeout,this.readTimeout, this.proxyHost, this.proxyPort);
        //
        /*request.addBodyParameter("client_id", this.config.getApiKey());
        request.addBodyParameter("client_secret", this.config.getApiSecret());
        request.addBodyParameter(OAuthConstants.CODE, verifier.getValue());
        request.addBodyParameter(OAuthConstants.REDIRECT_URI, this.config.getCallback());
        request.addBodyParameter("grant_type", "authorization_code");*/
        final Response response = request.send();
        return this.api.getAccessTokenExtractor().extract(response.getBody());
    }

    @Override
    public void signRequest(final Token accessToken, final OAuthRequest request) {
        request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
//        String response = accessToken.getRawResponse();
    }
}
