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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.jfootball.domain.Nation;
import com.jfootball.domain.Player;
import com.jfootball.domain.Position;
import com.jfootball.domain.SearchPlayer;
import com.jfootball.domain.Season;
import com.jfootball.domain.Staff;
import com.jfootball.domain.Team;
import com.jfootball.util.ProjectConstant;
import com.jfootball.web.validator.ManagerValidator;

@Controller
public class StaffController extends GenericController 
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
        //binder.setValidator(new PlayerValidator());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }			
	
	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * 
	 * 						METODI DI ACTION
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 */
	

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/staffs/view",  method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView view(HttpServletRequest request,HttpServletResponse response, @RequestParam("id") String id) 
	{
		logger.info("--------------------- Player Controller : view --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.VIEW_STAFF);

		Long staffId = Long.parseLong(id);

		Staff staff = (Staff)businessDelegate.getEntityByID(staffId, "STAFF");

		view.addObject("staff", staff);

		logger.info("view: VIEW_STAFF");

		return view;
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/staffs/listByTeam", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView listByTeam(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam("team.id") String teamId, 
			@RequestParam("teamCategory") String teamCategory) 
	{
		
		logger.info("--------------------- Player Controller : listByTeam --------------------- ");
		
		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		if (!StringUtils.isEmpty(teamId)) {
			Long idTeam = new Long(teamId);
			Team team = (Team)businessDelegate.getEntityByID(idTeam, "TEAM");
			Player player = new Player();
			List<Player> players = (List<Player>)businessDelegate.getEntitiesByIDAndDesc(idTeam, teamCategory, "PLAYER");
			
			Staff staff = (Staff)businessDelegate.getEntityBySecondId(idTeam, "STAFF");
			List<Staff> staffList = new ArrayList<Staff>(); 
			
			if (staff != null)
				staffList.add(staff);

			view.addObject("team", team);
			view.addObject("player", player);
			view.addObject("staff", new Staff());
			view.addObject("staffList", staffList);			
			view.addObject("teamCategory", teamCategory);
			view.addObject("playerList", players);

			/// SERVE ANCORA??? SI PUO' TOGLIERE??
			SearchPlayer buyPlayer = new SearchPlayer();
			buyPlayer.setTeamId(idTeam);
			view.addObject("buyPlayer", buyPlayer);		
			///////////

			logger.info("Players loaded: " + players.size());
		}

		logger.info("view: LIST_PLAYER");

		return view;
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/staffs/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("id") String staffId, 
			@RequestParam("team.id") String teamId) 
	{
		
		logger.info("--------------------- Player Controller : delete --------------------- ");

		businessDelegate.deleteEntity(Long.parseLong(staffId), "STAFF");
		
		logger.info("Manager " + staffId + "deleted");

		return listByTeam(request, response, teamId, "1");
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/staffs/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("player") SearchPlayer searchManager, BindingResult result) 
	{

		logger.info("--------------------- Player Controller : search --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.SEARCH_STAFF);

		String[] lettere = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		
		List<String> lettereRicerca = Arrays.asList(lettere);
		
		if (searchManager == null)
			searchManager = new SearchPlayer();

		view.addObject("searchManager", searchManager);
		
		String searchType = searchManager.getType();
		String hiddenIniziale = searchManager.getHiddenIniziale();
		String iniziale = searchManager.getIniziale();

		if (!StringUtils.isEmpty(hiddenIniziale)) 
		{
			iniziale = new String(hiddenIniziale);
		}
		
		if (!StringUtils.isEmpty(iniziale)) 
		{
			List<Staff> staffList = (List<Staff>)businessDelegate.getEntitiesByParams(iniziale, searchType, "STAFF");

			view.addObject("staffList", staffList);
		}

		view.addObject("lettereRicerca", lettereRicerca);

		logger.info("view: SEARCH_STAFF");

		return view;
	}
	
	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@RequestMapping(value = "/staffs/save", method = RequestMethod.POST)
	protected ModelAndView processSave(@ModelAttribute("staff") Staff staff, BindingResult result, 
			SessionStatus status, HttpServletRequest request, HttpServletResponse response) 
			throws Exception 
	{
		logger.info("--------------------- Player Controller : processSubmit --------------------- ");

		new ManagerValidator().validate(staff, result);
		
		if (result.hasErrors()) 
		{
			request.setAttribute("staff", staff);

			ModelAndView view = new ModelAndView(ProjectConstant.EDIT_STAFF);
			
    		logger.info("Player didn't save");
			
			logger.info("view: EDIT_STAFF");

			return view;
		} 
		else 
		{

			Team team = null;
			if (staff.getTeam().getId() != null)
			{
				team =  (Team)businessDelegate.getEntityByID(staff.getTeam().getId(), "TEAM");
				//team.setLastUserModify(getUsername());
				//team.setLastTimeModify(new Timestamp(System.currentTimeMillis()));				
			}

			Nation nationality = (Nation)businessDelegate.getEntityByID(staff.getNationality().getId(), "NATION");
			
			Nation nationality2 =  null;
			if (staff.getNationality2().getId() != null)
				nationality2 = (Nation)businessDelegate.getEntityByID(staff.getNationality2().getId(), "NATION");

			staff.setTeam(team);
			staff.setNationality(nationality);
			staff.setNationality2(nationality2);
			
			// solo nel caso di primo inserimento
			if (staff.getId() == null || staff.getTeamPrev().getId() == null ) 
				staff.setTeamPrev(team);	
			
			
			staff.setLastUserModify(getUsername());
			staff.setLastTimeModify(new Timestamp(System.currentTimeMillis()));

			// togliere i punti di separazione delle migliaia - 24/07/2014
			formatGrossWeeklySalary(staff);
			
			formatNetAnnualSalary(staff);

			businessDelegate.saveEntity(staff, "STAFF");

    		logger.info("Staff saved");

    		// GESTIONE IMMAGINE
			if (staff.getImage().length == 0 && staff.getId() != null) 
			{
				Staff staffDb = (Staff)businessDelegate.getEntityByID(staff.getId(), "STAFF");

				if (staffDb != null) {
					staff.setImage(staffDb.getImage());
				}
				// byte[] image = getFileBytes();
				// team.setImage(image);
			} else {
				if (staff.getId() == null) {
					File file = new File(request.getSession().getServletContext().getRealPath("/") + "/images/players/notFound.jpg");
					FileInputStream imageInFile;
					try {
						imageInFile = new FileInputStream(file);
						byte imageData[] = new byte[(int) file.length()];
						imageInFile.read(imageData);
						staff.setImage(imageData);
						imageInFile.close();
					} catch (FileNotFoundException e) {
						// e.printStackTrace();
						throw e;
					} catch (IOException e) {
						// e.printStackTrace();
						throw e;
					}
				} else {
					OutputStream outputStream = null;

					byte[] bytesFile = staff.getImage();
					if (bytesFile.length > 0) {

						String tomcat_home = System.getProperty("catalina.base") + "\\webapps\\images\\staff\\";
						File file = new File(tomcat_home);
						
						if (! file.exists())
							file.mkdir();
						
						outputStream = new FileOutputStream(tomcat_home + staff.getId() + ".png");
						outputStream.write(bytesFile);
						outputStream.close();
					}
				}
			}
    		
    		
			/*Long teamId = 0L;
    		if (team != null)
    		{
    			teamId = staff.getTeam().getId();    			
    			return listByTeam(request, response, teamId.toString(), "1");
    		}
    		else 
    		{
    			return view(request, response, ""+staff.getId());    			
    		}	*/

			Long teamId = staff.getTeam().getId();    			
			return listByTeam(request, response, teamId.toString(), "1");

		}
	}

	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/staffs/moveManager", method = RequestMethod.POST)
	protected ModelAndView processMovePlayer(
			@ModelAttribute("player") Player player, BindingResult result,
			SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("--------------------- Player Controller : processSubmitMovePlayer --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player playerDb = (Player)businessDelegate.getEntityByID(player.getId(), "PLAYER");

		Long teamId = 0L;
		
		if (playerDb.getTeam() != null)
			teamId = playerDb.getTeam().getId();			

		else 
			teamId = player.getTeam().getId();						

		
		String teamBranch = playerDb.getTeamBranch();

		playerDb.setTeam((Team)businessDelegate.getEntityByID(player.getTeam().getId(), "TEAM"));

		// gestione per il trasferimento in prestito 
		
		if (player.getOnLoan())
			playerDb.setTeamOwner((Team)businessDelegate.getEntityByID(player.getTeamOwner().getId(), "TEAM"));			
		
		else 
			playerDb.setTeamOwner((Team)businessDelegate.getEntityByID(player.getTeam().getId(), "TEAM"));		
		
		// fine 
		playerDb.setNumber(null);
		playerDb.setRetired(false);
		playerDb.setUnemployed(false);
		playerDb.setCaptain(false);
		
		playerDb.setNetAnnualSalary(null);
		playerDb.setGrossWeeklySalary(null);
		
		businessDelegate.saveEntity(playerDb, "PLAYER");

		logger.info("Player changed team.");

		if (teamId != null)
		{
			List<Player> playerList = (List<Player>)businessDelegate.getEntitiesByIDAndDesc(teamId, teamBranch, "PLAYER");
			Team teamPrec = (Team)businessDelegate.getEntityByID(teamId, "TEAM");

			view.addObject("playerList", playerList);
			view.addObject("team", teamPrec);
			
			logger.info("view: LIST_PLAYER");

			return view;
		}
		else 
		{
			return view(request, response, ""+player.getId());    			
		}	
	}
	
	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/staffs/withoutTeam")
	protected ModelAndView processWithoutTeam(
			@ModelAttribute("player") Player player, BindingResult result,
			SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("--------------------- Player Controller : processWithoutTeam --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player playerDb = (Player)businessDelegate.getEntityByID(player.getId(), "PLAYER");

		Long teamId = 0L;
		
		if (playerDb.getTeam() != null)
			teamId = playerDb.getTeam().getId();			

		else 
			teamId = player.getTeam().getId();						

		
		String teamBranch = playerDb.getTeamBranch();

		playerDb.setUnemployed(true);
		playerDb.setCaptain(false);
		playerDb.setContractUntil(null);

		playerDb.setNetAnnualSalary(null);
		playerDb.setGrossWeeklySalary(null);

		playerDb.setNumber(null);
		playerDb.setCost(null);
		playerDb.setOnLoan(false);		
		playerDb.setTeam(null);
		playerDb.setTeamOwner(null);

		businessDelegate.saveEntity(playerDb, "PLAYER");

		logger.info("Player changed team.");

		List<Player> playerList = (List<Player>)businessDelegate.getEntitiesByIDAndDesc(teamId, teamBranch, "PLAYER");
		Team teamPrec = (Team)businessDelegate.getEntityByID(teamId, "TEAM");

		view.addObject("playerList", playerList);
		view.addObject("team", teamPrec);
		
		logger.info("view: LIST_PLAYER");

		return view;

	}	

	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/staffs/endCareer", method = RequestMethod.POST)
	protected ModelAndView processEndCareer(
			@ModelAttribute("player") Player player, BindingResult result,
			SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("--------------------- Player Controller : processWithoutTeam --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player playerDb = (Player)businessDelegate.getEntityByID(player.getId(), "PLAYER");

		Long teamId = 0L;
		
		if (playerDb.getTeam() != null)
			teamId = playerDb.getTeam().getId();			

		else if (player.getTeam() != null)
			teamId = player.getTeam().getId();						

		
		String teamBranch = playerDb.getTeamBranch();

		// gestione fine carriera
		playerDb.setRetired(true);
		playerDb.setUnemployed(false);		
		playerDb.setCaptain(false);
		playerDb.setContractUntil(null);

		playerDb.setNetAnnualSalary(null);
		playerDb.setGrossWeeklySalary(null);
		
		playerDb.setNumber(null);
		playerDb.setCost(null);
		playerDb.setOnLoan(false);		
		playerDb.setTeam(null);
		playerDb.setTeamOwner(null);
		playerDb.setTeamPrev(null);		
		
		businessDelegate.saveEntity(playerDb, "PLAYER");

		logger.info("Player changed team.");

		List<Player> playerList = (List<Player>)businessDelegate.getEntitiesByIDAndDesc(teamId, teamBranch, "PLAYER");
		Team teamPrec = (Team)businessDelegate.getEntityByID(teamId, "TEAM");

		view.addObject("playerList", playerList);
		view.addObject("team", teamPrec);
		
		logger.info("view: LIST_PLAYER");

		return view;

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
	
		List<Nation> nationList = (List<Nation>)businessDelegate.getEntities("NATION");
		return nationList;
	}

	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */
	@ModelAttribute("ruoliList")
	protected List<Position> populateRuoli(HttpServletRequest request)
			throws Exception {
		List<Position> positionList = new ArrayList<Position>();
		Position porta = new Position();
		porta.setDescRuolo("Porta");
		porta.setCodRuolo("01");

		Position difesa = new Position();
		difesa.setDescRuolo("Difesa");
		difesa.setCodRuolo("02");

		Position centrocampo = new Position();
		centrocampo.setDescRuolo("Centrocampo");
		centrocampo.setCodRuolo("03");

		Position attacco = new Position();
		attacco.setDescRuolo("Attacco");
		attacco.setCodRuolo("04");

		positionList.add(porta);
		positionList.add(difesa);
		positionList.add(centrocampo);
		positionList.add(attacco);

		return positionList;
	}
	
	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */
	@SuppressWarnings("unchecked")
	@ModelAttribute("seasonYearList")
	protected List<Season> populateSeasons(HttpServletRequest request) throws Exception 
	{
		List<Season> seasonYearList = (List<Season>)businessDelegate.getEntities("SEASON");
		return seasonYearList;
	}	

}