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

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jfootball.dao.UserDao;
import com.jfootball.domain.user.UserBean;
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
			List<UserProfile> profiles = hibernateTemplate.find("from UserProfile WHERE user_id=?", user.getId());
			
			user.setProfiles(profiles);
		}

		

		return users == null || users.size() <= 0 ? null : (UserBean) users.get(0);

	}

}