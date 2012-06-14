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

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

import com.progetto.dao.DAOs;
import com.progetto.domain.User;
import com.progetto.manager.UserCredentialManager;

/**
 * @author zkessentials store
 * 
 *         This class provides a class which manages user authentication
 * 
 */
public class UserCredentialManagerImpl implements UserCredentialManager 
{

	private User user;

	private UserCredentialManagerImpl() {
	}

	public static UserCredentialManagerImpl getIntance() 
	{
		Session session = Executions.getCurrent().getSession();
		synchronized (session) 
		{
			UserCredentialManagerImpl userModel = (UserCredentialManagerImpl) session.getAttribute(KEY_USER_MODEL);
			if (userModel == null) 
			{
				session.setAttribute(KEY_USER_MODEL, userModel = new UserCredentialManagerImpl());
			}
			return userModel;
		}
	}

	public synchronized void login(String name, String password) 
	{
		User tempUser = DAOs.getUserDAO().findUserByName(name);
		if (tempUser != null && tempUser.getPassword().equals(password)) {
			user = tempUser;
		} else {
			user = null;
		}
	}

	public synchronized void logOff() {
		this.user = null;
	}

	public synchronized User getUser() {
		return user;
	}

	public synchronized boolean isAuthenticated() {
		return user != null;
	}

}
