package org.jasig.cas.support.pac4j.plugin.common;

import java.util.Map;

import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.exception.SDKException;
import com.ai.paas.ipaas.ccs.constants.ConfigException;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * 从配置中心获取第三方登录配置信息
 * 配置中心路径：/thirdlogin
 * 配置示例：
 * {  <br>
  "callbackurl": "http://ssotest.yeecloud.com/login",<br>
  "thirdapps": {<br>
    "kingsoft": {<br>
      "appid": "",<br>
      "secret": ""<br>
    },<br>
    "baidu": {<br>
      "appid": "bt3CnVDOjdbZcYUaX59ZExXc",<br>
      "secret": "l0gM1BvAcY2L7PFr3xuG3rki2mKk305S"<br>
    },<br>
    "qq": {<br>
      "appid": "101378897",<br>
      "secret": "1dc5a602743f62a60ddf66b1cd2e20b7"<br>
    },<br>
    "sinaweibo": {<br>
      "appid": "606577360",<br>
      "secret": "2547eb81b19310ffbda5f83043817136"<br>
    },<br>
    "weixin": {<br>
      "appid": "wxe5a67ee0fb5e181f",<br>
      "secret": "25bd1d5a4ac75618a7ed2cbd46417800"<br>
    },<br>
 	"facebook": {<br>
 		"appid": "252390021878892",<br>
 		"secret": "d42ba06aafee03b2a79c727754cfbd85"<br>
 	},<br>
 	"twitter": {<br>
 		"appid": "2Q4fzw2JW4QrZ9BeiBCYH5oKt",<br>
 		"secret": "FqjNmgrq6szfU3B695habiu6sPL7ndR6bjadW42D1j20hb4pER"<br>
 	}<br>
  }<br>
}<br>
 *
 * Date: 2017年2月21日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author gucl
 */
public class ThirdLoginConfigUtil {
	public static final String CCS_THIRD_LOGIN_PATH="/thirdlogin";
	public static final String CALL_BACK_URL="callbackurl";
	public static final String THIRD_APPS="thirdapps";
	public static final String WEIXIN="weixin";
	public static final String SINA_WEIBO="sinaweibo";
	public static final String BAIDU="baidu";
	public static final String QQ="qq";
	public static final String KINGSOFT="kingsoft";
	public static final String FACEBOOK = "facebook";
	public static final String TWITTER = "twitter";

	public static String getCallBackUrl(){
		try {
            String conf = CCSClientFactory.getDefaultConfigClient().get(CCS_THIRD_LOGIN_PATH);
            if (StringUtil.isBlank(conf)) {
                throw new SDKException("第三方登录回调地址配置为空，请检查配置中心的相关配置");
            }
            JSONObject data = JSON.parseObject(conf);
            String callbackurl = data.getString(CALL_BACK_URL);
            if (StringUtil.isBlank(callbackurl)) {
                throw new SDKException("无法从配置中心获取" + CALL_BACK_URL + "");
            }
            return callbackurl;
        } catch (ConfigException e) {
            throw new SDKException("获取第三方登录回调地址配置错误", e);
        }
	}
	
	public static Map<String,ThirdOauthConfig> getAllThirdAppsConfig(){
		try {
            String conf = CCSClientFactory.getDefaultConfigClient().get(CCS_THIRD_LOGIN_PATH);
            if (StringUtil.isBlank(conf)) {
                throw new SDKException("第三方登录配置为空，请检查配置中心的相关配置");
            }
            JSONObject data = JSON.parseObject(conf);
            String strConfigsData = data.getString(THIRD_APPS);
            if (StringUtil.isBlank(strConfigsData)) {
                throw new SDKException("无法从配置中心获取" + THIRD_APPS + "对应的配置");
            }
            Map<String,ThirdOauthConfig> mapConfig = JSON.parseObject(strConfigsData,new TypeReference<Map<String,ThirdOauthConfig>>(){});
            
            return mapConfig;
        } catch (ConfigException e) {
            throw new SDKException("获取第三方登录配置错误", e);
        }
	}
	public static ThirdOauthConfig getWeixinConfig(){
		return getAllThirdAppsConfig().get(WEIXIN);
	}
	public static ThirdOauthConfig getSinaWeiboConfig(){
		return getAllThirdAppsConfig().get(SINA_WEIBO);
	}
	public static ThirdOauthConfig getBaiduConfig(){
		return getAllThirdAppsConfig().get(BAIDU);
	}
	public static ThirdOauthConfig getQQConfig(){
		return getAllThirdAppsConfig().get(QQ);
	}
	public static ThirdOauthConfig getKingSoftConfig(){
		return getAllThirdAppsConfig().get(KINGSOFT);
	}
	public static ThirdOauthConfig getFaceBookConfig(){
		return getAllThirdAppsConfig().get(FACEBOOK);
	}
	public static ThirdOauthConfig getTwitterConfig(){
		return getAllThirdAppsConfig().get(TWITTER);
	}

	public static void main(String[] args) {
//		System.out.println(JSON.toJSONString(getWeixinConfig()));
		System.out.println(getCallBackUrl());
	}
}
