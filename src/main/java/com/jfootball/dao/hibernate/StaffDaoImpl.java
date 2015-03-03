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
package com.jfootball.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.StaffDao;
import com.jfootball.domain.Staff;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("staffDAO")
public class StaffDaoImpl extends GenericDao implements StaffDao
{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	/**
	 * Method to save player
	 * 
	 * @param career the player to save
	 */
	 @Transactional
	public void saveOrUpdateStaff(Staff staff)
	{
		logger.info("Saving staff " + staff.getFirstName() + " " + staff.getLastName());

		if (staff.getId() != null)
		{
			hibernateTemplate.merge(staff);
		} else
		{
			hibernateTemplate.saveOrUpdate(staff);
		}

		logger.info("Player saved.");
	}

	/**
	 * Method to list player of a team
	 * 
	 * @param teamId
	 *            the team ask for our players
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> listStaffs()
	{
		logger.info("Staff list");

		List<Staff> staffs = hibernateTemplate.find("from Staff p order by p.lastName");

		logger.info("Staffs returned");

		return staffs;
	}
	
	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> listStaffsByTeam(Long teamId, String teamBranch)
	{

		logger.info("Staff list by team " + teamId + " and category " + teamBranch);

		List<Staff> staffs = hibernateTemplate.findByNamedParam("from Staff s where s.team.id = :idTeam and s.teamBranch = :teamBranch "
				+ "order by s.lastName", new String[] { "idTeam", "teamBranch" }, new Object[] { teamId, teamBranch });

		logger.info("Staffs returned");

		return staffs;
	}	

	/***
	 * Method to list players
	 * 
	 * @return the players found
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> listStaffsByLetter(String letter, String searchType)
	{
		logger.info("Staff list by letter "  + letter);
		
		String sql = "";
		if ("ALL".equals(searchType))
		{
			sql = "from Staff p where p.lastName like :letter order by p.lastName";			
		}
		else if ("END_CAREER".equals(searchType))
		{
			sql = "from Staff p where p.lastName like :letter and p.retired = TRUE order by p.lastName";			
		}
		else if ("WITHOUT_TEAM".equals(searchType))
		{
			sql = "from Staff p where p.lastName like :letter and p.unemployed = TRUE order by p.lastName";			
		}
		else if ("OTHERS".equals(searchType))
		{
			sql = "from Staff p where p.lastName like :letter and p.retired = FALSE order by p.lastName";			
		}
		
		logger.info("sql: "  + sql);
		
		List<Staff> staffs =  hibernateTemplate.findByNamedParam(sql, "letter", letter + "%");

		logger.info("Staff returned");

		return staffs;
	}

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */
	public Staff getStaffById(Long staffId)
	{
		Staff staff = hibernateTemplate.get(Staff.class, staffId);

		return staff;
	}
	
	public Staff getStaffBySecondId(Long teamId)
	{
		//logger.info("Staff by id " + playerId);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Staff.class);
		criteria.add(Restrictions.eq("team.id", teamId));	
		
		Staff staff = (Staff)criteria.uniqueResult();

		return staff;
	}	
	


	/**
	 * Method to delete a player
	 * 
	 * @param playerId
	 *            the playerId id
	 */
	public void deleteStaff(Long staffId)
	{
		logger.info("Delete staff " + staffId);

		Staff staff = getStaffById(staffId);

		hibernateTemplate.delete(staff);

		logger.info("Staff deleted");
	}

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	public boolean findStaffExists(String firstName, String lastName, String dateOfBirth)
	{
		boolean staffExists = false;
		
		List<Staff> staffs = hibernateTemplate.findByNamedParam(""
				+ "from Staff p "
				+ "where p.firstName = :firstName "
				+ "and p.lastName = :lastName "
				+ "and DATE_FORMAT(p.dateOfBirth, '%d/%m/%Y') = :dateOfBirth ", 
				new String[] { "firstName", "lastName", "dateOfBirth" }, new Object[] { firstName, lastName, dateOfBirth });
		
		if (staffs.size() > 0)
		{
			staffExists = true;
			
			logger.info("Staff exists!");
		}

		return staffExists;
	}	


}