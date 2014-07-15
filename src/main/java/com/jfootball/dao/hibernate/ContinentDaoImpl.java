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
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jfootball.dao.ContinentDao;
import com.jfootball.domain.Continent;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("continentDAO")
public class ContinentDaoImpl extends GenericDao implements ContinentDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */	
	public void saveOrUpdateContinent(Continent continent)
	{
		hibernateTemplate.saveOrUpdate(continent);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Continent> listContinents()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Continent.class);
		criteria.addOrder(Order.asc("name"));
		List<Continent> continentList = criteria.list();
		return continentList;			
	}

	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Continent getContinentByID(Long continentId)
	{
		return (Continent) hibernateTemplate.get(Continent.class, continentId);
	}

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteContinent(Long continentId)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.clear();
		
		Continent continent = (Continent) hibernateTemplate.get(Continent.class, continentId);

		hibernateTemplate.delete(continent);
		
	}
}
