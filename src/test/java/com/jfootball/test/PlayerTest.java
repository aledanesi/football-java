package com.jfootball.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfootball.Constant;
import com.jfootball.domain.Nation;
import com.jfootball.domain.Player;
import com.jfootball.manager.PlayerManager;

public class PlayerTest extends BaseTest
{

	@Autowired
	protected PlayerManager playerManager;

	private Player player;

	@Before
	public void initializePlayer() {
		player = new Player();
		player.setFirstName("XXX");
		player.setLastName("XXX");
		player.setCaptain(false);

		Nation nation = new Nation();
		nation.setId(11L);

		player.setNationality(nation);
	}
	
	@Test
	public void getPlayerByID() {		
		// TOTTI
		Long playerId = 467L;

		Player player = playerManager.getPlayerByID(playerId);
		Assert.assertNotNull(player);			

		prLine("Getting the player by ID...", "test ok");
	}	
	
	@Test
	public void getListPlayerByTeam() 
	{

		List<Player> listPlayer = playerManager.listPlayersByTeam(212L, Constant.FIRST_TEAM);
		Assert.assertNotNull(listPlayer);			

		prLine("Getting the player list by Team...", "test ok");
	}	

	@Test
	public void createPlayer() 
	{	
		playerManager.saveOrUpdatePlayer(player);

		prLine("Creating player...", "test ok");
	}
	
	@Test
	public void deletePlayer() 
	{		
		List<Player> listPlayer = playerManager.listPlayersByTeam(212L, Constant.FIRST_TEAM);

		Player obj = listPlayer.get(listPlayer.size()-1);
		//Assert.assertNotNull("Player is null.", obj);
		
		playerManager.deletePlayer(obj.getId());

		prLine("Deleting player...", "test ok");
	}		

}
