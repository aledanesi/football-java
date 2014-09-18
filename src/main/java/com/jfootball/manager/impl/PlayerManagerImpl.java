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
package com.jfootball.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.PlayerDao;
import com.jfootball.dao.TeamDao;
import com.jfootball.domain.Player;
import com.jfootball.domain.Team;
import com.jfootball.manager.PlayerManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("playerManager")
@Transactional(readOnly = true)
@RemoteProxy(name = "playerManager")
public class PlayerManagerImpl implements PlayerManager
{
	private PlayerDao playerDAO;
	private TeamDao teamDAO;	
	
	
	@Autowired
	public PlayerManagerImpl(PlayerDao playerDAO, TeamDao teamDAO) 
	{
		this.playerDAO = playerDAO;
		this.teamDAO = teamDAO;
	}		

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdatePlayer(Player player)
	{
		if (player.getImage() == null && player.getId() != null)
		{
			Long id = player.getId();
			Player p = getPlayerByID(id);
			byte[] image = p.getImage();
			player.setImage(image);
		}	
		playerDAO.saveOrUpdatePlayer(player);
	}

	
	public List<Player> listPlayersByTeam(Long idTeam, String teamCategory)
	{
		return playerDAO.listPlayersByTeam(idTeam, teamCategory);
	}
	

	public List<Player> listPlayersByLetter(String letter, String searchType)
	{
		return playerDAO.listPlayersByLetter(letter, searchType);
	}	
	

	public List<Player> listPlayersByDivision(Long idDivision)
	{
		List<Player> listaOrig = listPlayers();
		List<Player> listaNew = new ArrayList<Player>();
		for (Player player : listaOrig)
		{
			if (player.getTeam().getId() != null )
			{
				listaNew.add(player);
			}
		}
		return listaNew;
	}	
	

	public List<Player> listPlayers()
	{
		return playerDAO.listPlayers();
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePlayer(Long idPlayer)
	{
		playerDAO.deletePlayer(idPlayer);
	}

	@RemoteMethod
	public Player getPlayerByID(Long idPlayer)
	{
		Player p = playerDAO.getPlayerById(idPlayer);
		return p;
	}
	
	@RemoteMethod
	@Transactional(readOnly = false)
	public void updateTeam(Long idPlayer, Long idTeam, Boolean isLoan)
	{
		Player player = getPlayerByID(idPlayer);
		Team team = teamDAO.getTeamByID(idTeam);
		
		if (! isLoan)
			player.setTeamOwner(team);
		
		// fine 
		player.setNumber(null);
		player.setRetired(null);
		player.setUnemployed(null);		
		
		player.setTeam(team);
		playerDAO.saveOrUpdatePlayer(player);
		
	}
	
	@RemoteMethod
	public boolean findPlayerExists(String firstName, String lastName, String birthDate)
	{
		return playerDAO.findPlayerExists(firstName, lastName, birthDate);
	}
	

	public HashMap<String, Object> getNextId(Long teamId, Integer rankId)
	{
		return playerDAO.getNextId(teamId, rankId);
	}
	

	public String getRank(Long teamId, Long playerId) 
	{
		return playerDAO.getRank(teamId, playerId);		
	}
	
	public void endSeasonJob()
	{
		playerDAO.endSeasonJob();		
	}
	
	
	public void careerPlayerJob()
	{
		playerDAO.careerPlayerJob();
	}
	
	
}
