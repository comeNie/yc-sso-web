package org.jasig.cas.support.pac4j.plugin.facebook;

import com.ai.opt.data.api.user.interfaces.ILoginSV;
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.fasterxml.jackson.databind.JsonNode;
import org.jasig.cas.support.pac4j.plugin.common.ThirdLoginConfigUtil;
import org.jasig.cas.support.pac4j.plugin.common.YcOAuthAttributesDefinitions;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.OAuthAttributesDefinitions;
import org.pac4j.oauth.profile.facebook.FacebookProfile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liutong on 2017/2/27.
 */
public class YcFaceBookClient extends FacebookClient {
    @Autowired
    private ILoginSV iLoginSV;

    public YcFaceBookClient() {
        String callbackurl= ThirdLoginConfigUtil.getCallBackUrl();
        String appid=ThirdLoginConfigUtil.getFaceBookConfig().getAppid();
        String secret=ThirdLoginConfigUtil.getFaceBookConfig().getSecret();
        setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
        setFields("id,name,first_name,middle_name,last_name,gender,email");//自定义字段
        setScope("");//自定义权限
    }

    public YcFaceBookClient(final String key, final String secret) {
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected YcFaceBookClient newClient() {
        final YcFaceBookClient newClient = new YcFaceBookClient();
        newClient.setScope(this.scope);
        newClient.setFields(this.fields);
        newClient.setLimit(this.limit);
        return newClient;
    }

    @Override
    protected YcFacebookProfile extractUserProfile(final String body) {
        final YcFacebookProfile profile = new YcFacebookProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            for (final String attribute : YcOAuthAttributesDefinitions.facebookDefinition.getAllAttributes()) {
                profile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
            String thirdUid = profile.getId();
            String thirdName="FB_"+thirdUid;
            /** 绑定账号到系统 */
            UcMembers ucMembers=new UcMembers();
            ucMembers.setUsersource(ThirdUserConstants.UserSource.FACEBOOK);
            ucMembers.setThirduid(thirdUid);
            ucMembers.setUsername(thirdName);
            String uid=iLoginSV.bindThirdUser(ucMembers);
            profile.addAttribute("userId", uid);
            profile.addAttribute("loginName", thirdName);
            profile.addAttribute("username", thirdName);
            profile.addAttribute("domainname", "CN");
            profile.setId(uid);
        }
        return profile;
    }

}
