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
package com.progetto.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progetto.dao.PlayerDao;
import com.progetto.domain.Player;
import com.progetto.manager.PlayerManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("playerManager")
public class PlayerManagerImpl implements PlayerManager
{
	@Autowired
	private PlayerDao playerDAO;
	
	
	public void saveOrUpdatePlayer(Player player)
	{
		playerDAO.saveOrUpdatePlayer(player);
	}

	public List<Player> listPlayersByTeam(long idTeam)
	{
		return playerDAO.listPlayersByTeam(idTeam);
	}
	
	public List<Player> listPlayersByLetter(String letter)
	{
		return playerDAO.listPlayersByLetter(letter);
	}	
	
	public List<Player> listPlayersByDivision(long idDivision)
	{
		List<Player> listaOrig = listPlayers();
		List<Player> listaNew = new ArrayList<Player>();
		for (Player player : listaOrig)
		{
			if (player.getTeamId() != null )
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

	public void deletePlayer(long idPlayer)
	{
		playerDAO.deletePlayer(idPlayer);
	}

	public Player getPlayerById(long idPlayer)
	{
		return playerDAO.getPlayerById(idPlayer);
	}
	
	public void updateTeam(long idPlayer, long idTeam)
	{
		Player player = getPlayerById(idPlayer);
		player.setTeamId(idTeam);
		
		playerDAO.saveOrUpdatePlayer(player);
	}

	public void setPlayerDAO(PlayerDao playerDAO) {
		this.playerDAO = playerDAO;
	}

	
}
