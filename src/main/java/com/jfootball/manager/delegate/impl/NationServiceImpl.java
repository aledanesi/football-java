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
package com.jfootball.manager.delegate.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.NationDao;
import com.jfootball.domain.Nation;
import com.jfootball.manager.delegate.BusinessService;

/**
 * @author C_ICTDNS
 *
 */
@Transactional(readOnly = true)
public class NationServiceImpl implements BusinessService
{
	
	private final NationDao nationDAO;

	@Autowired
	public NationServiceImpl(NationDao nationDAO) 
	{
		this.nationDAO = nationDAO;
	}	
	

	public void saveEntity(Serializable obj)
	{
		Nation nation = (Nation)obj;
		nationDAO.saveOrUpdateNation(nation);
	}
	

	public List<Nation> getEntities()
	{
		return nationDAO.listNations();
	}
	
	public List<Nation> getEntitiesBySecondID(Long continentId)
	{
		return nationDAO.listNationsFromContinent(continentId);		
	}
	
	public List<Nation> getOtherEntities()
	{
		return nationDAO.listNationsTournament();
	}	
	

	public Nation getEntityByID(Long nationId)
	{
		return nationDAO.getNationByID(nationId);
	}
	

	public void deleteEntity(Long nationId)
	{
		nationDAO.deleteNation(nationId);
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
	public void updateEntityByParams(Object... params) {
	}
	
	@Override
	public HashMap<String, Object> getHashMap(Long param1, Integer param2) {
		return null;
	}
	
	@Override
	public boolean findEntityExists(String... params) {
		return false;
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