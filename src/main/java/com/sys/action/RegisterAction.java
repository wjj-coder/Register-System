package com.sys.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sys.entity.User;
import com.sys.service.UserService;
import com.sys.util.Common;
import com.sys.util.CommonUtil;
import com.sys.util.SendEmailVerify;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class RegisterAction {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "register.html")
	public ModelAndView showRegisterPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("www/register");
		return mav;
	}

	@RequestMapping(value = "registerSuccess.html")
	public ModelAndView showRegisterSuccessPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Successful");
		return mav;
	}

	@RequestMapping(value = "userRegister.html", method = RequestMethod.POST)
	public ModelAndView userRegister(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("userName") String userName,
			@RequestParam("userPwd") String userPwd) {

		// send email
		boolean verifyEmail = SendEmailVerify.sendEmail(userName);

		if (verifyEmail) {
			User user = new User();
			user.setUserName(userName);
			System.out.println(userPwd);
			userPwd = CommonUtil.md5Encode(userPwd);
			user.setUserPwd(userPwd);
			user.setStatus("0");// 0 未激活 1激活 9禁用

			String createDate = CommonUtil.createDate();
			user.setCreateDate(createDate);

			try {
				userService.saves(user);
			} catch (Exception e) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("sysError");
				return mav;
			}

		} else {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("verifyError");
			return mav;
		}

		ModelAndView mav = new ModelAndView(new RedirectView(
				Common.DEFAULT_REGISTER_SUCCESS_REDIRECT_PAGE));
		return mav;
	}

	@RequestMapping(value = "verifyEmail.html", method = RequestMethod.GET)
	public ModelAndView verifyEmail(String userName) {

		if (null != userName && !"".equals(userName)) {
			User user = userService.getByEmailAddress(userName);
			if (user != null) {

				try {
					if (user.getStatus().equals("0")) {
						user.setStatus("1");
					}
					userService.updates(user);
					System.out.println("邮箱验证成功:" + userName);
				} catch (Exception e) {
					ModelAndView err = new ModelAndView("sysError");
					return err;
				}

			} else {
				ModelAndView err = new ModelAndView("error");
				err.addObject("msg", "无效的验证地址");
				return err;
			}
		} else {
			ModelAndView err = new ModelAndView("error");
			err.addObject("msg", "无效的验证地址");
			return err;
		}

		ModelAndView mav = new ModelAndView("verifySuccessful");
		return mav;
	}

}
