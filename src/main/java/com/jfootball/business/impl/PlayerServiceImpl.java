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
package com.jfootball.business.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.business.BusinessService;
import com.jfootball.dao.PlayerDao;
import com.jfootball.dao.TeamDao;
import com.jfootball.domain.Player;
import com.jfootball.domain.Team;

/**
 * @author C_ICTDNS
 *
 */
@Transactional(readOnly = true)
public class PlayerServiceImpl implements BusinessService
{
	private PlayerDao playerDAO;
	private TeamDao teamDAO;	
	
	
	@Autowired
	public PlayerServiceImpl(PlayerDao playerDAO, TeamDao teamDAO) 
	{
		this.playerDAO = playerDAO;
		this.teamDAO = teamDAO;
	}		

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveEntity(Serializable obj)
	{
		Player player = (Player)obj;
		if (player.getImage() == null && player.getId() != null)
		{
			Long id = player.getId();
			Player p = getEntityByID(id);
			byte[] image = p.getImage();
			player.setImage(image);
		}	
		playerDAO.saveOrUpdatePlayer(player);
	}

	
	public List<Player> getEntitiesByIDAndDesc(Long idTeam, String teamCategory)
	{
		return playerDAO.listPlayersByTeam(idTeam, teamCategory);
	}
	
	public List<Player> getEntitiesByParams(String... params)
	{
		return playerDAO.listPlayersByLetter(params[0], params[1]);
	}	
	

	public List<Player> getEntitiesBySecondID(Long idDivision)
	{
		List<Player> listaOrig = getEntities();
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
	

	public List<Player> getEntities()
	{
		return playerDAO.listPlayers();
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteEntity(Long idPlayer)
	{
		playerDAO.deletePlayer(idPlayer);
	}

	public Player getEntityByID(Long idPlayer)
	{
		Player p = playerDAO.getPlayerById(idPlayer);
		return p;
	}
	
	@Transactional(readOnly = false)
	public void updateEntityByParams(Object... params)
	{
		Player player = getEntityByID((Long)params[0]);
		Team team = teamDAO.getTeamByID((Long)params[1]);
		
		if (! (Boolean)params[2])
			player.setTeamOwner(team);
		
		// fine 
		player.setNumber(null);
		player.setRetired(false);
		player.setUnemployed(false);		
		
		player.setTeam(team);
		playerDAO.saveOrUpdatePlayer(player);
		
	}
	
	public boolean findEntityExists(String... params)
	{
		return playerDAO.findPlayerExists(params[0], params[1], params[2]);
	}
	

	public HashMap<String, Object> getHashMap(Long teamId, Integer rankId)
	{
		return playerDAO.getNextId(teamId, rankId);
	}
	

	public String getString(Long teamId, Long playerId) 
	{
		return playerDAO.getRank(teamId, playerId);		
	}
	
	public void doFirstJob()
	{
		playerDAO.endSeasonJob();		
	}
	
	
	public void doSecondJob()
	{
		playerDAO.careerPlayerJob();
	}

	
	
	
	
	
	
	/** ************************************************************************************************************
	 * 
	 * // Auto-generated method stub
	 * 
	 * 
	 * *************************************************************************************************************/
	
	@Override
	public Serializable getEntityBySecondId(Long id) {
		return null;
	}

	@Override
	public Serializable getEntityByDesc(String desc) {
		return null;
	}

	@Override
	public List<? extends Serializable> getEntitiesByID(Long id) {
		return null;
	}

	@Override
	public List<? extends Serializable> getEntitiesByIDs(Long id1, Long id2) {
		return null;
	}

	@Override
	public List<? extends Serializable> getEntitiesByIDsNew(Long id1, Long id2) {
		return null;
	}

	@Override
	public List<? extends Serializable> getOtherEntities() {
		return null;
	}

}
