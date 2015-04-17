package com.sys.util;

public class Common {

	/**
	 * 当前服务器名称
	 */
	public static final String SERVER_URL = PropertiesUtil
			.getProperty("server_url");

	public static final String SERVER_IMG = PropertiesUtil
			.getProperty("server_img");

	public static final String HOME_IMG_PATH = PropertiesUtil
			.getProperty("home_img_path");

	public static final String DEFAULT_USER = PropertiesUtil
			.getProperty("default_user");
	public static final String DEFAULT_PWD = PropertiesUtil
			.getProperty("default_pwd");
	public static final String DEFAULT_TOKEN = PropertiesUtil
			.getProperty("default_token");

	/**
	 * Email config
	 */
	public static final String SERVER_EMAIL_NAME = PropertiesUtil
			.getProperty("server_email_name");
	public static final String SERVER_EMAIL_PWD = PropertiesUtil
			.getProperty("server_email_pwd");
	public static final String SERVER_EMAIL_HOSTNAME = PropertiesUtil
			.getProperty("server_email_hostname");
	public static final String SERVER_EMAIL_TITLE = PropertiesUtil
			.getProperty("server_email_title");
	
	/**
	 * 登陆注册 页面跳转 默认地址 login 
	 */
	
	public static final String DEFAULT_LOGIN_PAGE = PropertiesUtil
			.getProperty("default_login_page");
	public static final String DEFAULT_LOGIN_SUCCESS_REDIRECT_PAGE = PropertiesUtil
			.getProperty("default_login_success_redirect_page");
	public static final String DEFAULT_REGISTER_PAGE = PropertiesUtil
			.getProperty("default_register_page");
	public static final String DEFAULT_REGISTER_SUCCESS_REDIRECT_PAGE = PropertiesUtil
			.getProperty("default_register_success_redirect_page");
	

	/**
	 * 默认userId 测试 
	 */
	public static final String USER_ID = "20";
	
	
	
    /**
     * 模板文件根目录
     */
        public static final String UVO = "uvo";
}
