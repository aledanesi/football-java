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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.progetto.dao.TeamDao;
import com.progetto.domain.Division;
import com.progetto.domain.Team;
import com.progetto.manager.TeamManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("teamManager")
@Transactional(readOnly = true)
public class TeamManagerImpl implements TeamManager
{
  @Autowired
  private TeamDao teamDAO;
  
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
  public void saveOrUpdateTeam(Team team)
  {
	if (team.getImage() == null && team.getId() != null)
	{
		team.setImage(getTeamById(team.getId()).getImage());
	}	  
    teamDAO.saveOrUpdateTeam(team);
  }
  
  public List<Team> listTeamsByDivision(Long nationId, Long divisionId)
  {
    return teamDAO.listTeamsByDivision(nationId, divisionId);
  }
  
  public List<Team> listTeams()
  {
    return teamDAO.listTeams();
  }   
 
  public List<Team> listTeamsByQuery(String query)
  {
    return teamDAO.listTeamsByQuery(query);
  }   
   
  public List<Team> listFreeTeamsBySeason(Long seasonId, Long divisionId, Long nationId)
  {
    return teamDAO.listFreeTeamsBySeason(seasonId, divisionId, nationId);
  }
  
  public Long[] listSelectedTeamsBySeason(Long seasonId, Long divisionId, Long nationId)
  {
    List<Long> lista = teamDAO.listSelectedTeamsBySeason(seasonId, divisionId, nationId);
    Long[] arr = new Long[lista.size()];
    return lista.toArray(arr);
  }   
  
  @Transactional(readOnly = true, propagation = Propagation.NEVER)
  public List<Division> listDivisions()
  {
    return teamDAO.listDivisions();
  }  
  
  @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
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
