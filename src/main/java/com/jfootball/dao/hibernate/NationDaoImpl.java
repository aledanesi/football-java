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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jfootball.dao.NationDao;
import com.jfootball.domain.Nation;

/**
 * Class of a nation object
 * 
 * @author Alessandro Danesi
 */
@Repository("nationDAO")
public class NationDaoImpl extends GenericDao implements NationDao 
{

	@Autowired
	private HibernateTemplate hibernateTemplate;


	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/


	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	@SuppressWarnings("unchecked")
	public List<Nation> listNations() {
		//return hibernateTemplate.find("from Nation n order by n.name");
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Nation.class);
		criteria.addOrder(Order.asc("name"));
		List<Nation> elencoNazioni = criteria.list();
		return elencoNazioni;	
	}
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	@SuppressWarnings("unchecked")
	public List<Nation> listNationsFromContinent(Long continentId) {
		//return hibernateTemplate.find("from Nation n order by n.name");
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Nation.class);
		criteria.add(Restrictions.eq("continent.id", continentId));
		criteria.addOrder(Order.asc("name"));
		List<Nation> elencoNazioni = criteria.list();
		return elencoNazioni;	
	}	
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	@SuppressWarnings("unchecked")
	public List<Nation> listNationsTournament() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("SELECT new Nation(n.id, n.name, n.value) FROM Team t, Nation n where t.nation.id = n.id GROUP BY t.nation.id HAVING id in (5, 11, 13, 45) order by n.name");
		List<Nation> result = query.list();
		return result;	
	}	

	/**
	 * Method to get a Nation
	 * 
	 * @param nationId
	 *            the nation id
	 * @return the nations found
	 */
	public Nation getNationByID(Long nationId) {
		return (Nation) hibernateTemplate.get(Nation.class, nationId);
	}

	/**
	 * Method to save nation
	 * 
	 * @param nation
	 *            the nation to save
	 */
	public void saveOrUpdateNation(Nation nation) {
		hibernateTemplate.saveOrUpdate(nation);
	}

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId
	 *            the nation id
	 */
	public void deleteNation(Long nationId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.clear();
		
		Nation nation = (Nation) hibernateTemplate.get(Nation.class, nationId);

		hibernateTemplate.delete(nation);
	}
}