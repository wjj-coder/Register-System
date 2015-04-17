package com.sys.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class SendEmailVerify {

	public static boolean sendEmail(String emailAddress) {
		try {
			System.out.println("send mail:" + emailAddress);
			emailSend(emailAddress);
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void emailSend(String emailAddress) throws EmailException {

		HtmlEmail email = new HtmlEmail();
		email.setStartTLSEnabled(true);
		email.setHostName(Common.SERVER_EMAIL_HOSTNAME);
		email.setAuthentication(Common.SERVER_EMAIL_NAME,
				Common.SERVER_EMAIL_PWD);
		email.setFrom(Common.SERVER_EMAIL_NAME, Common.SERVER_EMAIL_TITLE);
		email.setCharset("utf-8");

		Map<String, String> params = new HashMap<String, String>();
		String verifyUrl = Common.SERVER_URL + "/verifyEmail.html?userName="
				+ emailAddress;
		params.put("userName", emailAddress);
		params.put("url", verifyUrl);
		String html = FreeMarkerResolver.htmlText("user-active-mail.ftl", params);

		StringBuilder content = new StringBuilder();
		content.append(html);
		
		/*
		content.append("<div>欢迎注册 pantre 童装商城,点击以下链接激活账号:</div>");
		content.append("<a href='" + Common.SERVER_URL
				+ "/verifyEmail.html?userName=" + emailAddress + "'>");
		content.append(Common.SERVER_URL + "/verifyEmail.html?userName="
				+ emailAddress + "</a>");
		
		*/
		
		email.addTo(emailAddress);
		/**
		 * email.addCc(""); 抄送 email.addBcc("");密送
		 */

		// set the html message 发送html邮件
		email.setHtmlMsg(content.toString());
		email.setSubject("邮箱激活验证");

		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");
		email.send();

	}
}
