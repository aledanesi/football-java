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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.progetto.dao.CareerDao;
import com.progetto.dao.TeamDao;
import com.progetto.domain.Career;
import com.progetto.domain.Player;
import com.progetto.domain.Team;
import com.progetto.manager.CareerManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("careerManager")
@Transactional(readOnly = true)
public class CareerManagerImpl implements CareerManager
{
	@Autowired
	private CareerDao careerDAO;
	
	@Autowired
	private TeamDao teamDAO;	

	
	public Career getCareerById(long idCareer)
	{
		return careerDAO.getCareerById(idCareer);
	}
	
	public List<Career> listCareer(long idPlayer)
	{
		return careerDAO.listCareer(idPlayer);
	}	
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void saveOrUpdateCareer(Career career)
	{
		careerDAO.saveOrUpdateCareer(career);	
	}
	
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void batchCareer(Player player)
    {
    	Team team = teamDAO.getTeamById(player.getTeamId());
    	Career career = new Career();
    	career.setPlayer(player);
    	career.setSerie("SERIE A");
    	career.setStagione("2011-2012");
    	career.setTeam(team);
    	//career.setImage(player.getImage());?  
    	careerDAO.saveOrUpdateCareer(career);
    	System.out.println("ANNO CARRIERA SALVATO...");
    }	
    
    public void setCareerDAO(CareerDao careerDAO) {
		this.careerDAO = careerDAO;
	}
	
    public void setTeamDAO(TeamDao teamDAO) {
    	this.teamDAO = teamDAO;
	}

}
