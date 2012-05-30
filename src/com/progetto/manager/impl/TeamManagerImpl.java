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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.progetto.dao.TeamDao;
import com.progetto.domain.Division;
import com.progetto.domain.Team;
import com.progetto.manager.TeamManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("teamManager")
public class TeamManagerImpl implements TeamManager
{
	@Autowired
	private TeamDao teamDAO;
	
	public void saveOrUpdateTeam(Team team)
	{
		teamDAO.saveOrUpdateTeam(team);
	}
	
	public List<Team> listTeamsByDivision(long idDivision)
	{
		return teamDAO.listTeamsByDivision(idDivision);
	}
	
	public List<Team> listTeams()
	{
		return teamDAO.listTeams();
	}		
	
	public List<Division> listDivisions()
	{
		return teamDAO.listDivisions();
	}	
	
	public void deleteTeam(long idTeam)
	{
		teamDAO.deleteTeam(idTeam);
	}
	
	public Team getTeamById(long idTeam)
	{
		return teamDAO.getTeamById(idTeam);
	}

	public void setTeamDAO(TeamDao teamDAO) {
		this.teamDAO = teamDAO;
	}
	
	
}
