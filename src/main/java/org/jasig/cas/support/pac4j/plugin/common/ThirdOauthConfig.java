package org.jasig.cas.support.pac4j.plugin.common;

/**
 * 第三方登录配置信息模型
 *
 * Date: 2017年2月21日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author gucl
 */
public class ThirdOauthConfig {
	private String appid;
	private String secret;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
}
