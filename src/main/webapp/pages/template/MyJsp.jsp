<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<title>激活账号</title>
</head>
<body>
<p>您好 , <strong>${userName}</strong>： </p>
<div style='margin-left:30px;'>
<p>您的账号已经成功注册，下一步操作，需要验证您的登录邮箱，请点击以下链接激活账号。</p>
<p><a href="${url}">
点此激活:${url}
</a></p>
<p>如果上面的链接无法点击，您也可以复制链接，粘贴到您的览器的地址栏内，然后按“回车”键打开预设页面，完成相
应功能。</p>
</div>
<p>此为系统消息，请勿回复</p>
</body>
</html>