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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.SeasonDao;
import com.jfootball.domain.Season;
import com.jfootball.manager.delegate.BusinessService;

/**
 * @author C_ICTDNS
 * 
 */
@Transactional(readOnly = true)
public class SeasonServiceImpl implements BusinessService
{
	
	private SeasonDao seasonDao;
	
	@Autowired
	public SeasonServiceImpl(SeasonDao seasonDao) 
	{
		this.seasonDao = seasonDao;
	}	
	


	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveEntity(Season season) {
		seasonDao.saveOrUpdateSeason(season);
	}


	public List<Season> getEntities() {
		return seasonDao.listSeason();
	}


	public Season getEntityByDesc(String nameSeason)
	{
		return seasonDao.getSeasonYearByName(nameSeason);
	}
	

	public Season getEntityByID(Long seasonYearID) {
		return seasonDao.getSeasonYearById(seasonYearID);
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteEntity(Long yearID) {

		seasonDao.deleteById(yearID);
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
	public void saveEntity(Serializable entity) {
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
