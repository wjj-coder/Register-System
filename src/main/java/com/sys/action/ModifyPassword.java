package com.sys.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.entity.User;
import com.sys.service.UserService;
import com.sys.util.CommonUtil;
import com.sys.util.user.CommonBLFun;
import com.sys.util.user.UVO;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class ModifyPassword {

	@Autowired
	private UserService userService;

	/**
	 * 修改密码 jsonp modifyUserPwd
	 * 
	 * @param request
	 * @param response
	 * @param userName
	 * @param oldPwd
	 * @param newPwd
	 * @return 返回 1修改成功，2旧密码错误，3修改失败
	 */
	@RequestMapping(value = "modifypwd.json", method = RequestMethod.GET)
	@ResponseBody
	public String modifyPwd(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("oldPwd") String oldPwd,
			@RequestParam("newPwd") String newPwd) {

		response.setContentType("text/plain");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String callback = request.getParameter("modifyUserPwd");

		UVO uvo = CommonBLFun.getUVO(request);
		String userName = null;
		if (uvo != null) {
			userName = uvo.getEmail() + "";
		}
		// System.out.println(userName);
		if (userName != null && oldPwd != null && newPwd != null) {
			// System.out.println(userName);
			User user = new User();
			user = userService.getByEmailAddress(userName);
			if (user != null) {
				// 判断旧密码
				if (user.getUserPwd().equals(CommonUtil.md5Encode(oldPwd))) {
					user.setUserPwd(CommonUtil.md5Encode(newPwd));
					try {
						userService.updates(user);
					} catch (Exception e) {
						e.printStackTrace();
						return callback + "(3)";
					}
					return callback + "(1)";
				} else {
					return callback + "(2)";
				}
			}

		}
		return callback + "(3)";
	}

}
