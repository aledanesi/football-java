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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.progetto.dao.CareerDao;
import com.progetto.domain.Career;

/**
 * Class of a career object
 * 
 * @author Alessandro Danesi
 */
@Repository("careerDAO")
public class CareerDaoImpl extends HibernateDaoSupport implements CareerDao {


	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	/**
	 * Method to save career
	 * 
	 * @param career
	 *            the career to save
	 * 
	 */
	@Override
	public void saveOrUpdateCareer(Career career) {
		getHibernateTemplate().saveOrUpdate(career);
	}

	/**
	 * Method to list career of a player
	 * 
	 * @param idPlayer
	 *            the player ask for our career
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Career> listCareer(Long idPlayer) {
		return getHibernateTemplate().find(
				"from Career c where c.player.id = " + idPlayer
						+ " order by stagione desc");
	}

	/**
	 * Method to get a year of player career
	 * 
	 * @param idPlayer
	 *            the player ask for our career
	 * @return the career
	 */
	@Override
	public Career getCareerById(Long careerId) {
		return (Career) getHibernateTemplate().get(Career.class, careerId);
	}
}
