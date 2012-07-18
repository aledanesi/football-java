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

import com.progetto.dao.DivisionDao;
import com.progetto.domain.Division;
import com.progetto.manager.DivisionManager;

/**
 * @author C_ICTDNS
 *
 */
public class DivisionManagerImpl implements DivisionManager
{
	@Autowired
	private DivisionDao divisionDAO;

	@Override
	public List<Division> listDivisions()
	{
		return divisionDAO.listDivisions();
	}	
	
	public Division getDivision(Long teamId, Long seasonYearId)
	{
		return divisionDAO.getDivision(teamId, seasonYearId);
	}		

	@Override
	/**
	 * Method to get a nation 
	 * 
	 * @param nationId the nation id
	 * @return the nation found
	 */	
	public List<Division> listDivisionsByNation(Long nationId)
	{
		return divisionDAO.listDivisionsByNation(nationId);
	}	
	
	public void setDivisionDAO(DivisionDao divisionDAO) {
		this.divisionDAO = divisionDAO;
	}	

}