package com.jfootball.web;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jfootball.domain.Career;
import com.jfootball.domain.Player;
import com.jfootball.domain.Season;
import com.jfootball.util.ProjectConstant;
import com.jfootball.web.validator.CareerValidator;


@Controller
public class CareerController extends GenericController 
{


	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * 
	 * 						METODI DI ACTION
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 */
	
	
	@RequestMapping(value = "/careers/save", method = RequestMethod.POST)
	protected ModelAndView processSubmit(@ModelAttribute("career") Career career, 
											BindingResult result, 
											SessionStatus status,
											HttpServletRequest request, 
											HttpServletResponse response) throws Exception
	{	
		ModelAndView view = new ModelAndView(ProjectConstant.EDIT_CAREER);
		
		new CareerValidator().validate(career, result);
        if (result.hasErrors()) 
        {
    		
    		view.addObject("career", career);

    		return view;
        }
        else 
        {    	
        	
    		Player player = (Player)businessDelegate.getEntityByID(career.getPlayer().getId(), "PLAYER");
    		player.setLastUserModify(getUsername());
    		player.setLastTimeModify(new Timestamp(System.currentTimeMillis()));
    		career.setPlayer(player);
        	
			// upper case della squadra
    		career.setSquadra(career.getSquadra().toUpperCase());
    		career.setSerie(career.getSerie().toUpperCase());    		
    		
			// aggiorno la modifica del team
    		career.setLastUserModify(getUsername());
    		career.setLastTimeModify(new Timestamp(System.currentTimeMillis()));
    		
    		businessDelegate.saveEntity(career, "CAREER");

    		return loadViewPlayer(player);
        }
	}
	
	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/careers/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("id") String careerId, 
			@RequestParam("player.id") String playerId) 
	{
		businessDelegate.deleteEntity(Long.parseLong(careerId), "CAREER");
		
		Player player = (Player)businessDelegate.getEntityByID(Long.parseLong(playerId), "PLAYER");

		return loadViewPlayer(player);
	}	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/careers/get_team_list", method = RequestMethod.GET, headers="Accept=*/*")    
	public @ResponseBody List<String> getTeamList(@RequestParam("term") String query) 
	{        
		List<String> teamList = (List<String>)businessDelegate.getEntitiesByParams(query, "TEAM");
		
		return teamList;    
	}	



	/**
	 * @param player
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ModelAndView loadViewPlayer(Player player) 
	{
		ModelAndView view = new ModelAndView(ProjectConstant.VIEW_PLAYER);
		
		List<Career> careerList = (List<Career>)businessDelegate.getEntitiesByID(player.getId(), "CAREER");
		
		List<Season> seasonYearList = (List<Season>)businessDelegate.getEntities("SEASON");
		
		Career career = new Career();
		career.setPlayer(player);		

		view.addObject("career", career);				
		view.addObject("player", player);
		view.addObject("careerList", careerList);
		view.addObject("seasonYearList", seasonYearList);
		return view;
	}
			
		
}