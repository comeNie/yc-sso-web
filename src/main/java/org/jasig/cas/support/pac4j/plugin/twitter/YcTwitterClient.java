package org.jasig.cas.support.pac4j.plugin.twitter;

import com.ai.opt.data.api.user.interfaces.ILoginSV;
import com.ai.opt.data.constants.ThirdUserConstants;
import com.ai.opt.data.dao.mapper.bo.UcMembers;
import com.fasterxml.jackson.databind.JsonNode;
import org.jasig.cas.support.pac4j.plugin.common.ThirdLoginConfigUtil;
import org.pac4j.oauth.client.TwitterClient;
import org.pac4j.oauth.profile.JsonHelper;
import org.pac4j.oauth.profile.OAuthAttributesDefinitions;
import org.pac4j.oauth.profile.twitter.TwitterProfile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liutong on 2017/2/27.
 */
public class YcTwitterClient extends TwitterClient {
    @Autowired
    private ILoginSV iLoginSV;

    public YcTwitterClient() {
        String callbackurl= ThirdLoginConfigUtil.getCallBackUrl();
        String appid=ThirdLoginConfigUtil.getTwitterConfig().getAppid();
        String secret=ThirdLoginConfigUtil.getTwitterConfig().getSecret();
        setKey(appid);
        setSecret(secret);
        setCallbackUrl(callbackurl);
    }

    @Override
    protected YcTwitterClient newClient() {
        return new YcTwitterClient();
    }

    @Override
    protected TwitterProfile extractUserProfile(final String body) {
        final TwitterProfile profile = new TwitterProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (json != null) {
            profile.setId(JsonHelper.get(json, "id"));
            for (final String attribute : OAuthAttributesDefinitions.twitterDefinition.getAllAttributes()) {
                profile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
            String thirdUid = profile.getId();
            String thirdName="TWITTER_"+thirdUid;
            /** 绑定账号到系统 */
            UcMembers ucMembers=new UcMembers();
            ucMembers.setUsersource(ThirdUserConstants.UserSource.TWITTER);
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
