package com.sys.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author wjj
 * 
 */
public class CommonUtil {

	/**
	 * 获取 当前时间 格式 yyyy-MM-dd HH:MM:ss
	 * 
	 * @return current time
	 */
	public static String createDate() {

		Date date = new Date();
		DateFormat formats = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		String createDate = formats.format(date);

		return createDate;
	}

	/**
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encodeForSession(String password) {
		try {
			return Base64.encodeBase64URLSafeString(password.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String decodeForSession(String password) {
		byte[] b;
		try {
			b = Base64.decodeBase64(password);
			return new String(b);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String md5Encode(String str) {
		String string = "";
		try {
			string = DigestUtils.md5Hex(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return string;
	}

}
