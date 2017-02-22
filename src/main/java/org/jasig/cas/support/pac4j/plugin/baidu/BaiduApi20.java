package org.jasig.cas.support.pac4j.plugin.baidu;

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
public class BaiduApi20 extends DefaultApi20
{
  private static final String AUTHORIZE_URL = "https://openapi.baidu.com/oauth/2.0/authorize?client_id=%s&redirect_uri=%s&response_type=code";

  @Override
  public Verb getAccessTokenVerb()
  {
    return Verb.POST;
  }

  @Override
  public AccessTokenExtractor getAccessTokenExtractor()
  {
    return new JsonTokenExtractor();
  }

  @Override
  public String getAccessTokenEndpoint()
  {
    return "https://openapi.baidu.com/oauth/2.0/token";
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config)
  {
      return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
  }
}