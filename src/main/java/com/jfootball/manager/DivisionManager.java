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
package com.jfootball.manager;

import java.util.List;

import com.jfootball.domain.Division;

/**
 * @author C_ICTDNS
 * 
 */
public interface DivisionManager {

	/**
	 * @return
	 */
	public List<Division> listDivisions();

	/**
	 * @param divisionId
	 * @return
	 */
	public Division getDivisionByID(Long divisionId);

	/**
	 * @param nationId
	 * @return
	 */
	public List<Division> listDivisionsByNation(Long nationId);

	/**
	 * @param division
	 */
	public void saveOrUpdateDivision(Division division);

	/**
	 * @param idDivision
	 */
	public void deleteDivision(Long idDivision);

}