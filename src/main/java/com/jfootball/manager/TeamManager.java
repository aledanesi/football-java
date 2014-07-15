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

import java.util.List;

import com.jfootball.domain.Team;

/**
 * @author Alessandro Danesi
 * 
 */
public interface TeamManager 
{
	
	/**
	 * @param idTeam
	 * @return
	 */
	public Team getTeamByID(Long idTeam);

	/**
	 * @param name
	 * @return
	 */
	public Team getTeamByName(String name);

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<Team> listTeamsByDivision(Long nationId, Long divisionId);
	
	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<Team> listTeamsByDivisionForView(Long nationId, Long divisionId);
	
	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<String> listTeamsByName(String term);	


	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveOrUpdateTeam(Team team);

	/**
	 * @param idTeam
	 */
	public void deleteTeam(Long idTeam);

}
