/**
 * 
 */
package com.jfootball.manager.impl;

import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfootball.domain.Career;
import com.jfootball.domain.Continent;
import com.jfootball.domain.Division;
import com.jfootball.domain.Nation;
import com.jfootball.domain.Player;
import com.jfootball.domain.Position;
import com.jfootball.domain.Season;
import com.jfootball.domain.Team;
import com.jfootball.domain.user.UserBean;
import com.jfootball.manager.CareerManager;
import com.jfootball.manager.ContinentManager;
import com.jfootball.manager.DivisionManager;
import com.jfootball.manager.FootballManager;
import com.jfootball.manager.NationManager;
import com.jfootball.manager.PlayerManager;
import com.jfootball.manager.PositionManager;
import com.jfootball.manager.SeasonManager;
import com.jfootball.manager.TeamManager;
import com.jfootball.manager.UserManager;

/**
 * @author Alessandro Danesi
 *
 * 12/set/201422:10:24
 */

@Service("footballManager")
@RemoteProxy(name = "footballManager")
public class FootballManagerImpl extends GenericManager implements FootballManager
{
	@Autowired
	private CareerManager careerManager;

	@Autowired
	private ContinentManager continentManager;

	@Autowired
	private DivisionManager divisionManager;
	
	@Autowired
	private NationManager nationManager;
	

	@Autowired
	private PlayerManager playerManager;
	
	@Autowired

	private PositionManager positionManager;

	@Autowired
	private SeasonManager seasonManager;
	
	@Autowired
	private TeamManager teamManager;
	
	@Autowired
	private UserManager userManager;
	
	
	/******************************************************************************************************************************
	 * 
	 * 											C   A   R   E   E   R
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param idCareer
	 * @return
	 */
	@RemoteMethod
	public Career getCareerByID(Long idCareer)
	{
		return careerManager.getCareerByID(idCareer);
	}
		
	/**
	 * @param idCareer
	 * @return
	 */
	public List<Career> getCareers(Long idCareer)
	{
		return careerManager.listCareer(idCareer);
	}
	
	/**
	 * @param career
	 */
	public void saveCareer(Career career)
	{
		careerManager.saveOrUpdateCareer(career);
	}
	
	
	/**
	 * @param idCareer
	 */
	public void deleteCareer(Long idCareer)		
	{
		careerManager.deleteCareer(idCareer);
	}
	

