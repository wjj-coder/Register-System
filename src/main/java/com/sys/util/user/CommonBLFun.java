package com.sys.util.user;

import java.text.DecimalFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import com.sys.util.Common;

public class CommonBLFun {

	/**
	 * 取得UVO
	 * @param request
	 * @return
	 */
    public static UVO getUVO(HttpServletRequest request){
    	Cookie[] cookies = request.getCookies();
    	if(cookies==null){
    		return null;
    	}

    	for (Cookie cookie : cookies) {
    		System.out.println(cookie.getName());

			String cName = cookie.getName();
			if (Common.UVO.equals(cName)) {
				String coStr = cookie.getValue();
				UVO uvo = unmarshall(coStr);
				return uvo;
			}
		}
    	return null;
    }
    
    private static UVO unmarshall(String cvalue) {
    	UVO uvo = new UVO();
    	try {
    		// TODO decode
    		String[] kvs = cvalue.split("\\|");
    		if (kvs.length < 3) {
    			return null;
    		}
    		String userid = kvs[0];
    		uvo.setUserId(Integer.parseInt(userid));// 1
    		uvo.setEmail(kvs[1]);// 2
    		uvo.setEncodedPassword(kvs[2]);// 3
		} catch (Exception e) {
			return null;// error return null
		}
    	return uvo;
    }
    
    public static void addUVO(UVO uvo, HttpServletResponse res) {
    	String cvalue = marshall(uvo);
    	Cookie cookie = new Cookie(Common.UVO,cvalue);
    	if (Common.SERVER_URL.startsWith("http://www")) {
    		cookie.setDomain(".ipantre.com");
    	}
    	cookie.setMaxAge(-1);
    	cookie.setPath("/");
    	res.addCookie(cookie);
    }
    public static void addtag(String tag, HttpServletResponse res) {
    	Cookie cookie = new Cookie("tag",tag);
    	if (Common.SERVER_URL.startsWith("http://www")) {
    		cookie.setDomain(".ipantre.com");
    	}
    	cookie.setMaxAge(-1);
    	cookie.setPath("/");
    	res.addCookie(cookie);
    }
    public static String gettag(HttpServletRequest request){
    	Cookie[] cookies = request.getCookies();
    	if(cookies==null){
    		return null;
    	}

    	for (Cookie cookie : cookies) {
    		System.out.println(cookie.getName());

			String cName = cookie.getName();
			if ("tag".equals(cName)) {
				String coStr = cookie.getValue();
				return coStr;
			}
		}
    	return null;
    }
    private static String marshall(UVO uvo) {
    	String kvs = "";
    	kvs += uvo.getUserId();// 1
    	kvs += "|";
    	kvs += uvo.getEmail();// 2
    	kvs += "|";
    	kvs += uvo.getEncodedPassword();// 5
    	return kvs;
    }
    

//    public static String getUVOUserName(HttpServletRequest request){
//    	UVO uvo = getUVO(request);
//    	
//    	return uvo == null ? null : "" + uvo.getUserName();
//    }

    public static String getUVOUserId(HttpServletRequest request){
    	UVO uvo = getUVO(request);
    	return uvo == null ? null : "" + uvo.getUserId();
    }
    /**
     * 
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
     * 
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
    /**
     * str非null && str非"" 返回true
     * 
     * @param str
     * @return
     */
    public static boolean assertNotNull(String str) {
        return str != null && !str.trim().equals("");
    }
    /**
     * 转int类型
     * 
     * @param str
     * @return
     */
    public static Integer convertStringToInt(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    /**
     * null 或 "" 返回true
     * @param str
     * @return
     */
    public static boolean assertNull(String str) {
        return str == null || str.trim().equals("");
    }
    /**
     * int类型分转换成元，保留小数点后2位
     * @param priceFen 价格
     * @return
     */
    public static String fen2YuanFormat(int priceFen) {

        double tmp = fen2Yuan(priceFen);
        return df.format(tmp);
    }
    /**
     * 分转元
     * @param priceFen
     * @return
     */
    public static double fen2Yuan(int priceFen) {
        return (double) priceFen / 100;
    }

    /**
     * 保留两位小数的format
     */
    private static DecimalFormat df = new DecimalFormat("0.00");
    
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

   
}
