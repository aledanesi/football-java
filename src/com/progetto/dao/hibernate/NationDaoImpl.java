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
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.progetto.dao.NationDao;
import com.progetto.domain.Nation;

/**
 * Class of a nation object
 * 
 * @author Alessandro Danesi
 */
@Repository("nationDAO")
public class NationDaoImpl extends HibernateDaoSupport implements NationDao {


	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	/**
	 * Method to save nation
	 * 
	 * @param nation
	 *            the nation to save
	 */
  @Override  
	public void saveOrUpdateNation(Nation nation) {
		getHibernateTemplate().saveOrUpdate(nation);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Nation> listNations() {
		return getHibernateTemplate().find("from Nation n order by n.name");
	}

	/**
	 * Method to get a Nation
	 * 
	 * @param nationId
	 *            the nation id
	 * @return the nations found
	 */
	@Override
	public Nation getNationById(Long nationId) {
		return (Nation) getHibernateTemplate().get(Nation.class, nationId);
	}

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId
	 *            the nation id
	 */
	@Override
	public void deleteNation(Long nationId) {
		Nation nation = (Nation) getHibernateTemplate().get(Nation.class, nationId);
		getHibernateTemplate().delete(nation);
	}
}