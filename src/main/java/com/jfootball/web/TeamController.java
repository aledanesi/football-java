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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
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
import com.jfootball.domain.user.UserBean;
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
	
	@Autowired
	private MessageSource messageSource;


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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/teams/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="nation.continent.id", required=false, defaultValue=Constant.DEFAULT_CONTINENT) Long continentId,
			@RequestParam(value="nation.id", required=false, defaultValue=Constant.DEFAULT_NATION) Long nationId,
			@RequestParam(value="division.id", required=false, defaultValue=Constant.DEFAULT_DIVISION) Long divisionId)
	{

		logger.info("--------------------- Team Controller : list --------------------- ");
		
		ModelAndView view = new ModelAndView(ProjectConstant.LIST_TEAM);
		
		creaAlfabetoPerRicerca(view);		

		HttpSession session = request.getSession();		
		
		logger.info("User in session: " + (session.getAttribute(Constant.USER_IN_SESSION) != null));
				

		if (session.getAttribute(Constant.USER_IN_SESSION) == null)
		{
			UserService.MyUserDetails user = getSecurityUser();
			
			if (user != null)
			{
				session.setAttribute(Constant.USER_IN_SESSION, user);

				logger.info("'" + getSecurityUser().getUsername() + "' is in session");
			}

		}	
		
		// imposto la nazione nella ricerca per la lista dei team 
		// [DEFAULT: ITALIA]
		
		Team team = new Team(continentId, nationId, divisionId);
		view.addObject("team", team);
		
		UserBean user = new UserBean();
		view.addObject("user", user);		
		
		List<Team> teamList = (List<Team>)businessDelegate.getEntitiesByIDs(nationId, divisionId, "TEAM");				
		view.addObject("teamList", teamList);
		
		Long playersCount = businessDelegate.getIntegerByTwoParams(nationId, divisionId, "TEAM");	
		view.addObject("playersCount", playersCount);

		logger.info("Teams loaded: " + teamList.size());

		List<Division> divisionList = (List<Division>)businessDelegate.getEntitiesBySecondID(nationId, "DIVISION");
		view.addObject("divisionList", divisionList);

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

		businessDelegate.deleteEntity(Long.parseLong(teamId), "TEAM");

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
	@SuppressWarnings("unchecked")	
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
    		List<Division> divisionList = (List<Division>)businessDelegate.getEntities("DIVISION");
    		List<Nation> nationList = (List<Nation>)businessDelegate.getEntities("NATION");
    		
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
    			Team teamDb = (Team)businessDelegate.getEntityByID(team.getId(), "TEAM");
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
        				
						File file = new File(tomcat_home);
						
						if (! file.exists())
							file.mkdir();        				
        				
        				outputStream = new FileOutputStream(tomcat_home + team.getId() + ".png");
    					outputStream.write(bytesFile);
    					outputStream.close();
        			}
        		}
    		}
    		
    		team.setManager(null);
    		
    		team.setLastUserModify(getUsername());
    		team.setLastTimeModify(new Timestamp(System.currentTimeMillis()));    		
    		

    		// frammento di codice per il ritorno alla corretta divisione dopo il salvataggio del team
    		Long divisionReturn = 0L;
    		Team teamDB = null;

    		if (team.getId() != null)
    		{
    			teamDB = (Team)businessDelegate.getEntityByID(team.getId(), "TEAM");  			
    			divisionReturn = teamDB.getDivision().getId();
    		}
    		else 
    		{
    			divisionReturn = team.getDivision().getId();    			
    		}
    		// fine modifica (03-05-2014)
    		
    		businessDelegate.saveEntity(team, "TEAM");
    		
    		logger.info("Team saved");
    		
			Nation nation = (Nation)businessDelegate.getEntityByID(team.getNation().getId(), "NATION");
    		
    		return list(request, response, nation.getContinent().getId(), nation.getId(), divisionReturn);

        }		
		
	}

	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */	
	@SuppressWarnings("unchecked")		
	@ModelAttribute("nationList")
	protected List<Nation> populateNations(HttpServletRequest request)
			throws Exception 
	{
		
		Long continentId = 1L;
		if(request.getParameter("nation.continent.id") != null)
			continentId = new Long(request.getParameter("nation.continent.id"));
		
		UserService.MyUserDetails user = getSecurityUser();
		
		List<Nation> nationList = null;

		if (user != null)
		{
			List<GrantedAuthority> listaRuoli = user.getAuthorities();

			if (listaRuoli.contains(Constant.ADMIN))
				nationList = (List<Nation>)businessDelegate.getEntitiesBySecondID(continentId, "NATION");
			else
				nationList = (List<Nation>)businessDelegate.getOtherEntities("NATION");
		}
		else
			nationList = (List<Nation>)businessDelegate.getOtherEntities("NATION");

		
		return nationList;
	}
	
	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */	
	@SuppressWarnings("unchecked")		
	@ModelAttribute("continentList")
	protected List<Continent> populateContinents(HttpServletRequest request)
			throws Exception 
	{
		
		UserService.MyUserDetails user = getSecurityUser();
		
		List<Continent> continentList = null;
		if (user != null)
		{
			List<GrantedAuthority> listaRuoli = user.getAuthorities();

			if (listaRuoli.contains(Constant.ADMIN))
				continentList = (List<Continent>)businessDelegate.getEntities("CONTINENT");
			else
				continentList = new ArrayList<Continent>();		
		}		

		else
			continentList = new ArrayList<Continent>();	
		
		return continentList;
	}	
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}	
	

}