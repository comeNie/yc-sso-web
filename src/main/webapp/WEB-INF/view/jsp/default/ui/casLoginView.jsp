<%@page import="com.ai.opt.uac.web.constants.Constants"%>
<%@page import="com.ai.opt.sdk.components.mcs.MCSClientFactory"%>
<%@page import="com.ai.opt.sdk.components.ccs.CCSClientFactory"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Date"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta property="wb:webmaster" content="d8bcb31352dcbeda" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>login</title>
	<%@ include file="/inc/inc.jsp"%>
	<script type="text/javascript" src="${_baasBase }/js/datacheck.js" ></script>
	<script language="javascript" src="${pageContext.request.contextPath}/resources/spm_modules/app/login/messenger.js"></script>  
	<script language="javascript" src="${pageContext.request.contextPath}/resources/spm_modules/app/login/casLoginView.js"></script>
	<%
	String default_editpassword_url = CCSClientFactory.getDefaultConfigClient().get("/default_editpassword_url");
	String default_register_url = CCSClientFactory.getDefaultConfigClient().get("/default_register_url");
	String errorNumCCS = CCSClientFactory.getDefaultConfigClient().get("/errorNum");
	String errorNumTimeOutCCS = CCSClientFactory.getDefaultConfigClient().get("/errorNumTimeOut");
	 request.setAttribute("default_editpassword_url", default_editpassword_url);
	 request.setAttribute("default_register_url", default_register_url);
	 request.setAttribute("errorNumCCS", errorNumCCS);
	 request.setAttribute("errorNumTimeOutCCS", errorNumTimeOutCCS);
	%>  
</head>
<body class="login-body">
		<div class="login-big"> 
	<form:form method="post" id="fm1" name="fm1" commandName="${commandName}" htmlEscape="true">
			<div class="login-headr"><p><a href="${default_index_url}"><img src="${_baasBase }/images/login-logo.png" /></a></p><p class="word"><spring:message code="dom.lables.accountlongin"/></p></div>
			<div class="login-wrapper">
				<div class="login-left"><img src="${_baasBase }/images/login-bj.png"></div>
				<div class="login-right radius">
					<div class="login-title"><spring:message code="dom.lables.signin"/></div>
					<div class="login-form-title">
						<ul>
							<label id="errorMsg"><form:errors path="*" id="msg" cssClass="errors" element="label" htmlEscape="false" /></label>
							<%-- <p class="right"><a href="#"><i class="icon iconfont">&#xe613;</i><spring:message code="dom.lables.mpql"/></a></p> --%>
						</ul>
					</div>
					<div class="login-form ">
						<ul>
							<li class="int-border radius">
								<p class="int-icon"><i class="icon iconfont">&#xe60c;</i></p>
								<p>
									<spring:message code="placeholder.username.tip" var="usernametip"/>
									<form:input cssClass="int-text logon-int" cssErrorClass="error" id="username" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true"  placeholder="${usernametip}"/>
								</p>
							</li>
							<li class="int-border radius">
								<p class="int-icon"><i class="icon iconfont">&#xe609;</i></p>
								<p>
								<spring:message code="placeholder.password.tip" var="passwordtip"/>
								<form:password  cssClass="int-text logon-int" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" 
								 placeholder="${passwordtip}" onkeydown="encryptPwd(event)"/>
								</p>
							</li>
							<input type="hidden" id="errorNumTimeOutCCS"  name="errorNumTimeOutCCS" value="${errorNumTimeOutCCS}"/>
							<input type="hidden" id="errorNumCCS"  name="errorNumCCS" value="${errorNumCCS}"/>
							<c:if test="${errorNum>=errorNumCCS}">
							<li>
								
								<p>
									  <spring:message code="placeholder.verifycode.tip" var="verifycodetip"/>
								      <input type="text" class="int-text logon-yz-int radius" id="captchaCode" 	
													tabindex="3" name="captchaCode" path="captchaCode" onkeydown="encryptCaptcha(event)"
													placeholder="${verifycodetip}"  style="width:225px"> 
								</p>
								<p>
									<spring:message code="dom.lables.codeagain" var="dom.lables.codeagain"/>
								      <img title="${dom.lables.codeagain}" src="${_base}/captcha/getImageVerifyCode" id="pictureVitenfy" onclick="reloadImage('${_base}/captcha/getImageVerifyCode');" style="margin-left:2px">
								 </p>
							</li>
							</c:if>
							<li>
								<p><a href="${default_register_url}"><spring:message code="dom.lables.register"/></a></p>
								<p class="right"><a href="${default_editpassword_url}"><spring:message code="dom.lables.forget"/></a></p>
							</li>
							<li>
								<input type="button" class="btn btn-blue login-btn radius20" value="<spring:message code="dom.lables.signin"/>" onclick="javascript:dologin();">
							</li>
							<li>
								<p><spring:message code="dom.lables.cooperative"/></p>
								<p class="line"></p>
							</li>
							<li>
								<p><a href="#" class="share share1"></a></p>
								<p><a href="#" class="share share2"></a></p>
								<p><a href="#" class="share share3"></a></p>
								<p><a href="${SinaWeiboClientUrl}" class="share share4"></a></p>
								<p><a href="${WeiXinClientUrl}" class="share share5"></a></p>
								<p><a href="#" class="share share6"></a></p>
								<p><a href="#" class="share share7 none-ma"></a></p>	
							</li>
						</ul>
					</div>
					
				</div>
			</div>
		<input type="hidden"  id="errorNum" name="errorNum" value="${errorNum}">
		<input type="hidden" name="lt" value="${loginTicket}" />
		<input type="hidden" name="execution" value="${flowExecutionKey}" />
		<input type="hidden" name="_eventId" value="submit" />
		<input type="hidden" name="sessionId" value="<%=request.getSession().getId()%>"/>
		</form:form>		
		</div>
</body>
</html>
