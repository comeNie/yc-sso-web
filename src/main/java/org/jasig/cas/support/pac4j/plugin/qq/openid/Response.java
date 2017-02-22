package org.jasig.cas.support.pac4j.plugin.qq.openid;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class Response {
	static Logger log = LoggerFactory.getLogger(Response.class.getName());

	private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal<DocumentBuilder>() {
		protected DocumentBuilder initialValue() {
			try {
				return DocumentBuilderFactory.newInstance().newDocumentBuilder();
			} catch (ParserConfigurationException ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}
	};
	private int statusCode;
	private Document responseAsDocument = null;
	private String responseAsString = null;
	private InputStream is;
	private HttpURLConnection con;
	private boolean streamConsumed = false;

	private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

	public Response() {
	}

	public Response(HttpURLConnection con) throws IOException {
		this.con = con;
		this.statusCode = con.getResponseCode();
		if (null == (this.is = con.getErrorStream())) {
			this.is = con.getInputStream();
		}
		if ((null != this.is) && ("gzip".equals(con.getContentEncoding()))) {
			this.is = new GZIPInputStream(this.is);
		}
	}

	Response(String content) {
		this.responseAsString = content;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public String getResponseHeader(String name) {
		if (this.con != null) {
			return this.con.getHeaderField(name);
		}
		return null;
	}

	public InputStream asStream() {
		if (this.streamConsumed) {
			throw new IllegalStateException("Stream has already been consumed.");
		}
		return this.is;
	}

	public String asString() throws QQConnectException {
		if (null == this.responseAsString) {
			try {
				InputStream stream = asStream();
				if (null == stream) {
					return null;
				}
				BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

				StringBuffer buf = new StringBuffer();
				String line;
				while (null != (line = br.readLine())) {
					buf.append(line).append("\n");
				}
				this.responseAsString = buf.toString();
				log(this.responseAsString);
				stream.close();
				this.con.disconnect();
				this.streamConsumed = true;
			} catch (NullPointerException npe) {
				throw new QQConnectException(npe.getMessage(), npe);
			} catch (IOException ioe) {
				throw new QQConnectException(ioe.getMessage(), ioe);
			}
		}
		return this.responseAsString;
	}

	public Document asDocument() throws QQConnectException {
		if (null == this.responseAsDocument) {
			try {
				this.responseAsDocument = ((DocumentBuilder) builders.get())
						.parse(new ByteArrayInputStream(asString().getBytes("UTF-8")));
			} catch (SAXException saxe) {
				throw new QQConnectException("The response body was not well-formed:\n" + this.responseAsString, saxe);
			} catch (IOException ioe) {
				throw new QQConnectException("There's something with the connection.", ioe);
			}
		}
		return this.responseAsDocument;
	}

	public JSONObject asJSONObject() throws QQConnectException {
		try {
			return JSONObject.parseObject(asString());
		} catch (JSONException jsone) {
			throw new QQConnectException(jsone.getMessage() + ":" + this.responseAsString, jsone);
		}
	}

	public JSONArray asJSONArray() throws QQConnectException {
		try {
			return JSONArray.parseArray(asString());
		} catch (Exception jsone) {
			throw new QQConnectException(jsone.getMessage() + ":" + this.responseAsString, jsone);
		}
	}

	public InputStreamReader asReader() {
		try {
			return new InputStreamReader(this.is, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
		}
		return new InputStreamReader(this.is);
	}

	public void disconnect() {
		this.con.disconnect();
	}

	public static String unescape(String original) {
		Matcher mm = escaped.matcher(original);
		StringBuffer unescaped = new StringBuffer();
		while (mm.find()) {
			mm.appendReplacement(unescaped, Character.toString((char) Integer.parseInt(mm.group(1), 10)));
		}

		mm.appendTail(unescaped);
		return unescaped.toString();
	}

	public String toString() {
		if (null != this.responseAsString) {
			return this.responseAsString;
		}
		return "Response{statusCode=" + this.statusCode + ", response=" + this.responseAsDocument + ", responseString='"
				+ this.responseAsString + '\'' + ", is=" + this.is + ", con=" + this.con + '}';
	}

	private void log(String message) {
		log.debug("[" + new Date() + "]" + message);
	}

	private void log(String message, String message2) {
		log(message + message2);
	}

	public String getResponseAsString() {
		return this.responseAsString;
	}

	public void setResponseAsString(String responseAsString) {
		this.responseAsString = responseAsString;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}