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

import com.jfootball.dao.UserDao;
import com.jfootball.domain.user.UserBean;
import com.jfootball.domain.user.UserLogged;
import com.jfootball.domain.user.UserProfile;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("userDAO")
public class UserDaoImpl extends GenericDao implements UserDao
{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/*************************************************
	 * METHODS
	 **************************************************/

	@SuppressWarnings("unchecked")
	public UserBean findUserByName(String username)
	{
		List<UserBean> users = hibernateTemplate.find("from UserBean WHERE username=?", username);
		
		UserBean user = (users == null || users.size() <= 0 ? null : (UserBean) users.get(0));
		
		if (user != null)
		{
			Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(UserProfile.class);
			UserProfile profile = (UserProfile) criteria.add(Restrictions.eq("userId", user.getId())).uniqueResult();			
			
			user.setProfiles(profile);
		}

		return users == null || users.size() <= 0 ? null : (UserBean) users.get(0);

	}

	/**
	 * Method to save used logged
	 * 
	 * @param userLogged the user logged to save
	 */		
	public void saveOrUpdateUserLogged(UserLogged userLogged)
	{
		try 
		{
			if (userLogged.getId() != null)
			{
				hibernateTemplate.merge(userLogged);
			} else
			{
				hibernateTemplate.saveOrUpdate(userLogged);
			}			
		}
		catch(Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		
	}
	
	/**
	 * @param idTeam
	 * @return
	 */
	public UserBean getUserByID(Long idUser)
	{
		//logger.info("User by id " + idUser);

		UserBean player = hibernateTemplate.get(UserBean.class, idUser);
		
		//logger.info("User returned");

		return player;
	}

	/**
	 * @param name
	 * @return
	 */
	public UserBean getUserByName(String name)
	{
		logger.info("User by name " + name);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(UserBean.class);
		UserBean user = (UserBean) criteria.add(Restrictions.eq("username", name)).uniqueResult();

		logger.info("Team returned");
		return user;		
	}	

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserBean> listUsers()
	{
		logger.info("User list");

		List<UserBean> users = hibernateTemplate.find("from UserBean p order by p.username");

		logger.info("Users returned");

		return users;
	}	


	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveOrUpdateUser(UserBean user)
	{
		logger.info("Saving user " + user.getUsername() + " " + user.getPassword());
		
		if (user.getId() != null)
		{
			hibernateTemplate.merge(user);
		} else
		{
			hibernateTemplate.saveOrUpdate(user);
		}

		logger.info("User saved.");		
	}	

	/**
	 * @param idTeam
	 */
	public void deleteUser(Long idUser)
	{
		logger.info("Delete user " + idUser);

		UserBean user = getUserByID(idUser);

		hibernateTemplate.delete(user);

		logger.info("User deleted");
	}	
}