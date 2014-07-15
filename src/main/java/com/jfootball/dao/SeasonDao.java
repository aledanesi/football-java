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

import com.jfootball.domain.Season;

/**
 * Interface of a career object
 * 
 * @author Alessandro Danesi
 */
public interface SeasonDao {
	/**
	 * Method to save season
	 * 
	 * @param career
	 *            the season to save
	 */
	public void saveOrUpdateSeason(Season season);

	/**
	 * Method to list season of a team
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public List<Season> listSeason();

	/**
	 * Method to get a year of player career
	 * 
	 * @param idPlayer
	 *            the player ask for our career
	 */
	public Season getSeasonYearByName(String nameSeason);

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public Season getSeasonYearById(Long seasonYearID);

	/**
	 * Method to get a year of player career
	 * 
	 * @param idSeason
	 *            the season ask for our team
	 */
	public void deleteById(Long seasonID);
}
