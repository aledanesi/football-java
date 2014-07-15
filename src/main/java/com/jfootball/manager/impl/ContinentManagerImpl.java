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
package com.jfootball.manager.impl;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.ContinentDao;
import com.jfootball.domain.Continent;
import com.jfootball.manager.ContinentManager;

/**
 * @author C_ICTDNS
 * 
 */
@Service("continentManager")
@Transactional(readOnly = true)
@RemoteProxy(name = "continentManager")
public class ContinentManagerImpl extends GenericManager implements ContinentManager
{

	private final ContinentDao continentDao;
		
	
	@Autowired
	public ContinentManagerImpl(ContinentDao continentDao) 
	{
		this.continentDao = continentDao;
	}		

	
	/**
	 * Method to save nation
	 * 
	 * @param nation the nation to save
	 */	
	public void saveOrUpdateContinent(Continent continent)
	{
		continentDao.saveOrUpdateContinent(continent);
	}

	/**
	 * Method to list nations
	 * 
	 * @return the nations found
	 */
	public List<Continent> listContinents()
	{
		return continentDao.listContinents();
	}

	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public Continent getContinentByID(Long continentId)
	{
		return continentDao.getContinentByID(continentId);		
	}

	/**
	 * Method to delete a nation
	 * 
	 * @param nationId the nation id
	 */	
	public void deleteContinent(Long continentId)
	{
		continentDao.deleteContinent(continentId);
	}
	
}
