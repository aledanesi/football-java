/**
 * 
 */
package com.jfootball.manager;

import java.util.HashMap;
import java.util.List;

import com.jfootball.domain.Career;
import com.jfootball.domain.Continent;
import com.jfootball.domain.Division;
import com.jfootball.domain.Nation;
import com.jfootball.domain.Player;
import com.jfootball.domain.Position;
import com.jfootball.domain.Season;
import com.jfootball.domain.Team;
import com.jfootball.domain.user.UserBean;

/**
 * @author Alessandro Danesi
 *
 * 12/set/201422:59:09
 */
public interface FootballManager
{

	/******************************************************************************************************************************
	 * 
	 * 											C   A   R   E   E   R
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Career getCareerByID(Long idCareer);
		
	/**
	 * @param idCareer
	 * @return
	 */
	public List<Career> getCareers(Long idCareer);
	
	/**
	 * @param career
	 */
	public void saveCareer(Career career);
	
	
	/**
	 * @param idCareer
	 */
	public void deleteCareer(Long idCareer);		
	

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
	public void saveContinent(Continent continent);

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Continent> getContinents();

	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Continent getContinent(Long continentId);

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteContinent(Long continentId);
	
	/******************************************************************************************************************************
	 * 
	 * 											D   I   V   I   S   I   O   N
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @return
	 */
	public List<Division> getDivisions();

	/**
	 * @param divisionId
	 * @return
	 */
	public Division getDivision(Long divisionId);

	/**
	 * @param nationId
	 * @return
	 */
	public List<Division> getDivisionsByNation(Long nationId);

	/**
	 * @param division
	 */
	public void saveDivision(Division division);

	/**
	 * @param idDivision
	 */
	public void deleteDivision(Long idDivision);
	
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
	public void saveNation(Nation nation);
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Nation> getNations();
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */		
	public List<Nation> getNationsFromContinent(Long continentId);
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */	
	public List<Nation> getNationsTournament();
	
	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Nation getNation(Long nationId);
	
	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteNation(Long nationId);
	
	
	/******************************************************************************************************************************
	 * 
	 * 											P   L   A   Y   E   R
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param player
	 */	
	public void savePlayer(Player player);

	/**
	 * @param idTeam
	 * @param teamCategory
	 * @return
	 */	
	public List<Player> getPlayers(Long idTeam, String teamCategory);
	
	/**
	 * @param letter
	 * @return
	 */
	public List<Player> getPlayers(String letter, String searchType);
	
	/**
	 * @param idDivision
	 * @return
	 */
	public List<Player> getPlayers(Long idDivision);
	
	/**
	 * @return
	 */
	public List<Player> getPlayers();

	/**
	 * @param idPlayer
	 */
	public void deletePlayer(Long idPlayer);

	/**
	 * @param idPlayer
	 * @return
	 */	
	public Player getPlayerByID(Long idPlayer);
	
	/**
	 * @param idPlayer
	 * @param idTeam
	 * @@param isLoan
	 */	
	public void updateTeam(Long idPlayer, Long idTeam, Boolean isLoan);
	
	/**
	 * @param player
	 * @return
	 */	
	public boolean findPlayerExists(String firstName, String lastName, String birthDate);
	
	/**
	 * @param teamId
	 * @param rankId
	 * @return
	 */
	public HashMap<String, Object> getNextId(Long teamId, Integer rankId);
	
	/**
	 * @param teamId
	 * @param playerId
	 * @return
	 */
	public String getPlayerRank(Long teamId, Long playerId);
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */		
	public void doEndPlayerSeasonJob();
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */			
	public void doCareerPlayerJob();
	
	
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
	public void savePosition(Position position);
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */		
	public List<Position> getPositions(String codRuolo);
	
	/**
	 * Method to get a position 
	 * 
	 * @param positionId the position id
	 * @return the position found
	 */		
	public Position getPosition(Long positionId);
	
	/**
	 * Method to delete a position
	 * 
	 * @param positionId the position id
	 */		
	public void deletePosition(Long positionId);
	

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
	public void saveSeason(Season seasonYear);

	/**
	 * Method to list season of a team
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public List<Season> getSeasons();

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public Season getSeasonYearByName(String nameSeason);

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public Season getSeason(Long seasonYearID);

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public void deleteSeason(Long seasonYearID);
	
	/******************************************************************************************************************************
	 * 
	 * 											T   E   A   M
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param idTeam
	 * @return
	 */
	public Team getTeamByID(Long idTeam);

	/**
	 * @param name
	 * @return
	 */
	public Team getTeam(String name);

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<Team> getTeamsByDivision(Long nationId, Long divisionId);
	
	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<Team> getTeamsByDivisionForView(Long nationId, Long divisionId);
	
	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<String> getTeams(String term);	


	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveTeam(Team team);

	/**
	 * @param idTeam
	 */
	public void deleteTeam(Long idTeam);
	
	
	/******************************************************************************************************************************
	 * 
	 * 											U   S   E   R
	 * 
	 ******************************************************************************************************************************/
	
	/**
	 * @param idTeam
	 * @return
	 */
	public UserBean getUser(Long idUser);

	/**
	 * @param name
	 * @return
	 */
	public UserBean getUser(String name);

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<UserBean> getUsers();


	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveUser(UserBean user);

	/**
	 * @param idTeam
	 */
	public void deleteUser(Long idUser);

}
