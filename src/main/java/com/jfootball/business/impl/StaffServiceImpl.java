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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.business.BusinessService;
import com.jfootball.dao.StaffDao;
import com.jfootball.domain.Staff;

/**
 * @author C_ICTDNS
 *
 */
@Transactional(readOnly = true)
public class StaffServiceImpl implements BusinessService
{
	private StaffDao staffDAO;
	
	@Autowired
	public StaffServiceImpl(StaffDao staffDAO) 
	{
		this.staffDAO = staffDAO;
	}		

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveEntity(Serializable obj)
	{
		Staff staff = (Staff)obj;
		if (staff.getImage() == null && staff.getId() != null)
		{
			Long id = staff.getId();
			Staff m = getEntityByID(id);
			byte[] image = m.getImage();
			staff.setImage(image);
		}	
		staffDAO.saveOrUpdateStaff(staff);
	}

	
	public List<Staff> getEntitiesByParams(String... params)
	{
		return staffDAO.listStaffsByLetter(params[0], params[1]);
	}	
	

	public List<Staff> getEntitiesBySecondID(Long idDivision)
	{
		List<Staff> listaOrig = getEntities();
		List<Staff> listaNew = new ArrayList<Staff>();
		for (Staff staff : listaOrig)
		{
			if (staff.getTeam().getId() != null )
			{
				listaNew.add(staff);
			}
		}
		return listaNew;
	}	
	

	public List<Staff> getEntities()
	{
		return staffDAO.listStaffs();
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteEntity(Long staffId)
	{
		staffDAO.deleteStaff(staffId);
	}

	public Staff getEntityByID(Long staffId)
	{
		Staff m = staffDAO.getStaffById(staffId);
		return m;
	}
	
	@Transactional(readOnly = false)
	public void updateEntityByParams(String... params)
	{
		Staff staff = getEntityByID(new Long((String)params[0]));
		
		// fine 
		staff.setRetired(false);
		staff.setUnemployed(false);		
		
		staffDAO.saveOrUpdateStaff(staff);
		
	}
	
	public boolean findEntityExists(String... params)
	{
		return staffDAO.findStaffExists(params[0], params[1], params[2]);
	}
	

	public Serializable getEntityBySecondId(Long teamId) {
		return staffDAO.getStaffBySecondId(teamId);
	}	
	

	
	/** ************************************************************************************************************
	 * 
	 * // Auto-generated method stub
	 * 
	 * 
	 * *************************************************************************************************************/
	


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
	public List<? extends Serializable> getOtherEntities() {
		return null;
	}

	public List<Staff> getEntitiesByIDAndDesc(Long idTeam, String teamCategory)
	{
		return staffDAO.listStaffsByTeam(idTeam, teamCategory);
	}

	
	public HashMap<String, Object> getHashMap(Long teamId, Integer rankId)
	{
		return null;
	}
	

	public String getString(Long teamId, Long playerId) 
	{
		return null;
	}
	
	public void doFirstJob()
	{
	}
	
	
	public void doSecondJob()
	{
	}

	@Override
	public Long getIntegerByTwoParams(Long id1, Long id2) {
		return null;
	}
	
	

}
