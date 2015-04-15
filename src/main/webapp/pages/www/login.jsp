<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<title>登陆</title>
<%@ include file="/pages/lib.jsp"%>
<script type="text/javascript" src="${baseUrl }/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function submits() {
		$("#loginForm").submit();
	}
</script>
</head>

<body>
	<div>
		<form action="verifyLogin.html" enctype="multipart/form-data"
			method="post" id="loginForm">
			邮箱:<input type="text" name="u_mail" id="u_mail" /><br /> <br />
			密码:<input type="password" name="pwd" id="pwd" /><br /> <span
				style="display: block;">${msg }</span> <input type="button"
				value="登陆" onclick="javascript:submits();"><br /> <br />
		</form>
	</div>
</body>
</html>
