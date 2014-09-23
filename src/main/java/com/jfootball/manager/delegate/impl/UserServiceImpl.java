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
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.UserDao;
import com.jfootball.domain.user.UserBean;
import com.jfootball.manager.delegate.BusinessService;

/**
 * @author Alessandro Danesi
 * 
 */
@Transactional(readOnly = true)
public class UserServiceImpl implements BusinessService
{
	
	private final UserDao userDAO;
	
	@Autowired
	public UserServiceImpl(UserDao userDAO) 
	{
		this.userDAO = userDAO;
	}
	
	/**
	 * @param idTeam
	 * @return
	 */
	public UserBean getUserByID(Long idUser)
	{
		return userDAO.getUserByID(idUser);
	}

	/**
	 * @param name
	 * @return
	 */
	public UserBean getUserByName(String name)
	{
		return userDAO.getUserByName(name);
	}	

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	public List<UserBean> listUsers()
	{
		return userDAO.listUsers();
	}

	/**
	 * Method to save team
	 * 
	 * @param team - the team to save
	 */
	public void saveOrUpdateUser(UserBean user)
	{
		userDAO.saveOrUpdateUser(user);
	}	

	/**
	 * @param idTeam
	 */
	public void deleteUser(Long idUser)
	{
		userDAO.deleteUser(idUser);
	}

	
	
	
	
	
	
	
	/** ************************************************************************************************************
	 * 
	 * // Auto-generated method stub
	 * 
	 * 
	 * *************************************************************************************************************/
	
	@Override
	public Serializable getEntityByID(Long id) {
		return null;
	}

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
	public List<? extends Serializable> getEntities() {
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
	public void deleteEntity(Long id) {
	}

	@Override
	public void doFirstJob() {
	}

	@Override
	public void doSecondJob() {
	}	
	

}
