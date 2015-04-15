package com.sys.util.user;

import java.io.Serializable;

public class UVO implements Serializable {
	private static final long serialVersionUID = -7213131344173101692L;
	private Integer userId;
	private String email;
	private String encodedPassword;

	/**
	 * 邮箱
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * userId
	 * 
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * userId
	 * 
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 加密后的密码(base64再加密一遍的)
	 * 
	 * @return
	 */
	public String getEncodedPassword() {
		return encodedPassword;
	}

	/**
	 * 加密后的密码(base64再加密一遍的)
	 * 
	 * @param encodedPassword
	 */
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

}
