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
package com.progetto.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.progetto.dao.PositionDao;
import com.progetto.domain.Position;
import com.progetto.manager.PositionManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("positionManager")
public class PositionManagerImpl implements PositionManager
{
	@Autowired
	private PositionDao positionDAO;
	
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */		
	public void saveOrUpdatePosition(Position position)
	{
		positionDAO.saveOrUpdatePosition(position);
	}
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */		
	public List<Position> listPositions()
	{
		return positionDAO.listPositions();
	}
	
	/**
	 * Method to get a position 
	 * 
	 * @param positionId the position id
	 * @return the position found
	 */		
	public Position getPositionById(Long positionId)
	{
		return positionDAO.getPositionById(positionId);
	}
	
	/**
	 * Method to delete a position
	 * 
	 * @param positionId the position id
	 */		
	public void deletePosition(Long positionId)
	{
		positionDAO.deletePosition(positionId);
	}

	public void setPositionDAO(PositionDao positionDAO) {
		this.positionDAO = positionDAO;
	}
	
	
	
}
