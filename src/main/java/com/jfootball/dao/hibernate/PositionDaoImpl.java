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

import com.jfootball.dao.PositionDao;
import com.jfootball.domain.Position;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("positionDAO")
public class PositionDaoImpl extends GenericDao implements PositionDao
{

	@Autowired
	private HibernateTemplate hibernateTemplate;

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
	public void saveOrUpdatePosition(Position position)
	{
		hibernateTemplate.saveOrUpdate(position);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	@SuppressWarnings("unchecked")
	public List<Position> listPositions(String codRuolo)
	{
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Criteria criteria = session.createCriteria(Position.class);
		criteria.add(Restrictions.eq("codRuolo", codRuolo));
		criteria.addOrder(Order.asc("descPosizione"));

		List<Position> positions = criteria.list();
		return positions;
		// return hibernateTemplate.find("from Position");
	}

	/**
	 * Method to get a position
	 * 
	 * @param positionId
	 *            the position id
	 * @return the position found
	 */
	public Position getPositionByID(Long positionId)
	{
		return (Position) hibernateTemplate.get(Position.class, positionId);
	}

	/**
	 * Method to delete a position
	 * 
	 * @param positionId
	 *            the position id
	 */
	public void deletePosition(Long positionId)
	{
		Position position = (Position) hibernateTemplate.get(Position.class, positionId);
		hibernateTemplate.delete(position);
	}

}