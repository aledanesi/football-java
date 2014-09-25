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
import com.jfootball.dao.ContinentDao;
import com.jfootball.domain.Continent;

/**
 * @author C_ICTDNS
 * 
 */
@Transactional(readOnly = true)
public class ContinentServiceImpl implements BusinessService
{

	private final ContinentDao continentDao;
		
	
	@Autowired
	public ContinentServiceImpl(ContinentDao continentDao) 
	{
		this.continentDao = continentDao;
	}		

	
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */	
	public void saveEntity(Serializable obj)
	{
		Continent continent = (Continent)obj;
		continentDao.saveOrUpdateContinent(continent);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Continent> getEntities()
	{
		return continentDao.listContinents();
	}

	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Continent getEntityByID(Long continentId)
	{
		return continentDao.getContinentByID(continentId);		
	}

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteEntity(Long continentId)
	{
		continentDao.deleteContinent(continentId);
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
	public List<? extends Serializable> getEntitiesByParams(String... params) {
		return null;
	}


	@Override
	public List<? extends Serializable> getOtherEntities() {
		return null;
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
	public boolean findEntityExists(String... params) {
		return false;
	}


	@Override
	public void updateEntityByParams(Object... params) {
	}


	@Override
	public void doFirstJob() {
	}


	@Override
	public void doSecondJob() {
	}
	
}
