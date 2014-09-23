/**
 * 
 */
package com.jfootball.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfootball.UserInSession;
import com.jfootball.manager.FootballManager;
import com.jfootball.service.UserService;

/**
 * @author Alessandro Danesi
 *
 * 14/giu/2014 13:14:17
 */

@Controller
public class GenericController 
{
	
	@Autowired
	protected FootballManager footballManager;		
	
	
	protected Logger logger = Logger.getLogger(this.getClass());

	private UserInSession userInSession = UserInSession.getInstance();
	
	protected UserService.MyUserDetails getSecurityUser()
	{
		return userInSession.getSecurityUser();
	}
	
	public String getPassword()
	{
		return userInSession.getPassword();
	}

	public String getUsername()
	{
		return userInSession.getUsername();
	}
	
	
	public void setFootballManager(FootballManager footballManager) {
		this.footballManager = footballManager;
	}	
	
	
}
