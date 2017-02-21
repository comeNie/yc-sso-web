package org.jasig.cas.support.pac4j.plugin.common;

import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.profile.OAuthAttributesDefinition;

/**
 * 第三方登录用户基本信息
 * @author gucl
 *
 */
public class BaseAttributesDefinition extends OAuthAttributesDefinition {

	
	//用户名称(账号ID/手机号码/邮件)
	public static final String USER_NAME="username";
	 //租户ID
    public static final String TENANT_ID="tenantId";
    //账号ID
    public static final String USER_ID="userId";
    //账号名称
    public static final String LOGIN_NAME="loginName";
    //手机号码
    public static final String MOBILE="mobile";
    //邮件
    public static final String EMAIL="email";
    //国家代码
    public static final String DOMAIN_NAME="domainname";


    public BaseAttributesDefinition(){
        addAttribute(USER_NAME, Converters.stringConverter);
        addAttribute(TENANT_ID, Converters.stringConverter);
        addAttribute(USER_ID, Converters.stringConverter);
        addAttribute(LOGIN_NAME, Converters.stringConverter);
        addAttribute(MOBILE, Converters.stringConverter);
        addAttribute(EMAIL, Converters.stringConverter);
        addAttribute(DOMAIN_NAME, Converters.stringConverter);
    }
}
