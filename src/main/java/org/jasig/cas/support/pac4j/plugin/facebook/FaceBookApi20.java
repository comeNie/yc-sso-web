package org.jasig.cas.support.pac4j.plugin.facebook;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

/**
 * 用于定义获取facebook返回的CODE与ACCESS_TOKEN
 * @author liutong
 *
 */
public class FaceBookApi20 extends DefaultApi20
{
  private static final String AUTHORIZE_URL = "https://www.facebook.com/v2.8/dialog/oauth?client_id=%s&redirect_uri=%s&response_type=code";
  private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";

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
    return "https://graph.facebook.com/v2.8/oauth/access_token?client_id=%s&client_secret=%s&code=%s&redirect_uri=%s";
  }

  @Override
  public String getAuthorizationUrl(OAuthConfig config)
  {
    // Append scope if present
    if (config.hasScope())
    {
      return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
    }
    else
    {
      return String.format(AUTHORIZE_URL, config.getApiKey(), OAuthEncoder.encode(config.getCallback()));
    }
  }
}