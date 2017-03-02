package org.jasig.cas.support.pac4j.plugin.twitter;

import org.jasig.cas.support.pac4j.plugin.common.YcOAuthAttributesDefinitions;
import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.twitter.TwitterProfile;

/**
 * Created by liutong on 2017/3/2.
 */
public class YcTwitterProfile extends TwitterProfile {
    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return YcOAuthAttributesDefinitions.twitterDefinition;
    }

}
