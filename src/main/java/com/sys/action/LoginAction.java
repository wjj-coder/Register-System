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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sys.entity.User;
import com.sys.service.UserService;
import com.sys.util.Common;
import com.sys.util.CommonUtil;
import com.sys.util.user.CommonBLFun;
import com.sys.util.user.UVO;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class LoginAction {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "login.html")
	public ModelAndView loginPage() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("www/login");
		return mav;
	}

	@RequestMapping(value = "loginSuccess.html")
	public ModelAndView loginSuccess() {
		ModelAndView mav = new ModelAndView("loginSuccessFul");
		return mav;
	}

	@RequestMapping(value = "verifyLogin.html", method = RequestMethod.POST)
	public ModelAndView verifyLogin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("u_mail") String userName,
			@RequestParam("pwd") String userPwd) {

		User user = new User();

		user = userService.getByEmailAndStatus(userName);

		if (null != user) {

			if (user.getUserName().equals(userName)
					&& user.getUserPwd().equals(CommonUtil.md5Encode(userPwd))) {

				// TODO 可配置登陆成功后跳转的页面
				UVO uvo = new UVO();
				CommonBLFun.addUVO(uvo, response);

				ModelAndView mav = new ModelAndView(new RedirectView(
						Common.SERVER_URL + "/loginSuccess.html"));
				return mav;
			} else {
				ModelAndView err = new ModelAndView();
				err.setViewName("www/login");
				err.addObject("msg", "用户名或密码错误");
				return err;
			}

		} else {
			ModelAndView err = new ModelAndView();
			err.setViewName("www/login");
			err.addObject("msg", "用户名或密码错误");
			return err;
		}

	}

	/**
	 * 返回是否登录成功 jsonp 登录页面js调用
	 * 
	 * @param request
	 * @param response
	 * @param userName
	 * @param userPwd
	 * @return verify 返回是否登录成功 false OR true
	 */
	@RequestMapping(value = "verifyLogin.json", method = RequestMethod.GET)
	@ResponseBody
	public String verifyLoginJson(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("u_mail") String userName,
			@RequestParam("pwd") String userPwd) {
		System.err.println("login verify:" + userName + userPwd);
		response.setContentType("text/plain");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String callback = request.getParameter("jsonVerifyLogin");

		String verify = "false";

		User user = new User();
		user = userService.getByEmailAndStatus(userName);

		if (null != user) {

			if (user.getUserName().equals(userName)
					&& user.getUserPwd().equals(CommonUtil.md5Encode(userPwd))) {
				// TODO 可配置登陆成功后跳转的页面
				UVO uvo = new UVO();
				uvo.setUserId(user.getUserId());
				uvo.setEmail(userName);
				uvo.setEncodedPassword(CommonUtil.md5Encode(userPwd));
				CommonBLFun.addUVO(uvo, response);
				verify = "true";
				return callback + "(" + verify + ")";
			} else {
				return callback + "(" + verify + ")";
			}

		} else {
			return callback + "(" + verify + ")";
		}

	}

	/**
	 * 验证是否登录 jsonp 所有页面js调用
	 * 
	 * @param request
	 * @param response
	 * @param userName 用户名
	 * @param encodePwd 加密密码
	 * @return verify 返回是否登录成功 false OR true
	 */
	@RequestMapping(value = "verifyLoginJsPage.json", method = RequestMethod.GET)
	@ResponseBody
	public String verifyLoginJsPage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("u_mail") String userName,
			@RequestParam("encodePwd") String userPwd) {
		System.err.println("login verify:" + userName + userPwd);
		response.setContentType("text/plain");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String callback = request.getParameter("jsonVerifyLogin");

		String verify = "false";

		User user = new User();
		user = userService.getByEmailAndStatus(userName);

		if (null != user) {

			if (user.getUserName().equals(userName)
					&& user.getUserPwd().equals(CommonUtil.md5Encode(userPwd))) {
				// TODO 可配置登陆成功后跳转的页面
				UVO uvo = new UVO();
				uvo.setUserId(user.getUserId());
				uvo.setEmail(userName);
				uvo.setEncodedPassword(CommonUtil.md5Encode(userPwd));
				CommonBLFun.addUVO(uvo, response);
				verify = "true";
				return callback + "(" + verify + ")";
			} else {
				return callback + "(" + verify + ")";
			}

		} else {
			return callback + "(" + verify + ")";
		}

	}

}
