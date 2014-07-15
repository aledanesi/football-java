package com.jfootball.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.jfootball.domain.user.UserBean;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @version $Id: SignonInterceptor.java,v 1.4 2004/01/04 23:43:41 jhoeller Exp $
 */
public class SignonInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		UserBean userSession = (UserBean) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession == null) {
			String url = request.getServletPath();
			String query = request.getQueryString();
			ModelAndView modelAndView = new ModelAndView("SignonForm");
			if (query != null) {
				modelAndView.addObject("signonForwardAction", url+"?"+query);
			}
			else {
				modelAndView.addObject("signonForwardAction", url);
			}
			throw new ModelAndViewDefiningException(modelAndView);
		}
		else {
			return true;
		}
	}

}

