<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<title>注册</title>
<%@ include file="/pages/lib.jsp"%>
<script type="text/javascript" src="${baseUrl }/js/jquery-1.7.1.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function CheckMail() {
		var mail = $("#userName").val();
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(mail != ""){
			if (filter.test(mail)){
				return true;
			}else {
				alert('您的电子邮件格式不正确');
				return false;
			}
		}
		
	}
	
	function submits(){
	var mails = $("#userName").val();
	var pwd1=$("#userPwd").val();
	var pwd2=$("#userPwd2").val();
		if($("#userName").val()=="" || $("#userPwd").val()=="" || $("#userPwd2").val()==""){
			alert("带 * 为 必填");
			return;
		}
		
		
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (filter.test(mails)){
			if(pwd1==pwd2){
				$("#addUserForm").submit();
				//alert("注册成功");
			}else{
				alert("两次密码不一样");
			}
		}else {
				alert('您的电子邮件格式不正确');
				return;
		}
		
	}
</script>

</head>

<body>

	<div>
		<form action="${baseUrl }/userRegister.html"
			enctype="multipart/form-data" method="post" id="addUserForm">
			注册邮箱:<input type="text" name="userName" id="userName"
				onblur="CheckMail();" />*<span style="font-size: 12px;">邮箱验证发送激活邮件</span><br />
			<br /> 设置密码:<input type="password" name="userPwd" id="userPwd" />*
			<br /> <br /> 确认密码:<input type="password" name="userPwd2"
				id="userPwd2" />*<br /> <br /> <input type="button" value="注册"
				onclick="javascript:submits();" />
		</form>

	</div>


</body>
</html>
