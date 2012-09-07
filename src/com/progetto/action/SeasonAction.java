package com.progetto.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.progetto.FileUploadAction;
import com.progetto.SpringHelper;
import com.progetto.domain.Division;
import com.progetto.domain.Nation;
import com.progetto.domain.Season;
import com.progetto.domain.SeasonYear;
import com.progetto.domain.Team;
import com.progetto.manager.DivisionManager;
import com.progetto.manager.NationManager;
import com.progetto.manager.SeasonManager;
import com.progetto.manager.TeamManager;

public class SeasonAction extends FileUploadAction implements ModelDriven<Season> 
{
  private static final long serialVersionUID = 1L;

  private TeamManager teamManager;
  private NationManager nationManager;
  private DivisionManager divisionManager;
  private SeasonManager seasonManager;

  private List<SeasonYear> yearList;
  private List<Season> championshipList;
  private List<Team> teamList;
  private List<Nation> nationList;
  
  private String[] selTeams;
  
  private List<Division> divisionList;

  private Season season;

  public SeasonAction() {
    teamManager = (TeamManager) SpringHelper.getBean("teamManager");
    nationManager = (NationManager) SpringHelper.getBean("nationManager");
    divisionManager = (DivisionManager) SpringHelper.getBean("divisionManager");
    seasonManager = (SeasonManager) SpringHelper.getBean("seasonManager");

    season = new Season();
    
    selTeams = new String[0];
    teamList = new ArrayList<Team>();
    yearList = new ArrayList<SeasonYear>();
    divisionList = divisionManager.listDivisions();
  }

  public Season getModel() {
    return season;
  }

  /*
   * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
   * 
   * METODI DI ACTION
   * 
   * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
   */


  /**
   * To save or update career.
   * 
   * @return String
   */
  @Action(value = "/saveSeason", results = {
      @Result(name = "success", location = "/content/secure/handleSeason.jsp"), 
      @Result(name = "input", location = "/content/secure/handleSeason.jsp") }

  )
  public String save() 
  {
    seasonManager.deleteAll(season);
    for (int i = 0; i < selTeams.length; i++)
    {
      Season season = new Season();
      season.setDivision(this.getSeason().getDivision());
      season.setNation(this.getSeason().getNation());
      season.setSeasonYear(this.getSeason().getSeasonYear());
      season.setTeam(teamManager.getTeamById(Integer.parseInt(selTeams[i])));
      seasonManager.saveOrUpdateSeason(season);
    }
  
    return list();
  }

  /**
   * To list all users.
   * 
   * @return String
   */
  @Action(value = "/handleSeason", results = { @Result(name = "success", location = "/content/secure/handleSeason.jsp") })
  public String list() {
    
    yearList = seasonManager.listYears();
    nationList = nationManager.listNations();
    
    // default value
    Long seasonYearId = 13L;
    Long divisionId = 1L;
    Long nationId = 11L;      
    
    if (season.getSeasonYear() != null)
    {
    	seasonYearId = season.getSeasonYear().getId(); 
      	season.setSeasonYear(seasonManager.getSeasonYearById(seasonYearId));
    }
    else 
    {
    	season.setSeasonYear(seasonManager.getSeasonYearById(seasonYearId));
    }
    
    if (season.getNation() != null)
    {
    	nationId = season.getNation().getId();  
    	season.setNation(nationManager.getNationById(nationId));
    } 
    else 
    {
    	season.setNation(nationManager.getNationById(nationId));
    }

    divisionList = divisionManager.listDivisionsByNation(nationId);

    if (season.getDivision() != null)
    {
    	divisionId = season.getDivision().getId(); 
    	Division division = divisionManager.getDivisionById(divisionId);
    	if (! divisionList.contains(division) && divisionList.size() > 0)
    	{
    		division = divisionList.get(0);
    		divisionId = division.getId();
        	season.setDivision(divisionManager.getDivisionById(division.getId()));
    	}
    	else 
    	{
    		season.setDivision(divisionManager.getDivisionById(divisionId));	
    	}
    	
    } 
    else 
    {
    	if (divisionList.size() > 0)
    	{
        	Division division = divisionList.get(0);
        	divisionId = division.getId();
        	season.setDivision(divisionManager.getDivisionById(division.getId()));
    	}
    }    
    
    teamList = teamManager.listFreeTeamsBySeason(seasonYearId, divisionId, nationId);
    season.setArr(teamManager.listSelectedTeamsBySeason(seasonYearId, divisionId, nationId));

    return SUCCESS;
  }  
  
  /*
   * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_
   * 
   * METODI GET E SET
   * 
   * =_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
   */

  public Season getSeason() {
    return season;
  }

  public void setSeason(Season season) {
    this.season = season;
  }

  public List<Season> getChampionshipList() {
    return championshipList;
  }
  
  public List<SeasonYear> getYearList() {
    return yearList;
  }  
  
  public List<Team> getTeamList() {
    return teamList;
  }  
  
  public List<Nation> getNationList() {
    return nationList;
  }    
  
  public List<Division> getDivisionList() {
    return divisionList;
  }  

  public String[] getSelTeams() {
    return selTeams;
  }

  public void setSelTeams(String[] selTeams) {
    this.selTeams = selTeams;
  }
}