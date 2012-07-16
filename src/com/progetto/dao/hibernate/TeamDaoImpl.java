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
package com.progetto.dao.hibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.InvalidStateException;
import org.hibernate.validator.InvalidValue;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.progetto.dao.TeamDao;
import com.progetto.domain.Division;
import com.progetto.domain.Team;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("teamDAO")
public class TeamDaoImpl extends HibernateDaoSupport implements TeamDao {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	/**
	 * Method to save team
	 * 
	 * @param team
	 *            the team to save
	 */
	@Override
	@Transactional
	public void saveOrUpdateTeam(Team team) {
		try {
			getHibernateTemplate().saveOrUpdate(team);
		} catch (InvalidStateException e) {
			for (InvalidValue invalidValue : e.getInvalidValues()) {
				System.out.println("Instance of bean class: "
						+ invalidValue.getBeanClass().getSimpleName()
						+ " has an invalid property: "
						+ invalidValue.getPropertyName() + " with message: "
						+ invalidValue.getMessage());
			}
		}

	}

	/**
	 * Method to list teams
	 * 
	 * @return the teams found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Team> listTeams() {
		List<Team> lista = getHibernateTemplate().find(
				"from Team t order by t.name ");
		return lista;
	}

	/**
	 * Method to list teams
	 * 
	 * @return the teams found
	 */	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Team> listTeamsByQuery(String query) 
	{
		List<Team> lista = getHibernateTemplate().findByNamedParam("from Team t where t.name like :query order by t.name ", "query", query+'%');
		return lista;
	}	
   
	/**
	 * Method to list teams
	 * 
	 * @return the teams found
	 */	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Team> listFreeTeamsBySeason(Long seasonId, Long divisionId) 
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Season.class);
		criteria.add(Restrictions.eq("seasonYear.id", seasonId));
		criteria.add(Restrictions.ne("division.id", divisionId));
		criteria.setProjection(Projections.property("team.id"));
		List<Long> listaLong = criteria.list();

		criteria = session.createCriteria(Team.class);
		criteria.addOrder(Order.asc("name"));
		if (listaLong.size() > 0)
			criteria.add(Restrictions.not(Restrictions.in("id", listaLong.toArray(new Long[listaLong.size()]))));
	
		List<Team> lista = criteria.list();

		return lista;
	}	
	
	@SuppressWarnings("unchecked")
	@Override	
	public List<Long> listSelectedTeamsBySeason(Long seasonId, Long divisionId) 
	{
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Season.class);
		criteria.add(Restrictions.eq("seasonYear.id", seasonId));
		criteria.add(Restrictions.eq("division.id", divisionId));
		criteria.setProjection(Projections.property("team.id"));
		List<Long> listaLong = criteria.list();

		return listaLong;
	}   

	/**
	 * Method to get a team list by a division
	 * 
	 * @param divisionId
	 *            the division id
	 * @return the teams found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Team> listTeamsByDivision(Long divisionId) {
		return getHibernateTemplate().findByNamedParam(
				"from Team t where division_id = :divisionId order by t.name",
				"divisionId", divisionId);
	}

	/**
	 * Method to list divisions
	 * 
	 * @return the divisions found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Division> listDivisions() {
		return getHibernateTemplate().find("from Division d order by d.id");
	}

	/**
	 * Method to get team by id
	 * 
	 * @param teamId
	 *            the team id
	 * @return the team found
	 */
	@Override
	public Team getTeamById(Long teamId) {
		return (Team) getHibernateTemplate().get(Team.class, teamId);
	}

	/**
	 * Method to delete a team
	 * 
	 * @param teamId
	 *            the team id
	 */
	@Override
	@Transactional
	public void deleteTeam(Long teamId) {
		Team team = (Team) getHibernateTemplate().get(Team.class, teamId);
		getHibernateTemplate().delete(team);
	}
}