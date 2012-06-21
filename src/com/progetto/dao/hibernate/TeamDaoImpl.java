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
package com.progetto.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progetto.dao.TeamDao;
import com.progetto.domain.Team;

/**
 * @author C_ICTDNS
 *
 */
@Repository("teamDAO")
public class TeamDaoImpl extends HibernateDaoSupport implements TeamDao
{
	
	
	/*************************************************
	  
	  METHODS
	  
	 **************************************************/	
	
	/**
	 * Method to save team
	 * 
	 * @param team the team to save
	 */
	@Override
	public void saveOrUpdateTeam(Team team) 
	{
		getHibernateTemplate().saveOrUpdate(team);			
	}
	
	/**
	 * Method to list teams
	 * 
	 * @return the teams found
	 */	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Team> listTeams() 
	{
		List<Team> lista = getHibernateTemplate().find("from Team t order by t.name ");
		return lista;
	}
	
	/**
	 * Method to get a team list by a division 
	 * 
	 * @param divisionId the division id
	 * @return the teams found
	 */	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Team> listTeamsByDivision(Long divisionId) 
	{
		return getHibernateTemplate().findByNamedParam("from Team t where division_id = :divisionId order by t.name", "divisionId", divisionId);
	}	
	
	/**
	 * Method to get team by id
	 * 
	 * @param teamId the team id
	 * @return the team found
	 */
	@Override
	public Team getTeamById(Long teamId) 
	{
		return (Team)getHibernateTemplate().get(Team.class, teamId);
	}
	
	/**
	 * Method to delete a team
	 * 
	 * @param teamId the team id
	 */		
	@Override
	@Transactional
	public void deleteTeam(Long teamId)
	{
		Team team = (Team)getHibernateTemplate().get(Team.class, teamId);
		getHibernateTemplate().delete(team);
	}	

}