package org.jasig.cas.support.pac4j.plugin.qq.openid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenID extends QQConnect {
	private static final long serialVersionUID = 6913005509508673584L;

	private String openIDURL;

	public OpenID(String token, String openIDURL) {
		this.client.setToken(token);
		this.openIDURL = openIDURL;
	}

	public String getOpenIDURL() {
		return openIDURL;
	}

	public void setOpenIDURL(String openIDURL) {
		this.openIDURL = openIDURL;
	}

	private String getUserOpenID(String accessToken) throws QQConnectException {
		String openid = "";
		// TODO
		String jsonp = this.client
				.get(openIDURL, new PostParameter[] { new PostParameter("access_token", accessToken) }).asString();

		Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(jsonp);

		if (m.find())
			openid = m.group(1);
		else {
			throw new QQConnectException("server error!");
		}
		return openid;
	}

	public String getUserOpenID() throws QQConnectException {
		String accessToken = this.client.getToken();
		return getUserOpenID(accessToken);
	}
}