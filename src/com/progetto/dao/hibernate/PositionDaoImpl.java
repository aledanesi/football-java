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
import com.progetto.dao.PositionDao;
import com.progetto.domain.Position;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("positionDAO")
public class PositionDaoImpl extends HibernateDaoSupport implements PositionDao {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

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
	public void saveOrUpdatePosition(Position position) {
		getHibernateTemplate().saveOrUpdate(position);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Position> listPositions() {
		return getHibernateTemplate().find("from Position");
	}

	/**
	 * Method to get a position
	 * 
	 * @param positionId
	 *            the position id
	 * @return the position found
	 */
	@Override
	public Position getPositionById(Long positionId) {
		return (Position) getHibernateTemplate()
				.get(Position.class, positionId);
	}

	/**
	 * Method to delete a position
	 * 
	 * @param positionId
	 *            the position id
	 */
	@Override
	public void deletePosition(Long positionId) {
		Position position = (Position) getHibernateTemplate().get(
				Position.class, positionId);
		getHibernateTemplate().delete(position);
	}

}