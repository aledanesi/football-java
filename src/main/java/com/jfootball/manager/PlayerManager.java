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
package com.jfootball.manager;

import java.util.HashMap;
import java.util.List;

import com.jfootball.domain.Player;

/**
 * @author C_ICTDNS
 * 
 */
public interface PlayerManager {


	/**
	 * @param idPlayer
	 * @return
	 */
	public Player getPlayerByID(Long idPlayer);

	/**
	 * @param idTeam
	 * @param teamCategory
	 * @return
	 */
	public List<Player> listPlayersByTeam(Long idTeam, String teamCategory);

	/**
	 * @param letter
	 * @return
	 */
	public List<Player> listPlayersByLetter(String letter, String searchType);

	
	/**
	 * @param idDivision
	 * @return
	 */
	public List<Player> listPlayersByDivision(Long idDivision);

	/**
	 * @return
	 */
	public List<Player> listPlayers();

	/**
	 * @param player
	 */
	public void saveOrUpdatePlayer(Player player);

	/**
	 * @param idPlayer
	 * @param idTeam
	 * @@param isLoan
	 */
	public void updateTeam(Long idPlayer, Long idTeam, Boolean isLoan);

	/**
	 * @param idPlayer
	 */
	public void deletePlayer(Long idPlayer);


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
	
	/**
	 * @param teamId
	 * @param rankId
	 * @return
	 */
	public HashMap<String, Object> getNextId(Long teamId, Integer rankId);

}
