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

import org.springframework.transaction.annotation.Transactional;

import com.jfootball.domain.Staff;

/**
 * Interface of a player object
 * 
 * @author Alessandro Danesi
 */
public interface StaffDao 
{
	/**
	 * Method to save player
	 * 
	 * @param career the player to save
	 */
	 @Transactional
	public void saveOrUpdateStaff(Staff staff);

	/**
	 * Method to list player of a team
	 * 
	 * @param teamId
	 *            the team ask for our players
	 * @return the players found
	 */
	public List<Staff> listStaffs();
	
	
	
	/**
	 * Method to list staff of a team
	 * 
	 * @param teamId the team ask for our staffs
	 * @return the staffs to found
	 */	
	public List<Staff> listStaffsByTeam(Long teamId, String teamBranch);

	/***
	 * Method to list players
	 * 
	 * @return the players found
	 * 
	 */
	public List<Staff> listStaffsByLetter(String letter, String searchType);

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */
	public Staff getStaffById(Long staffId);

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */	
	public Staff getStaffBySecondId(Long teamId);

	/**
	 * Method to delete a player
	 * 
	 * @param playerId
	 *            the playerId id
	 */
	public void deleteStaff(Long staffId);

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	public boolean findStaffExists(String firstName, String lastName, String dateOfBirth);


}
