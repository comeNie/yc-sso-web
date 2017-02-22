package org.jasig.cas.support.pac4j.plugin.qq.openid;

import java.io.Serializable;

public class QQConnect implements Serializable {
	private static final long serialVersionUID = 2403532632395197292L;
	protected HttpClient client = new HttpClient();

	protected QQConnect() {
	}

	protected QQConnect(String token, String openID) {
		this.client.setToken(token);
		this.client.setOpenID(openID);
	}

	protected void setToken(String token) {
		this.client.setToken(token);
	}

	protected void setOpenID(String openID) {
		this.client.setOpenID(openID);
	}
}
