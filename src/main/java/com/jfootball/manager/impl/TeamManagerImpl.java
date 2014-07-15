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

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.TeamDao;
import com.jfootball.domain.Team;
import com.jfootball.manager.TeamManager;

/**
 * @author Alessandro Danesi
 * 
 */
@Service("teamManager")
@Transactional(readOnly = true)
@RemoteProxy(name = "teamManager")
public class TeamManagerImpl extends GenericManager implements TeamManager
{

	private TeamDao teamDAO;

	@Autowired
	public TeamManagerImpl(TeamDao teamDAO)
	{
		this.teamDAO = teamDAO;
	}

	@RemoteMethod
	public Team getTeamByID(Long idTeam)
	{
		Team team = teamDAO.getTeamByID(idTeam);
		return team;
	}

	public Team getTeamByName(String name)
	{
		return teamDAO.getTeamByName(name);
	}

	@RemoteMethod
	public List<Team> listTeamsByDivision(Long nationId, Long divisionId)
	{
		return teamDAO.listTeamsByDivision(nationId, divisionId);
	}

	public List<Team> listTeamsByDivisionForView(Long nationId, Long divisionId)
	{
		return teamDAO.listTeamsByDivisionForView(nationId, divisionId);
	}

	public List<String> listTeamsByName(String term)
	{
		return teamDAO.listTeamsByName(term);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdateTeam(Team team)
	{
		if (team.getImage() == null && team.getId() != null)
		{
			team.setImage(getTeamByID(team.getId()).getImage());
		}
		try
		{
			teamDAO.saveOrUpdateTeam(team);
		} catch (Exception e)
		{
			logger.error(e);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteTeam(Long idTeam)
	{
		teamDAO.deleteTeam(idTeam);
	}

}
