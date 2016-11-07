<!DOCTYPE html>
<%@page import="com.ai.opt.uac.web.constants.Constants"%>
<%@page import="com.ai.opt.sdk.components.mcs.MCSClientFactory"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.Date"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-cn">
<head>
<%@ include file="/inc/inc.jsp"%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>login</title>
	<script type="text/javascript" src="${_baasBase }/js/datacheck.js" ></script>
	<script language="javascript" src="${pageContext.request.contextPath}/resources/spm_modules/app/login/messenger.js"></script>  
	<script language="javascript" src="${pageContext.request.contextPath}/resources/spm_modules/app/login/casLoginView.js"></script>  
</head>
<body class="login-body">
		<div class="login-big"> 
	<form:form method="post" id="fm1" name="fm1" commandName="${commandName}" htmlEscape="true">
			<div class="login-headr"><p><img src="${_baasBase }/images/login-logo.png" /></p><p class="word">账号登录</p></div>
			<div class="login-wrapper">
				<div class="login-left"><img src="${_baasBase }/images/login-bj.png"></div>
				<div class="login-right radius">
					<div class="login-title">登录</div>
					<div class="login-form-title">
						<ul>
							<label id="errorMsg"><form:errors path="*" id="msg" cssClass="errors" element="label" htmlEscape="false" /></label>
							<p class="right"><a href="#"><i class="icon iconfont">&#xe613;</i>手机快速登录</a></p>
						</ul>
					</div>
					<div class="login-form ">
						<ul>
							<li class="int-border radius">
								<p class="int-icon"><i class="icon iconfont">&#xe60c;</i></p>
								<p>
									<form:input cssClass="int-text logon-int" cssErrorClass="error" id="username" tabindex="1" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" placeholder="登录名/手机号／邮箱"/>
								</p>
							</li>
							<li class="int-border radius">
								<p class="int-icon"><i class="icon iconfont">&#xe609;</i></p>
								<p>
								<form:password  cssClass="int-text logon-int" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off"  placeholder="密码" onkeydown="encryptPwd(event)"/>
								</p>
							</li>
							<li>
								
								<p>
								        <input type="text" class="int-text logon-yz-int radius" id="captchaCode" 	
													tabindex="3" name="captchaCode" path="captchaCode" onkeydown="encryptCaptcha(event)"
													placeholder="请输入验证码"> 
								</p>
								<p>
								      <img title="点击重新获取验证码" src="${_base}/captcha/getImageVerifyCode" id="pictureVitenfy" onclick="reloadImage('${_base}/captcha/getImageVerifyCode');">
								 </p>
							</li>
							<li>
								<p><a href="#">注册</a></p>
								<p class="right"><a href="#">忘记密码</a></p>
							</li>
							<li>
								<input type="button" class="btn btn-blue login-btn radius20" value="登 录" onclick="javascript:dologin();">
							</li>
							<li>
								<p>合作账号登录</p>
								<p class="line"></p>
							</li>
							<li>
								<p><a href="#" class="share share1"></a></p>
								<p><a href="#" class="share share2"></a></p>
								<p><a href="#" class="share share3"></a></p>
								<p><a href="#" class="share share4"></a></p>
								<p><a href="#" class="share share5"></a></p>
								<p><a href="#" class="share share6"></a></p>
								<p><a href="#" class="share share7 none-ma"></a></p>	
							</li>
						</ul>
					</div>
					
				</div>
			</div>
			
		<input type="hidden" name="lt" value="${loginTicket}" />
		<input type="hidden" name="execution" value="${flowExecutionKey}" />
		<input type="hidden" name="_eventId" value="submit" />
		<input type="hidden" name="sessionId" value="<%=request.getSession().getId()%>"/>
		</form:form>		
		</div>
</body>
</html>
