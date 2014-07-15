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

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.DivisionDao;
import com.jfootball.domain.Division;
import com.jfootball.manager.DivisionManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("divisionManager")
@RemoteProxy(name = "divisionManager")
public class DivisionManagerImpl extends GenericManager implements DivisionManager 
{
	
	private final DivisionDao divisionDAO;


	@Autowired
	public DivisionManagerImpl(DivisionDao divisionDAO) 
	{
		this.divisionDAO = divisionDAO;
	}	
	
	
	public List<Division> listDivisions() {
		return divisionDAO.listDivisions();
	}


	public Division getDivisionByID(Long divisionId) {
		return divisionDAO.getDivisionByID(divisionId);
	}


	@RemoteMethod
	public List<Division> listDivisionsByNation(Long nationId) 
	{
		//return divisionDAO.listDivisionsByNation(nationId);
		List<Division> lista = divisionDAO.listDivisionsByNation(nationId);
		
		// codice temporaneo da cancellare quando saranno sistemati tutti i nomi dei campionati non ancora inseriti
		if (lista.size() == 0)
		{
			lista = new ArrayList<Division>();
			
			Division d1 = new Division();
			d1.setId(1L);
			d1.setLevel(1L);
			d1.setName("SERIE A");
			
			Division d2 = new Division();
			d2.setId(2L);
			d2.setLevel(2L);
			d2.setName("SERIE B");
			
			lista.add(d1);
			lista.add(d2);
		}
		return lista;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdateDivision(Division division) 
	{
		divisionDAO.saveOrUpdateDivision(division);
	}
	
	public void deleteDivision(Long idDivision)
	{
		divisionDAO.deleteDivision(idDivision);		
	}


}