package com.sys.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sys.util.Common;

@Controller
@Scope("prototype")
@RequestMapping(value = "/")
public class LoginOutAction {

	@RequestMapping(value = "loginOut.html", method = RequestMethod.GET)
	public ModelAndView loginOut(HttpServletRequest request,
			HttpServletResponse response) {

		// response.setContentType("text/plain");
		// response.setHeader("Pragma", "No-cache");
		// response.setHeader("Cache-Control", "no-cache");
		// response.setDateHeader("Expires", 0);
		// String callback = request.getParameter("jsonLoginOut");

		Cookie[] cookie = request.getCookies();
		System.out.println("login out ---->");
		if (cookie != null) {
			System.out.println("login out cookie is not null---->");
			for (Cookie ck : cookie) {
				if (Common.UVO.equals(ck.getName())) {
					Cookie token = new Cookie(ck.getName(), null);
					token.setMaxAge(0);
					token.setPath("/");
					if (Common.SERVER_URL.startsWith(Common.SERVER_URL)) {
						token.setDomain(".ipantre.com");
					}
					response.addCookie(token);
				}
			}
		}
		ModelAndView maView = new ModelAndView(new RedirectView(
				Common.DEFAULT_LOGIN_PAGE));
		return maView;
	}

}
