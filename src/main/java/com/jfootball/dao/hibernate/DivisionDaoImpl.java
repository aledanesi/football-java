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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jfootball.dao.DivisionDao;
import com.jfootball.domain.Division;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("divisionDAO")
public class DivisionDaoImpl extends GenericDao implements DivisionDao
{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/
	
	/**
	 * hibernateTemplate.find("from Division d order by d.id").list()
	 * 
	 * @see com.jfootball.dao.DivisionDao#listDivisions()
	 */
	@SuppressWarnings("unchecked")
	public List<Division> listDivisions()
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Division.class);
		criteria.add( Restrictions.gt("level", new Long(0)) );
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	public Division getDivisionByID(Long divisionId)
	{
		return (Division) hibernateTemplate.get(Division.class, divisionId);
	}

	@SuppressWarnings("unchecked")
	public List<Division> listDivisionsByNation(Long nationId)
	{
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from Division d where nation_id = ? order by d.level", nationId);
	}
	
	/**
	 * Method to save team
	 * 
	 * @param team
	 *            the team to save
	 */
	public void saveOrUpdateDivision(Division division) {
		if (division.getId() != null) {
			hibernateTemplate.merge(division);
		} else {
			hibernateTemplate.saveOrUpdate(division);
		}
	}	
	
	/**
	 * Method to delete a player
	 * 
	 * @param playerId
	 *            the playerId id
	 */
	public void deleteDivision(Long divisionId) 
	{
		Division division = (Division) hibernateTemplate.get(Division.class, divisionId);
		hibernateTemplate.delete(division);
	}	

}