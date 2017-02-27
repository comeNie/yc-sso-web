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
        addAttribute("name", Converters.stringConverter);  //用户全名
//        addAttribute("gender", Converters.stringConverter);  //性别,m--男，f--女,n--未知
//        addAttribute("first_name", Converters.stringConverter);//
//        addAttribute("last_name", Converters.stringConverter);//
//        addAttribute("cover", Converters.stringConverter);//cover图片地址
    }
}
