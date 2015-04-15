package com.sys.util;

import javax.servlet.http.Cookie;

public class CheckCookie {

	public static String getToken(Cookie[] cookies) {
		String token = "";
		if (null != cookies) {
			for (Cookie ck : cookies) {
				if ("token".equals(ck.getName())) {
					token = ck.getValue();
				}
			}

		}

		return token;
	}

}
