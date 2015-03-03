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

import com.jfootball.domain.Position;

/**
 * Interface of a position object
 * 
 * @author Alessandro Danesi
 */
public interface PositionDao 
{
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */		
	public void saveOrUpdatePosition(Position position);
	
	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */		
	public List<Position> listPositions(String codRuolo);
	
	/**
	 * Method to get a position 
	 * 
	 * @param positionId the position id
	 * @return the position found
	 */		
	public Position getPositionByID(Long positionId);
	
	/**
	 * Method to delete a position
	 * 
	 * @param positionId the position id
	 */		
	public void deletePosition(Long positionId);
}
