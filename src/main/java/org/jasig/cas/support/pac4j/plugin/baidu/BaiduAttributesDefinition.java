package org.jasig.cas.support.pac4j.plugin.baidu;

import org.jasig.cas.support.pac4j.plugin.common.BaseAttributesDefinition;
import org.pac4j.core.profile.converter.Converters;

/**
 * 用于接收百度返回的用户信息
 * @author b2c021
 *
 */
public class BaiduAttributesDefinition extends BaseAttributesDefinition {

    public BaiduAttributesDefinition(){
    	
        addAttribute("userid", Converters.stringConverter);  
        addAttribute("username", Converters.stringConverter);  
        addAttribute("portrait", Converters.stringConverter); 
        
        addAttribute("birthday", Converters.stringConverter);  
        addAttribute("blood", Converters.stringConverter);  
        addAttribute("constellation", Converters.stringConverter);  
        addAttribute("education", Converters.stringConverter);  
        addAttribute("figure", Converters.stringConverter);  
        addAttribute("is_bind_mobile", Converters.stringConverter);  
        addAttribute("job", Converters.stringConverter);  
        addAttribute("marriage", Converters.stringConverter);  
        addAttribute("sex", Converters.stringConverter);  
        addAttribute("trade", Converters.stringConverter);  
    }
}
