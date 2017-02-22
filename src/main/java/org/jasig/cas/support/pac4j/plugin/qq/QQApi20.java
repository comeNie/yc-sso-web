package org.jasig.cas.support.pac4j.plugin.qq;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

/**
 * 用于定义获取微信返回的CODE与ACCESS_TOKEN
 * @author b2c021
 *
 */
public class QQApi20 extends DefaultApi20 {
	private static final String scope="get_user_info,add_topic,add_one_blog,add_album,upload_pic,list_album,add_share,check_page_fans,add_t,add_pic_t,del_t,get_repost_list,get_info,get_other_info,get_fanslist,get_idollist,add_idol,del_ido,get_tenpay_addr";

    private static final String QQ_AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=%s&scope="+scope;

    @Override
    public AccessTokenExtractor getAccessTokenExtractor()
    {
      return new JsonTokenExtractor();
    }

    @Override
    public Verb getAccessTokenVerb()
    {
      return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://graph.qq.com/oauth2.0/token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        return String.format(QQ_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()),RandomStatusGenerator.getUniqueState());
    }

}