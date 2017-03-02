package org.jasig.cas.support.pac4j.plugin.facebook;

import org.jasig.cas.support.pac4j.plugin.common.YcOAuthAttributesDefinitions;
import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.facebook.FacebookProfile;

/**
 * Created by liutong on 2017/3/2.
 */
public class YcFacebookProfile extends FacebookProfile {

    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return YcOAuthAttributesDefinitions.facebookDefinition;
    }

}
