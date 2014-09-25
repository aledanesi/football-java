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
package com.jfootball.business.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.business.BusinessService;
import com.jfootball.dao.PositionDao;
import com.jfootball.domain.Position;

/**
 * @author C_ICTDNS
 *
 */
@Transactional(readOnly = true)
public class PositionServiceImpl implements BusinessService
{
	private PositionDao positionDAO;
	
	@Autowired
	public PositionServiceImpl(PositionDao positionDAO) 
	{
		this.positionDAO = positionDAO;
	}	
	

	public void saveEntity(Serializable obj)
	{
		Position position = (Position)obj;
		positionDAO.saveOrUpdatePosition(position);
	}
	
	public List<Position> getEntitiesByParams(String... params)
	{
		return positionDAO.listPositions(params[0]);
	}
	

	public Position getEntityByID(Long positionId)
	{
		return positionDAO.getPositionByID(positionId);
	}
	

	public void deleteEntity(Long positionId)
	{
		positionDAO.deletePosition(positionId);
	}


	
	
	
	
	
	
	/** ************************************************************************************************************
	 * 
	 * // Auto-generated method stub
	 * 
	 * 
	 * *************************************************************************************************************/
	
	
	@Override
	public Serializable getEntityBySecondId(Long id) {
		return null;
	}


	@Override
	public Serializable getEntityByDesc(String desc) {
		return null;
	}


	@Override
	public List<? extends Serializable> getEntitiesByID(Long id) {
		return null;
	}


	@Override
	public List<? extends Serializable> getEntitiesBySecondID(Long id) {
		return null;
	}


	@Override
	public List<? extends Serializable> getEntitiesByIDs(Long id1, Long id2) {
		return null;
	}


	@Override
	public List<? extends Serializable> getEntitiesByIDsNew(Long id1, Long id2) {
		return null;
	}


	@Override
	public List<? extends Serializable> getEntitiesByIDAndDesc(Long id, String desc) {
		return null;
	}


	@Override
	public List<? extends Serializable> getEntities() {
		return null;
	}


	@Override
	public List<? extends Serializable> getOtherEntities() {
		return null;
	}


	@Override
	public void updateEntityByParams(Object... params) {
	}

	@Override
	public boolean findEntityExists(String... params) {
		return false;
	}


	@Override
	public HashMap<String, Object> getHashMap(Long param1, Integer param2) {
		return null;
	}

	@Override
	public String getString(Long teamId, Long playerId) {
		return null;
	}	

	@Override
	public void doFirstJob() {
	}


	@Override
	public void doSecondJob() {
	}

}
