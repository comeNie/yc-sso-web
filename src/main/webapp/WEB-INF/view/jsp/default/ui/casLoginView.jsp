<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //根据系统标识，显示不同页面
    String systemId = request.getParameter("systemId");
    String loginView = "/jsp/clientLogin/yeecloud/loginView.jsp";
    if(systemId!=null && !"".equals(systemId.trim())){
        loginView = "/jsp/clientLogin/"+systemId+"/loginView.jsp";
    }
    request.setAttribute("loginView",loginView);
    System.out.println("loginView = [" + loginView + "]");
%>
<jsp:forward page="${loginView}"/>