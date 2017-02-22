package org.jasig.cas.support.pac4j.plugin.qq.openid;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class QQConnectException extends Exception {
	private int statusCode = -1;
	private static final long serialVersionUID = -2623309261327598087L;

	public QQConnectException(String msg) {
		super(msg);
	}

	public QQConnectException(Exception cause) {
		super(cause);
	}

	public QQConnectException(String msg, int statusCode) throws JSONException {
		super(msg);
		this.statusCode = statusCode;
	}

	public QQConnectException(String msg, JSONObject json, int statusCode) throws JSONException {
		super(msg + "\n error:" + json.getString("msg"));
		this.statusCode = statusCode;
	}

	public QQConnectException(String msg, Exception cause) {
		super(msg, cause);
	}

	public QQConnectException(String msg, Exception cause, int statusCode) {
		super(msg, cause);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}
}