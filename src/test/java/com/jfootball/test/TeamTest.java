package com.jfootball.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfootball.Constant;
import com.jfootball.business.impl.TeamServiceImpl;
import com.jfootball.domain.Division;
import com.jfootball.domain.Nation;
import com.jfootball.domain.Team;

public class TeamTest extends BaseTest 
{

	@Autowired
	protected TeamServiceImpl teamManager;

	private Team team;
	
	@Before
	public void initialize() 
	{
		team = new Team();
		team.setName("XXX");
		team.setManager(null);

		Division division = new Division();
		division.setId(new Long(Constant.DEFAULT_DIVISION));

		Nation nation = new Nation();
		nation.setId(new Long(Constant.DEFAULT_NATION));

		team.setDivision(division);
		team.setNation(nation);
	}

	@Test
	public void getListTeam() 
	{
		Long nationId = new Long(Constant.DEFAULT_NATION);
		Long divisionId = new Long(Constant.DEFAULT_DIVISION);
		
		List<Team> teamList =  teamManager.getEntitiesByIDs(nationId, divisionId);
		Assert.assertNotNull("Team list is null.", teamList);

		prLine("Getting the team list...", "test ok");
	}
	
	@Test
	public void getTeamByID() 
	{
		// team NULL
		Long teamID = new Long(10);
		
		Team team = teamManager.getEntityByID(teamID);
		Assert.assertNull("Team is not null.", team);

		// team ROMA
		teamID = new Long(212);
		
		team = teamManager.getEntityByID(teamID);
		Assert.assertNotNull("Team is null.", team);

		prLine("Getting team by ID...", "test ok");
	}	
	
	@Test
	public void getTeamByName() 
	{
		// team NULL
		String teamName = "POMEZIA";
		
		Team team = teamManager.getEntityByDesc(teamName);
		Assert.assertNull("Team is not null.", team);

		// team NAPOLI
		teamName = "NAPOLI";
		
		team = teamManager.getEntityByDesc(teamName);
		Assert.assertNotNull("Team is null.", team);

		prLine("Getting team by Name...", "test ok");
	}
	
	@Test
	public void saveTeam() 
	{
		
		teamManager.saveEntity(team);
		
		Team obj = teamManager.getEntityByDesc("XXX");
		Assert.assertNotNull("Team is null.", obj);

		prLine("Saving team...", "test ok");
	}	
		

	@Test
	public void deleteTeam() 
	{		
		teamManager.saveEntity(team);

		Team obj = teamManager.getEntityByDesc("XXX");
		Assert.assertNotNull("Team is null.", obj);
		
		teamManager.deleteEntity(obj.getId());

		prLine("Deleting team...", "test ok");
	}	

}
