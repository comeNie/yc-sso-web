package org.jasig.cas.support.pac4j.plugin.qq;

import org.jasig.cas.support.pac4j.plugin.common.BaseAttributesDefinition;
import org.pac4j.core.profile.converter.Converters;

/**
 * 用于接收微信返回的用户信息
 * @author gucl
 *
 */
public class QQAttributesDefinition extends BaseAttributesDefinition {

    public static final String OPEN_ID = "openid";
    public static final String NICK_NAME = "nickname";
    public static final String GENDER = "gender";
    public static final String VIP = "vip";
    public static final String LEVEL = "level";
    public static final String RET = "ret";
    public static final String MSG = "msg";
    public static final String FIGUREURL = "figureurl";
    public static final String FIGUREURL_1 = "figureurl_1";
    public static final String FIGUREURL_2 = "figureurl_2";
    public static final String FIGUREURL_QQ_1 = "figureurl_qq_1";
    public static final String FIGUREURL_QQ_2 = "figureurl_qq_2";

    public QQAttributesDefinition(){
        addAttribute(OPEN_ID, Converters.stringConverter);
        addAttribute(NICK_NAME, Converters.stringConverter);
        addAttribute(GENDER, Converters.stringConverter);
        addAttribute(VIP, Converters.booleanConverter);
        addAttribute(LEVEL, Converters.integerConverter);
        addAttribute(RET, Converters.integerConverter);
        addAttribute(MSG, Converters.stringConverter);
        addAttribute(FIGUREURL, Converters.booleanConverter);
        addAttribute(FIGUREURL_1, Converters.booleanConverter);
        addAttribute(FIGUREURL_2, Converters.booleanConverter);
        addAttribute(FIGUREURL_QQ_1, Converters.booleanConverter);
        addAttribute(FIGUREURL_QQ_2, Converters.booleanConverter);
    }
}
