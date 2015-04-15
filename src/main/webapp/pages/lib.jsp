<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.sys.util.Common"%>

<%
	String baseUrl = Common.SERVER_URL;
	pageContext.setAttribute("baseUrl", baseUrl);
	String LoginPage = Common.DEFAULT_LOGIN_PAGE;
	pageContext.setAttribute("LoginPage",LoginPage);
	
	String serverUrl = (String)pageContext.getAttribute("baseUrl") ; 
%>

<input type="hidden" id="serverUrl" value="<%=baseUrl %>" />
<script type="text/javascript" src="${baseUrl }/js/Common.js"></script>
