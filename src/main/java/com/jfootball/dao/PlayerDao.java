/*
 * Progetto, a new java application 
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Rattazzi Street, Fifth Floor
 * Pomezia, RM  00040  Italy
 */
package com.jfootball.dao;

import java.util.HashMap;
import java.util.List;

import com.jfootball.domain.Player;

/**
 * Interface of a player object
 * 
 * @author Alessandro Danesi
 */
public interface PlayerDao 
{
	/**
	 * Method to save player
	 * 
	 * @param career the player to save
	 */		
	public void saveOrUpdatePlayer(Player player);
	
	/**
	 * Method to list player of a team
	 * 
	 * @param teamId the team ask for our players
	 * @return the players found
	 */		
	public List<Player> listPlayersByTeam(Long teamId, String teamCategory);
	
	/**
	 * Method to list player of a team
	 * 
	 * @param teamId the team ask for our players
	 * @return the players found
	 */		
	public List<Player> listPlayersByLetter(String letter, String searchType);
	

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */			
	public List<Player> listPlayers();
	
	/**
	 * Method to get a player 
	 * 
	 * @param playerId the player id
	 * @return the players found
	 */		
	public Player getPlayerById(Long playerId);
	
	/**
	 * Method to delete a player
	 * 
	 * @param playerId the playerId id
	 */		
	public void deletePlayer(Long playerId);

	
	/**
	 * @param teamId
	 * @param rankId
	 * @return
	 */
	public HashMap<String, Object> getNextId(Long teamId, Integer rankId);
	
	
	/**
	 * @param player
	 * @return
	 */
	public boolean findPlayerExists(String firstName, String lastName, String birthDate);
		
	
	/**
	 * @param teamId
	 * @param playerId
	 * @return
	 */
	public String getRank(Long teamId, Long playerId); 

	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */		
	public void endSeasonJob();
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */		
	public void careerPlayerJob();	

}
