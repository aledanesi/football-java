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
import java.util.HashMap;
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

import com.jfootball.Constant;
import com.jfootball.domain.Career;
import com.jfootball.domain.Division;
import com.jfootball.domain.Nation;
import com.jfootball.domain.Player;
import com.jfootball.domain.Position;
import com.jfootball.domain.SearchPlayer;
import com.jfootball.domain.Season;
import com.jfootball.domain.Team;
import com.jfootball.util.ProjectConstant;
import com.jfootball.web.validator.PlayerValidator;

@Controller
public class PlayerController extends GenericController 
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
	@RequestMapping(value = "/players/view", method = RequestMethod.POST)
	public ModelAndView view(HttpServletRequest request,HttpServletResponse response, @RequestParam("id") String playerId) 
	{
		logger.info("--------------------- Player Controller : view --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.VIEW_PLAYER);

		Long idPlayer = Long.parseLong(playerId);

		Player player = playerManager.getPlayerByID(idPlayer);

		// questo metodo, diversamente dal suo gemello, carica solo alcune informazioni in memoria e non tutto l'albero per problemi di performance.
		List<Team> teamList = new ArrayList<Team>();
		List<Player> playerList = new ArrayList<Player>();		
		
		if (player.getTeam() != null)
		{
			teamList = teamManager.listTeamsByDivisionForView(player.getTeam().getNation().getId(), player.getTeam().getDivision().getId());
			
			playerList = playerManager.listPlayersByTeam(player.getTeam().getId(), player.getTeamCategory());			
		}

		List<Career> careerList = careerManager.listCareer(idPlayer);

		Career career = new Career();
		career.setPlayer(player);		

		view.addObject("career", career);

		view.addObject("player", player);

		view.addObject("playerList", playerList);

		view.addObject("teamList", teamList);
		
		view.addObject("careerList", careerList);
		
		List<Division> divisionList = divisionManager.listDivisionsByNation(new Long(Constant.DEFAULT_NATION));
		view.addObject("divisionList", divisionList);
		
		
		if (player.getTeam() != null) {
			int counter = Integer.parseInt(playerManager.getRank(player.getTeam().getId(), idPlayer));

			HashMap<String, Object> hashSetNext = playerManager.getNextId(player.getTeam().getId(), counter);
			HashMap<String, Object> hashSetPrev = playerManager.getNextId(player.getTeam().getId(), counter - 2);

			view.addObject("nextCounter", hashSetNext.get("counter"));
			view.addObject("nextId", hashSetNext.get("id"));
			view.addObject("nextPlayer", hashSetNext.get("player"));

			view.addObject("prevCounter", hashSetPrev.get("counter"));
			view.addObject("prevId", hashSetPrev.get("id"));
			view.addObject("prevPlayer", hashSetPrev.get("player"));
		}
		
		logger.info("view: VIEW_PLAYER");

		return view;
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/players/listByTeam")
	public ModelAndView listByTeam(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam("team.id") String teamId, 
			@RequestParam("teamCategory") String teamCategory) 
	{
		
		logger.info("--------------------- Player Controller : listByTeam --------------------- ");
		
		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		if (!StringUtils.isEmpty(teamId)) {
			Long idTeam = new Long(teamId);
			Team team = teamManager.getTeamByID(idTeam);
			Player player = new Player();
			List<Player> players = playerManager.listPlayersByTeam(idTeam, teamCategory);

			view.addObject("team", team);
			view.addObject("player", player);
			view.addObject("teamCategory", teamCategory);
			view.addObject("playerList", players);

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
	@RequestMapping(value = "/players/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) 
	{

		logger.info("--------------------- Player Controller : list --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player player = new Player();

		List<Player> playerList = playerManager.listPlayers();

		view.addObject("player", player);
		view.addObject("playerList", playerList);

		logger.info("view: LIST_PLAYER");

		return view;
	}
	


	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/players/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("id") String playerId, 
			@RequestParam("team.id") String teamId) 
	{
		
		logger.info("--------------------- Player Controller : delete --------------------- ");

		Player player = playerManager.getPlayerByID(Long.parseLong(playerId));
		
		playerManager.deletePlayer(Long.parseLong(playerId));
		
		logger.info("Player " + playerId + "deleted");

		return listByTeam(request, response, teamId, player.getTeamCategory());
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping("/players/search")
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("player") SearchPlayer searchPlayer, BindingResult result) 
	{

		logger.info("--------------------- Player Controller : search --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.SEARCH_PLAYER);

		String[] lettere = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z" };
		
		List<String> lettereRicerca = Arrays.asList(lettere);
		
		if (searchPlayer == null)
			searchPlayer = new SearchPlayer();

		view.addObject("searchPlayer", searchPlayer);
		
		String searchType = searchPlayer.getType();
		String hiddenIniziale = searchPlayer.getHiddenIniziale();
		String iniziale = searchPlayer.getIniziale();

		if (!StringUtils.isEmpty(hiddenIniziale)) 
		{
			iniziale = new String(hiddenIniziale);
		}
		
		if (!StringUtils.isEmpty(iniziale)) 
		{
			List<Player> playerList = playerManager.listPlayersByLetter(iniziale, searchType);

			view.addObject("playerList", playerList);
		}

		view.addObject("lettereRicerca", lettereRicerca);

		logger.info("view: SEARCH_PLAYER");

		return view;
	}
	
	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping("/players/buy")
	public ModelAndView buy(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("player") SearchPlayer buyPlayer, BindingResult result
			) 
	{

		logger.info("--------------------- Player Controller : search --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.BUY_PLAYER);

		
		if (buyPlayer == null){
			buyPlayer = new SearchPlayer();
		}	

		buyPlayer.setTeamId(new Long(request.getParameter("teamId")));
		view.addObject("buyPlayer", buyPlayer);
		
		String searchType = buyPlayer.getType();
		String hiddenIniziale = buyPlayer.getHiddenIniziale();
		String iniziale = buyPlayer.getIniziale();

		if (!StringUtils.isEmpty(hiddenIniziale)) 
		{
			iniziale = new String(hiddenIniziale);
		}
		
		if (!StringUtils.isEmpty(iniziale)) 
		{
			List<Player> playerList = playerManager.listPlayersByLetter(iniziale, searchType);

			view.addObject("playerList", playerList);
		}

		logger.info("view: BUY_PLAYER");

		return view;
	}	

	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@RequestMapping(value = "/players/save", method = RequestMethod.POST)
	protected ModelAndView processSubmit(@ModelAttribute("player") Player player, BindingResult result, SessionStatus status, HttpServletRequest request, HttpServletResponse response) 
			throws Exception 
	{
		logger.info("--------------------- Player Controller : processSubmit --------------------- ");

		new PlayerValidator().validate(player, result);
		
		if (result.hasErrors()) 
		{
			request.setAttribute("player", player);

			ModelAndView view = new ModelAndView(ProjectConstant.EDIT_PLAYER);
			
    		logger.info("Player didn't save");
			
			logger.info("view: EDIT_PLAYER");

			return view;
		} 
		else 
		{

			Team team = null;
			if (player.getTeam().getId() != null)
			{
				team = teamManager.getTeamByID(player.getTeam().getId());
				team.setLastUserModify(getUsername());
				team.setLastTimeModify(new Timestamp(System.currentTimeMillis()));				
			}
			
			Team teamOwner = null;
			if (player.getTeamOwner().getId() != null)
				teamOwner = teamManager.getTeamByID(player.getTeamOwner().getId());


			Position position = positionManager.getPositionByID(player.getPosition().getId());
			Nation nation = nationManager.getNationByID(player.getNation().getId());
			
			Nation nation2 =  null;
			if (player.getNation2().getId() != null)
				nation2 = nationManager.getNationByID(player.getNation2().getId());

			player.setPosition(position);
			player.setTeam(team);
			player.setTeamOwner(teamOwner);
			player.setNation(nation);
			player.setNation2(nation2);
			
			// solo nel caso di primo inserimento
			if (player.getId() == null || player.getTeamPrev().getId() == null ) 
				player.setTeamPrev(team);	


			if (player.getImage().length == 0 && player.getId() != null) 
			{
				Player playerDb = playerManager.getPlayerByID(player.getId());

				if (playerDb != null) {
					player.setImage(playerDb.getImage());
				}
				// byte[] image = getFileBytes();
				// team.setImage(image);
			} else {
				if (player.getId() == null) {
					File file = new File(request.getSession().getServletContext().getRealPath("/") + "/images/players/notFound.jpg");
					FileInputStream imageInFile;
					try {
						imageInFile = new FileInputStream(file);
						byte imageData[] = new byte[(int) file.length()];
						imageInFile.read(imageData);
						player.setImage(imageData);
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

					byte[] bytesFile = player.getImage();
					if (bytesFile.length > 0) {

						String tomcat_home = System.getProperty("catalina.base") + "\\webapps\\images\\player\\";
						outputStream = new FileOutputStream(tomcat_home + player.getId() + ".png");
						outputStream.write(bytesFile);
						outputStream.close();
					}
				}
			}

			player.setLastUserModify(getUsername());
			player.setLastTimeModify(new Timestamp(System.currentTimeMillis()));

			
			// togliere i punti di separazione delle migliaia - 24/07/2014
			if (player.getValue() != null && ! "".equals(player.getValue().trim()))
				player.setValue(player.getValue().replace(".", ""));
			else if ("".equals(player.getValue().trim()))
				player.setValue(null);

			if (player.getIncome() != null && ! "".equals(player.getIncome().trim()))
				player.setIncome(player.getIncome().replace(".", ""));
			else if ("".equals(player.getIncome().trim()))
				player.setIncome(null);
			
			/*
			 * if (userImage != null) { byte[] image = getFileBytes();
			 * player.setImage(image); }
			 */

			playerManager.saveOrUpdatePlayer(player);

    		logger.info("Player saved");
    		
			Long teamId = 0L;
    		if (team != null)
    		{
    			teamId = player.getTeam().getId();    			
    			return listByTeam(request, response, teamId.toString(), player.getTeamCategory());
    		}
    		else 
    		{
    			return view(request, response, ""+player.getId());    			
    		}	

		}
	}

	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@RequestMapping(value = "/players/movePlayer", method = RequestMethod.POST)
	protected ModelAndView processSubmitMovePlayer(
			@ModelAttribute("player") Player player, BindingResult result,
			SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("--------------------- Player Controller : processSubmitMovePlayer --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player playerDb = playerManager.getPlayerByID(player.getId());

		Long teamId = 0L;
		
		if (playerDb.getTeam() != null)
			teamId = playerDb.getTeam().getId();			

		else 
			teamId = player.getTeam().getId();						

		
		String teamCategory = playerDb.getTeamCategory();

		playerDb.setTeam(teamManager.getTeamByID(player.getTeam().getId()));

		// gestione per il trasferimento in prestito 
		
		if (player.getOnLoan())
			playerDb.setTeamOwner(teamManager.getTeamByID(player.getTeamOwner().getId()));			
		
		else 
			playerDb.setTeamOwner(teamManager.getTeamByID(player.getTeam().getId()));		
		
		// fine 
		playerDb.setNumber(null);
		playerDb.setEndCareer(null);
		playerDb.setWithoutTeam(null);
		
		playerManager.saveOrUpdatePlayer(playerDb);

		logger.info("Player changed team.");

		if (teamId != null)
		{
			List<Player> playerList = playerManager.listPlayersByTeam(teamId, teamCategory);
			Team teamPrec = teamManager.getTeamByID(teamId);

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
	@RequestMapping(value = "/players/withoutTeam", method = RequestMethod.POST)
	protected ModelAndView processWithoutTeam(
			@ModelAttribute("player") Player player, BindingResult result,
			SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("--------------------- Player Controller : processWithoutTeam --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player playerDb = playerManager.getPlayerByID(player.getId());

		Long teamId = 0L;
		
		if (playerDb.getTeam() != null)
			teamId = playerDb.getTeam().getId();			

		else 
			teamId = player.getTeam().getId();						

		
		String teamCategory = playerDb.getTeamCategory();

		playerDb.setWithoutTeam(true);
		playerDb.setCaptain(null);
		playerDb.setDateContract(null);
		playerDb.setIncome(null);
		playerDb.setNumber(null);
		playerDb.setValue(null);
		playerDb.setOnLoan(null);		
		playerDb.setTeam(null);
		playerDb.setTeamOwner(null);

		playerManager.saveOrUpdatePlayer(playerDb);

		logger.info("Player changed team.");

		List<Player> playerList = playerManager.listPlayersByTeam(teamId, teamCategory);
		Team teamPrec = teamManager.getTeamByID(teamId);

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
	@RequestMapping(value = "/players/endCareer", method = RequestMethod.POST)
	protected ModelAndView processEndCareer(
			@ModelAttribute("player") Player player, BindingResult result,
			SessionStatus status, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("--------------------- Player Controller : processWithoutTeam --------------------- ");

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_PLAYER);

		Player playerDb = playerManager.getPlayerByID(player.getId());

		Long teamId = 0L;
		
		if (playerDb.getTeam() != null)
			teamId = playerDb.getTeam().getId();			

		else if (player.getTeam() != null)
			teamId = player.getTeam().getId();						

		
		String teamCategory = playerDb.getTeamCategory();

		// gestione fine carriera
		playerDb.setEndCareer(true);
		playerDb.setWithoutTeam(false);		
		playerDb.setCaptain(null);
		playerDb.setDateContract(null);
		playerDb.setIncome(null);
		playerDb.setNumber(null);
		playerDb.setValue(null);
		playerDb.setOnLoan(null);		
		playerDb.setTeam(null);
		playerDb.setTeamOwner(null);
		playerDb.setTeamPrev(null);		
		
		playerManager.saveOrUpdatePlayer(playerDb);

		logger.info("Player changed team.");

		List<Player> playerList = playerManager.listPlayersByTeam(teamId, teamCategory);
		Team teamPrec = teamManager.getTeamByID(teamId);

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
	@ModelAttribute("nationList")
	protected List<Nation> populateNations(HttpServletRequest request)
			throws Exception {
		List<Nation> nationList = nationManager.listNations();
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
	@ModelAttribute("seasonYearList")
	protected List<Season> populateSeasons(HttpServletRequest request) throws Exception 
	{
		List<Season> seasonYearList = seasonManager.listSeason();
		return seasonYearList;
	}	

}