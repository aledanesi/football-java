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
package com.jfootball.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jfootball.dao.SeasonDao;
import com.jfootball.domain.Season;

/**
 * Class of a career object
 * 
 * @author Alessandro Danesi
 */
@Repository("seasonDAO")
public class SeasonDaoImpl extends GenericDao implements SeasonDao
{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/*************************************************
	 * METHODS
	 **************************************************/

	/**
	 * Method to save career
	 * 
	 * @param career
	 *            the career to save
	 * 
	 */
	public void saveOrUpdateSeason(Season seasonYear)
	{
		hibernateTemplate.saveOrUpdate(seasonYear);
	}

	/**
	 * Method to list career of a player
	 * 
	 * @param idPlayer
	 *            the player ask for our career
	 */
	@SuppressWarnings("unchecked")
	public List<Season> listSeason()
	{
		return hibernateTemplate.find("from Season s order by value desc");
	}

	/**
	 * Method to get a year of player career
	 * 
	 * @param idPlayer
	 *            the player ask for our career
	 */
	public Season getSeasonYearByName(String nameCareer)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Season.class);
		return (Season) criteria.add(Restrictions.eq("value", nameCareer)).uniqueResult();
	}

	/**
	 * Method to get a year of player career
	 * 
	 * @param idPlayer
	 *            the player ask for our career
	 * @return the career
	 */
	public Season getSeasonYearById(Long seasonYearID)
	{
		return (Season) hibernateTemplate.get(Season.class, seasonYearID);
	}

	/**
	 * Method to save career
	 * 
	 * @param career
	 *            the career to save
	 * 
	 */
	public void deleteById(Long seasonYearID)
	{
		Season seasonYear = (Season) hibernateTemplate.get(Season.class, seasonYearID);
		hibernateTemplate.delete(seasonYear);
	}

}
