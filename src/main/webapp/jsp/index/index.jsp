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
<body class="login-body">
		<div class="login-big"> 
			<div class="login-headr"><p><a href="${default_index_url}"><img src="${_baasBase }/images/login-logo.png" /></a></p><p class="word"><spring:message code="dom.lables.accountlongin"/></p></div>
			<div class="login-wrapper">
				<div class="login-left"><img src="${_baasBase }/images/login-bj.png"></div>
			</div>
		</div>
</body>
</html>
