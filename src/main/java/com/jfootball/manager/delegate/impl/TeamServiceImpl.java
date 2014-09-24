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
package com.jfootball.manager.delegate.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.TeamDao;
import com.jfootball.domain.Team;
import com.jfootball.manager.delegate.BusinessService;

/**
 * @author Alessandro Danesi
 * 
 */
@Transactional(readOnly = true)
public class TeamServiceImpl implements BusinessService
{

	private TeamDao teamDAO;

	@Autowired
	public TeamServiceImpl(TeamDao teamDAO)
	{
		this.teamDAO = teamDAO;
	}

	public Team getEntityByID(Long idTeam)
	{
		Team team = teamDAO.getTeamByID(idTeam);
		return team;
	}

	public Team getEntityByDesc(String name)
	{
		return teamDAO.getTeamByName(name);
	}

	public List<Team> getEntitiesByIDs(Long nationId, Long divisionId)
	{
		return teamDAO.listTeamsByDivision(nationId, divisionId);
	}

	public List<Team> getEntitiesByIDsNew(Long nationId, Long divisionId)
	{
		return teamDAO.listTeamsByDivisionForView(nationId, divisionId);
	}

	public List<String> getEntitiesByParams(String... params)
	{
		return teamDAO.listTeamsByName(params[0]);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveEntity(Serializable obj)
	{
		Team team = (Team) obj;
		
		if (team.getImage() == null && team.getId() != null)
		{
			team.setImage(getEntityByID(team.getId()).getImage());
		}
		try
		{
			teamDAO.saveOrUpdateTeam(team);
		} catch (Exception e)
		{
			//logger.error(e);
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteEntity(Long idTeam)
	{
		teamDAO.deleteTeam(idTeam);
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
	public List<? extends Serializable> getEntitiesByID(Long id) {
		return null;
	}

	@Override
	public List<? extends Serializable> getEntitiesBySecondID(Long id) {
		return null;
	}

	@Override
	public List<? extends Serializable> getEntitiesByIDAndDesc(Long id, String desc) {
		return null;
	}

	@Override
	public List<? extends Serializable> getEntities() {
		return null;
	}

	@Override
	public List<? extends Serializable> getOtherEntities() {
		return null;
	}
	
	@Override
	public boolean findEntityExists(String... params) {
		return false;
	}

	@Override
	public HashMap<String, Object> getHashMap(Long param1, Integer param2) {
		return null;
	}

	@Override
	public String getString(Long teamId, Long playerId) {
		return null;
	}	


	@Override
	public void updateEntityByParams(Object... params) {
	}

	@Override
	public void doFirstJob() {
	}

	@Override
	public void doSecondJob() {
	}
	
}