	/******************************************************************************************************************************
	 * 
	 * 											C   O   N   T   I   N    E    N    T
	 * 
	 ******************************************************************************************************************************/

	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */	
	public void saveContinent(Continent continent)
	{
		continentManager.saveOrUpdateContinent(continent);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Continent> getContinents()
	{
		return continentManager.listContinents();
	}

	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Continent getContinent(Long continentId)
	{
		return continentManager.getContinentByID(continentId);		
	}

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteContinent(Long continentId)
	{
		continentManager.deleteContinent(continentId);				
	}
	
	/******************************************************************************************************************************
	 * 
	 * 											D   I   V   I   S   I   O   N
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @return
	 */
	public List<Division> getDivisions()
	{
		return divisionManager.listDivisions();
	}

	/**
	 * @param divisionId
	 * @return
	 */
	public Division getDivision(Long divisionId)
	{
		return divisionManager.getDivisionByID(divisionId);
	}

	/**
	 * @param nationId
	 * @return
	 */
	@RemoteMethod
	public List<Division> getDivisionsByNation(Long nationId)
	{
		return divisionManager.listDivisionsByNation(nationId);
	}

	/**
	 * @param division
	 */
	public void saveDivision(Division division)
	{
		divisionManager.saveOrUpdateDivision(division);
	}

	/**
	 * @param idDivision
	 */
	public void deleteDivision(Long idDivision)
	{
		divisionManager.deleteDivision(idDivision);		
	}
	
	/******************************************************************************************************************************
	 * 
	 * 											N   A   T   I   O   N
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */		
	public void saveNation(Nation nation)
	{
		nationManager.saveOrUpdateNation(nation);
	}
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Nation> getNations()
	{
		return nationManager.listNations();
	}
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */		
	@RemoteMethod
	public List<Nation> getNationsFromContinent(Long continentId)
	{
		return nationManager.listNationsFromContinent(continentId);		
	}
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */	
	public List<Nation> getNationsTournament()
	{
		return nationManager.listNationsTournament();
	}	
	
	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Nation getNation(Long nationId)
	{
		return nationManager.getNationByID(nationId);
	}
	
	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteNation(Long nationId)
	{
		nationManager.deleteNation(nationId);
	}		
	
	
	/******************************************************************************************************************************
	 * 
	 * 											P   L   A   Y   E   R
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param player
	 */	
	public void savePlayer(Player player)
	{
		playerManager.saveOrUpdatePlayer(player);
	}

	/**
	 * @param idTeam
	 * @param teamCategory
	 * @return
	 */	
	public List<Player> getPlayers(Long idTeam, String teamCategory)
	{
		return playerManager.listPlayersByTeam(idTeam, teamCategory);
	}
	
	/**
	 * @param letter
	 * @return
	 */
	@RemoteMethod
	public List<Player> getPlayers(String letter, String searchType)
	{
		return playerManager.listPlayersByLetter(letter, searchType);
	}	
	
	/**
	 * @param idDivision
	 * @return
	 */
	public List<Player> getPlayers(Long idDivision)
	{
		return playerManager.listPlayersByDivision(idDivision);
	}	
	
	/**
	 * @return
	 */
	public List<Player> getPlayers()
	{
		return playerManager.listPlayers();
	}

	/**
	 * @param idPlayer
	 */
	public void deletePlayer(Long idPlayer)
	{
		playerManager.deletePlayer(idPlayer);
	}

	/**
	 * @param idPlayer
	 * @return
	 */	
	@RemoteMethod
	public Player getPlayerByID(Long idPlayer)
	{
		return playerManager.getPlayerByID(idPlayer);
	}
	
	/**
	 * @param idPlayer
	 * @param idTeam
	 * @@param isLoan
	 */	
	@RemoteMethod
	public void updateTeam(Long idPlayer, Long idTeam, Boolean isLoan)
	{
		playerManager.updateTeam(idPlayer, idTeam, isLoan);
	}
	
	/**
	 * @param player
	 * @return
	 */	
	@RemoteMethod
	public boolean findPlayerExists(String firstName, String lastName, String birthDate)
	{
		return playerManager.findPlayerExists(firstName, lastName, birthDate);
	}
	
	/**
	 * @param teamId
	 * @param rankId
	 * @return
	 */
	public HashMap<String, Object> getNextId(Long teamId, Integer rankId)
	{
		return playerManager.getNextId(teamId, rankId);
	}
	
	/**
	 * @param teamId
	 * @param playerId
	 * @return
	 */
	public String getPlayerRank(Long teamId, Long playerId) 
	{
		return playerManager.getRank(teamId, playerId);		
	}
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */		
	public void doEndPlayerSeasonJob()
	{
		playerManager.endSeasonJob();		
	}
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */			
	public void doCareerPlayerJob()
	{
		playerManager.careerPlayerJob();
	}	
	
	
	/******************************************************************************************************************************
	 * 
	 * 											P   O   S   I   T   I   O   N
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */		
	public void savePosition(Position position)
	{
		positionManager.saveOrUpdatePosition(position);
	}
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */		
	@RemoteMethod
	public List<Position> getPositions(String codRuolo)
	{
		return positionManager.listPositions(codRuolo);
	}	
	
	/**
	 * Method to get a position 
	 * 
	 * @param positionId the position id
	 * @return the position found
	 */		
	public Position getPosition(Long positionId)
	{
		return positionManager.getPositionByID(positionId);
	}
	
	/**
	 * Method to delete a position
	 * 
	 * @param positionId the position id
	 */		
	public void deletePosition(Long positionId)
	{
		positionManager.deletePosition(positionId);
	}
	

	/******************************************************************************************************************************
	 * 
	 * 											S   E   A   S   O   N
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * Method to save season
	 * 
	 * @param career
	 *            the season to save
	 */
	public void saveSeason(Season seasonYear)
	{
		seasonManager.saveOrUpdateSeason(seasonYear);
	}

	/**
	 * Method to list season of a team
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public List<Season> getSeasons()
	{
		return seasonManager.listSeason();
	}

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	//@RemoteMethod
	public Season getSeasonYearByName(String nameSeason)
	{
		return seasonManager.getSeasonYearByName(nameSeason);		
	}

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public Season getSeason(Long seasonYearID)
	{
		return seasonManager.getSeasonYearById(seasonYearID);				
	}

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public void deleteSeason(Long seasonYearID)
	{
		seasonManager.deleteById(seasonYearID);		
	}
	
	/******************************************************************************************************************************
	 * 
	 * 											T   E   A   M
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param idTeam
	 * @return
	 */
	@RemoteMethod
	public Team getTeamByID(Long idTeam)
	{
		return teamManager.getTeamByID(idTeam);
	}

	/**
	 * @param name
	 * @return
	 */
	public Team getTeam(String name)
	{
		return teamManager.getTeamByName(name);
	}

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@RemoteMethod
	public List<Team> getTeamsByDivision(Long nationId, Long divisionId)
	{
		return teamManager.listTeamsByDivision(nationId, divisionId);
	}
	
	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<Team> getTeamsByDivisionForView(Long nationId, Long divisionId)
	{
		return teamManager.listTeamsByDivisionForView(nationId, divisionId);
	}
	
	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<String> getTeams(String term)	
	{
		return teamManager.listTeamsByName(term);
	}


	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveTeam(Team team)
	{
		teamManager.saveOrUpdateTeam(team);
	}

	/**
	 * @param idTeam
	 */
	public void deleteTeam(Long idTeam)
	{
		teamManager.deleteTeam(idTeam);
	}
	
	
	/******************************************************************************************************************************
	 * 
	 * 											U   S   E   R
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param idTeam
	 * @return
	 */
	@RemoteMethod
	public UserBean getUser(Long idUser)
	{
		return userManager.getUserByID(idUser);
	}

	/**
	 * @param name
	 * @return
	 */
	public UserBean getUser(String name)
	{
		return userManager.getUserByName(name);		
	}

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<UserBean> getUsers()
	{
		return userManager.listUsers();				
	}


	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveUser(UserBean user)
	{
		userManager.saveOrUpdateUser(user);						
	}

	/**
	 * @param idTeam
	 */
	public void deleteUser(Long idUser)
	{
		userManager.deleteUser(idUser);
	}
	
	

	/******************************************************************************************************************************
	 * 
	 * 											S   E   T   T   E   R   S
	 * 
	 ******************************************************************************************************************************/

	public void setCareerManager(CareerManager careerManager)
	{
		this.careerManager = careerManager;
	}
	public void setContinentManager(ContinentManager continentManager)
	{
		this.continentManager = continentManager;
	}
	public void setDivisionManager(DivisionManager divisionManager)
	{
		this.divisionManager = divisionManager;
	}
	public void setNationManager(NationManager nationManager)
	{
		this.nationManager = nationManager;
	}
	public void setPlayerManager(PlayerManager playerManager)
	{
		this.playerManager = playerManager;
	}
	public void setPositionManager(PositionManager positionManager)
	{
		this.positionManager = positionManager;
	}
	public void setSeasonManager(SeasonManager seasonManager)
	{
		this.seasonManager = seasonManager;
	}
	public void setTeamManager(TeamManager teamManager)
	{
		this.teamManager = teamManager;
	}
	public void setUserManager(UserManager userManager)
	{
		this.userManager = userManager;
	}	
	
	
	
}
