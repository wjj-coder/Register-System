<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<!-- InstanceBeginEditable name="doctitle" -->
<title>验证成功</title>
<%@ include file="/pages/lib.jsp"%>
<script type="text/javascript" src="${baseUrl }/js/jquery-1.7.1.min.js"></script>
<style type="text/css">
.succ {
 width: 500px;
 position: fixed;
			z-index: 9999;
			left: 50%;
			text-align:center;
			margin-left: -250px;
			background-color: rgb(255, 255, 255);
			padding: 10px 20px;
			border:1px solid rgb(200,200,200);
			box-shadow: rgb(242, 242, 242) 0px 0px 6px;
			top:50px;
}
</style>
<script type="text/javascript">
	var count = 0;
	function start() {
		setInterval("writeTime()", 1000);
	}
	function writeTime() {
		count++;
		$("#times").html(10 - count + "");
		if (count == 10) {
			login();
		}
	}

	function login() {
		var base = getRootPath();
		var loginUrl = base + "/login.html";
		//window.location.href = loginUrl;LoginPage
		window.location.href = '${LoginPage}';
	}
	$(function() {
		start();
	});
</script>
</head>

<body>
<div class="succ">
<div style="text-align: center;font-size: 20px;">邮箱验证成！</div>
<div style="text-align: center;">
<span id="times">10</span>秒后 跳转到<a href="${LoginPage}">登陆</a>页面

</div>
</div>
</body>

</html>

