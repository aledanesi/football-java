/**
 * 
 */
package com.jfootball.web;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.jfootball.UserInSession;
import com.jfootball.business.BusinessDelegate;
import com.jfootball.domain.BasePerson;
import com.jfootball.service.UserService;

/**
 * @author Alessandro Danesi
 *
 * 14/giu/2014 13:14:17
 */

@Controller
public class GenericController 
{
	
	@Autowired(required=true)    
	@Qualifier(value="businessDelegate")
	protected BusinessDelegate businessDelegate;		

	
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
	
	/**
	 * @param player
	 */
	protected void formatGrossWeeklySalary(BasePerson person)
	{
		String str = null;
		
		if (person.getGrossWeeklySalary() != null && !"".equals(person.getGrossWeeklySalary().trim()))
			str = person.getGrossWeeklySalary().replace(".", "");
		
		person.setGrossWeeklySalary(str);
	}
	
	/**
	 * @param player
	 */
	protected void formatNetAnnualSalary(BasePerson person)
	{
		String str = null;
		
		if (person.getNetAnnualSalary() != null && !"".equals(person.getNetAnnualSalary().trim()))
			str = person.getNetAnnualSalary().replace(".", "");
		
		person.setNetAnnualSalary(str);
	}	
	
	protected void creaAlfabetoPerRicerca(ModelAndView view) {
		String[] lettere = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		
		List<String> lettereRicerca = Arrays.asList(lettere);
		view.addObject("lettereRicerca", lettereRicerca);
	}	
	
	
	/*public void setFootballManager(FootballManager footballManager) {
		this.footballManager = footballManager;
	}*/

	public void setBusinessDelegate(BusinessDelegate businessDelegate)
	{
		this.businessDelegate = businessDelegate;
	}	
	
}
