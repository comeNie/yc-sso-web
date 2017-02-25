package org.jasig.cas.support.pac4j.plugin.facebook;

import org.jasig.cas.support.pac4j.plugin.common.BaseAttributesDefinition;
import org.pac4j.core.profile.converter.Converters;

/**
 * 用于接收facebook返回的用户信息
 * @author liutong
 *
 */
public class FaceBookAttributesDefinition extends BaseAttributesDefinition {

	/*
	* id
    cover
    name
    first_name
    last_name
    age_range
    link
    gender
    locale
    picture
    timezone
    updated_time
    verified
	* */


    public FaceBookAttributesDefinition(){
        addAttribute("id", Converters.stringConverter);  //用户UID
        addAttribute("idstr", Converters.stringConverter);  //用户UIDgender
        addAttribute("gender", Converters.stringConverter);  //性别,m--男，f--女,n--未知
        addAttribute("avatar_hd", Converters.stringConverter);//大头像地址高清
        addAttribute("avatar_large", Converters.stringConverter);//大头像地址
        addAttribute("cover_image", Converters.stringConverter);//cover图片地址
    }
}
