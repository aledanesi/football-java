package com.jfootball;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.jfootball.service.UserService;

/**
 * @author Alessandro Danesi
 *
 * 15/feb/2014 23:31
 */
public class UserInSession 
{

	private static UserInSession instance;
	
	private UserInSession()
	{
	}

	public static UserInSession getInstance()
	{
		if (instance == null)
		{
			instance = new UserInSession();
		}
		return instance;
	}
	
	public String getPassword()
	{
		UserService.MyUserDetails securityUser = getSecurityUser();
		return securityUser.getPassword();
	}

	public String getUsername()
	{
		UserService.MyUserDetails securityUser = getSecurityUser();
		return securityUser.getUsername();
	}

	public UserService.MyUserDetails getSecurityUser()
	{
		// utente non ancora autorizzato
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof String)
			return null;
		
		UserService.MyUserDetails securityUser = (UserService.MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return securityUser;
	}
}
