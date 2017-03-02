package org.jasig.cas.support.pac4j.plugin.common;

import org.jasig.cas.support.pac4j.plugin.facebook.YcFacebookAttributesDefinition;
import org.jasig.cas.support.pac4j.plugin.twitter.YcTwitterAttributesDefinition;
import org.pac4j.core.profile.AttributesDefinition;

/**
 * Created by liutong on 2017/3/2.
 */
public final class YcOAuthAttributesDefinitions {
    public final static AttributesDefinition facebookDefinition = new YcFacebookAttributesDefinition();
    public final static AttributesDefinition twitterDefinition = new YcTwitterAttributesDefinition();
}
