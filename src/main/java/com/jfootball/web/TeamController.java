package com.jfootball.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.jfootball.Constant;
import com.jfootball.domain.Continent;
import com.jfootball.domain.Division;
import com.jfootball.domain.Nation;
import com.jfootball.domain.Team;
import com.jfootball.service.UserService;
import com.jfootball.util.ProjectConstant;
import com.jfootball.web.validator.TeamValidator;

/**
 * @author Alessandro Danesi
 *
 * 15/mar/2014 15:18:17
 */
@Controller
public class TeamController extends GenericController
{


	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_
	 * 
	 * 
	 * 						BINDER
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */
	
	/** 
	 * ----------------initBinder()----------------
	 * @see com.jfootball.FileUpload#initBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TeamValidator());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }	

	
	
	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_
	 * 
	 * 
	 * 						METODI DI ACTION
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */
	
	/**
	 * ----------------formBackingObject()----------------
	 * @param request
	 * @param response
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@RequestMapping(value = "/teams/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="nation.continent.id", required=false, defaultValue=Constant.DEFAULT_CONTINENT) Long continentId,
			@RequestParam(value="nation.id", required=false, defaultValue=Constant.DEFAULT_NATION) Long nationId,
			@RequestParam(value="division.id", required=false, defaultValue=Constant.DEFAULT_DIVISION) Long divisionId)
	{

		logger.info("--------------------- Team Controller : list --------------------- ");
		
		ModelAndView view = new ModelAndView(ProjectConstant.LIST_TEAM);

		HttpSession session = request.getSession();		
		
		logger.info("User in session: " + (session.getAttribute(Constant.USER_IN_SESSION) != null));
				

		if (session.getAttribute(Constant.USER_IN_SESSION) == null)
		{
			UserService.MyUserDetails user = getSecurityUser();

			session.setAttribute(Constant.USER_IN_SESSION, user);

			logger.info("'" + getSecurityUser().getUsername() + "' is in session");
		}	
		
		// imposto la nazione nella ricerca per la lista dei team 
		// [DEFAULT: ITALIA]
		Team team = new Team(nationId, divisionId);
		view.addObject("team", team);
		
		List<Team> teamList = teamManager.listTeamsByDivision(nationId, divisionId);				
		view.addObject("teamList", teamList);

		logger.info("Teams loaded: " + teamList.size());

		List<Division> divisionList = divisionManager.listDivisionsByNation(nationId);
		view.addObject("divisionList", divisionList);

		Nation nation = nationManager.getNationByID(team.getNation().getId());

		System.out.println("nation: " + nation);
		view.addObject("nation", nation);

		
		logger.info("view: LIST_TEAM");

		return view;
	}


	/**
	 * ----------------formBackingObject()----------------
	 * @param request
	 * @param response
	 * @param teamId
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@RequestMapping(value = "/teams/delete", method = RequestMethod.GET)	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("id") String teamId, 
			@RequestParam(value="nation.continent.id", required=false, defaultValue=Constant.DEFAULT_CONTINENT) Long continentId,
			@RequestParam(value="nation.id", required=false, defaultValue=Constant.DEFAULT_NATION) Long nationId,
			@RequestParam(value="division.id", required=false, defaultValue=Constant.DEFAULT_DIVISION) Long divisionId)
	{

		logger.info("--------------------- Team Controller : delete --------------------- ");

		teamManager.deleteTeam(Long.parseLong(teamId));

		logger.info("Team " + teamId + "deleted");

		return list(request, response, continentId, nationId, divisionId);
	}


	/**
	 * ----------------onSubmit()----------------
	 * @param team
	 * @param result
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teams/save", method = RequestMethod.POST)
	protected ModelAndView processSubmit(@ModelAttribute("team") Team team, 
											BindingResult result, 
											SessionStatus status,
											HttpServletRequest request, 
											HttpServletResponse response) throws Exception
	{	
		logger.info("--------------------- Team Controller : processSubmit --------------------- ");

		new TeamValidator().validate(team, result);
				
        if (result.hasErrors()) 
        {
    		List<Division> divisionList = divisionManager.listDivisions();
    		List<Nation> nationList = nationManager.listNations();
    		
    		request.setAttribute("team", team);
    		request.setAttribute("divisionList", divisionList);
    		request.setAttribute("nationList", nationList);
    		
    		ModelAndView view  = new ModelAndView(ProjectConstant.EDIT_TEAM);

    		logger.info("Team didn't save");

			logger.info("view: EDIT_TEAM");

    		return view;
        }
        else 
        {
    		if (team.getImage().length == 0 && team.getId() != null)
    		{
    			Team teamDb = teamManager.getTeamByID(team.getId());
    			if (teamDb != null )
    			{
        			team.setImage(teamDb.getImage());
    			}
    			//byte[] image = getFileBytes();
    			//team.setImage(image);
    		}
    		else 
    		{
        		if (team.getId() == null)
        		{
    				File file = new File(request.getSession().getServletContext().getRealPath("/") + "/images/players/notFound.jpg");
    				FileInputStream imageInFile;
    				try {
    					imageInFile = new FileInputStream(file);
    					byte imageData[] = new byte[(int) file.length()];            
    					imageInFile.read(imageData);	
    					team.setImage(imageData);
    					imageInFile.close();
    				} catch (FileNotFoundException e) {
    					//e.printStackTrace();
    					throw e;
    				}  catch (IOException e) {
    					//e.printStackTrace();
    					throw e;
    				}              			
        		}
        		else 
        		{
        			OutputStream outputStream = null;
        			
        			byte[] bytesFile = team.getImage();
        			if (bytesFile.length > 0) {
        				
        				String tomcat_home = System.getProperty("catalina.base") + "\\webapps\\images\\team\\";
        				outputStream = new FileOutputStream(tomcat_home + team.getId() + ".png");
    					outputStream.write(bytesFile);
    					outputStream.close();
        			}
        		}
    		}
    		
    		team.setLastUserModify(getUsername());
    		team.setLastTimeModify(new Timestamp(System.currentTimeMillis()));    		
    		

    		// frammento di codice per il ritorno alla corretta divisione dopo il salvataggio del team
    		Long divisionReturn = 0L;
    		Team teamDB = null;

    		if (team.getId() != null)
    		{
    			teamDB = teamManager.getTeamByID(team.getId());    			
    			divisionReturn = teamDB.getDivision().getId();
    		}
    		else 
    		{
    			divisionReturn = team.getDivision().getId();    			
    		}
    		// fine modifica (03-05-2014)
    		
    		teamManager.saveOrUpdateTeam(team);
    		
    		logger.info("Team saved");
    		
    		return list(request, response, team.getNation().getContinent().getId(), team.getNation().getId(), divisionReturn);

        }		
		
	}

	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */	
	@ModelAttribute("nationList")
	protected List<Nation> populateNations(HttpServletRequest request)
			throws Exception 
	{
		
		Long continentId = 1L;
		if(request.getParameter("nation.continent.id") != null)
			continentId = new Long(request.getParameter("nation.continent.id"));
		
		UserService.MyUserDetails user = getSecurityUser();

		List<GrantedAuthority> listaRuoli = user.getAuthorities();

		List<Nation> nationList = null;

		if (listaRuoli.contains(Constant.ADMIN))
			nationList = nationManager.listNationsFromContinent(continentId);
		else
			nationList = nationManager.listNationsTournament();
		
		return nationList;
	}
	
	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */	
	@ModelAttribute("continentList")
	protected List<Continent> populateContinents(HttpServletRequest request)
			throws Exception 
	{
		
		UserService.MyUserDetails user = getSecurityUser();

		List<GrantedAuthority> listaRuoli = user.getAuthorities();

		List<Continent> continentList = null;

		if (listaRuoli.contains(Constant.ADMIN))
			continentList = continentManager.listContinents();
		else
			continentList = new ArrayList<Continent>();
		
		return continentList;
	}	
	

}