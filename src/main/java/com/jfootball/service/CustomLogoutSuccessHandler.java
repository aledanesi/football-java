/**
 * 
 */
package com.jfootball.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * @author Alessandro Danesi
 * 
 *         31/mag/2014 20:00:04
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler
{

	protected Logger log = Logger.getLogger(this.getClass());

	private String successLogoutUrl;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
	{

		log.info("--------------------- User Logout --------------------- ");

		String refererUrl = request.getHeader("Referer");
		log.info("Logout from: " + refererUrl);
		log.info("User logout: " + authentication.getName());

		super.setDefaultTargetUrl(this.successLogoutUrl);

		super.onLogoutSuccess(request, response, authentication);
	}

	public void setSuccessLogoutUrl(String successLogoutUrl)
	{
		this.successLogoutUrl = successLogoutUrl;
	}

}