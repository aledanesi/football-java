/**
 * 
 */
package com.jfootball.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfootball.UserInSession;
import com.jfootball.manager.CareerManager;
import com.jfootball.manager.ContinentManager;
import com.jfootball.manager.DivisionManager;
import com.jfootball.manager.NationManager;
import com.jfootball.manager.PlayerManager;
import com.jfootball.manager.PositionManager;
import com.jfootball.manager.SeasonManager;
import com.jfootball.manager.TeamManager;
import com.jfootball.manager.UserManager;
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
	protected TeamManager teamManager;

	@Autowired
	protected PlayerManager playerManager;

	@Autowired
	protected CareerManager careerManager;

	@Autowired
	protected SeasonManager seasonManager;
	
	@Autowired
	protected DivisionManager divisionManager;

	@Autowired
	protected NationManager nationManager;
	
	@Autowired
	protected PositionManager positionManager;

	@Autowired
	protected ContinentManager continentManager;
	
	@Autowired
	protected UserManager userManager;	
	
	
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
	
	
	
	

	public void setTeamManager(TeamManager teamManager) {
		this.teamManager = teamManager;
	}

	public void setPlayerManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public void setCareerManager(CareerManager careerManager) {
		this.careerManager = careerManager;
	}

	public void setSeasonManager(SeasonManager seasonManager) {
		this.seasonManager = seasonManager;
	}

	public void setDivisionManager(DivisionManager divisionManager) {
		this.divisionManager = divisionManager;
	}	
	
	public void setNationManager(NationManager nationManager) {
		this.nationManager = nationManager;
	}

	public void setPositionManager(PositionManager positionManager) {
		this.positionManager = positionManager;
	}

	public void setContinentManager(ContinentManager continentManager)
	{
		this.continentManager = continentManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}	
	
	
}
