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
package com.jfootball.dao;

import java.util.List;

import com.jfootball.domain.Team;

/**
 * Interface of a team object
 * 
 * @author Alessandro Danesi
 */
public interface TeamDao {
	/**
	 * Method to save team
	 * 
	 * @param team
	 *            the team to save
	 */
	public void saveOrUpdateTeam(Team team);

	/**
	 * Method to get a team list by a division
	 * 
	 * @param divisionId
	 *            the division id
	 * @return the teams found
	 */
	public List<Team> listTeamsByDivision(Long nationId, Long divisionId);
	
	/**
	 * Method to get a team list by a division
	 * 
	 * @param divisionId
	 *            the division id
	 * @return the teams found
	 */
	public List<Team> listTeamsByDivisionForView(Long nationId, Long divisionId);


	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<String> listTeamsByName(String term);
	
	/**
	 * Method to get team by id
	 * 
	 * @param teamId
	 *            the team id
	 * @return the team found
	 */
	public Team getTeamByID(Long teamId);

	/**
	 * Method to get team by name
	 * 
	 * @param name
	 * @return the team found
	 */
	public Team getTeamByName(String name);

	/**
	 * Method to delete a team
	 * 
	 * @param teamId
	 *            the team id
	 */
	public void deleteTeam(Long teamId);

}