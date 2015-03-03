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

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.TeamDao;
import com.jfootball.domain.Team;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("teamDAO")
public class TeamDaoImpl extends GenericDao implements TeamDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

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
	public void saveOrUpdateTeam(Team team) {
		logger.info("Saving team " + team.getName());

		if (team.getId() != null) {
			hibernateTemplate.merge(team);
		} else {
			hibernateTemplate.saveOrUpdate(team);
		}

		logger.info("Team saved.");
	}

	/**
	 * Method to get a team list by a division
	 * 
	 * @param divisionId
	 *            the division id
	 * @return the teams found
	 */
	@SuppressWarnings("unchecked")
	public List<Team> listTeamsByDivisionForView(Long nationId, Long divisionId) {
		logger.info("Team list by division " + divisionId + " and nation "
				+ nationId + " for view");

		Session session = hibernateTemplate.getSessionFactory()
				.getCurrentSession();

		String hql = "Select new Team(id, name, nation.id, division.id) from Team"
				+ " where nation_id = "
				+ nationId
				+ " and division_id = "
				+ divisionId + " order by name";

		Query query = session.createQuery(hql);

		query.setComment("My HQL: " + hql);

		List<Team> teams = query.list();

		logger.info("Teams loaded");

		return teams;
	}

	/**
	 * Method to get a team list by a division
	 * 
	 * @param divisionId
	 *            the division id
	 * @return the teams found
	 */
	@SuppressWarnings("unchecked")
	public List<Team> listTeamsByDivision(Long nationId, Long divisionId) {
		logger.info("Team list by division " + divisionId + " and nation "
				+ nationId);

		String[] paramNames = { "nationId", "divisionId" };

		String hql = "from Team where nation_id = :nationId and division_id = :divisionId order by name";

		List<Team> teams = hibernateTemplate.findByNamedParam(hql, paramNames,
				new Object[] { nationId, divisionId });

		logger.info("Teams returned");

		return teams;
	}

	/**
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> listTeamsByName(String name) {
		logger.info("Team list by name " + name);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		Query query = session.createQuery("select distinct name from Team where name like '"	+ name + "%' order by name");
		List<String> teams = query.list();

		logger.info("Teams returned");

		return teams;
	}

	/**
	 * Method to get team by id
	 * 
	 * @param teamId
	 *            the team id
	 * @return the team found
	 */
	public Team getTeamByID(Long teamId) {
		// logger.info("Team by id " + teamId);

		Team team = hibernateTemplate.get(Team.class, teamId);

		// logger.info("Team returned");

		return team;
	}

	/**
	 * Method to get team by name
	 * 
	 * @param name
	 * @return the team found
	 */
	public Team getTeamByName(String name) {
		logger.info("Team by name " + name);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Team.class);
		Team team = (Team) criteria.add(Restrictions.eq("name", name)).uniqueResult();

		logger.info("Team returned");
		return team;
	}

	/**
	 * Method to delete a team
	 * 
	 * @param teamId
	 *            the team id
	 */
	@Transactional
	public void deleteTeam(Long teamId) {
		logger.info("Delete team " + teamId);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		session.clear();

		Team team = getTeamByID(teamId);

		hibernateTemplate.delete(team);

		logger.info("Team deleted");
	}

	@Override
	public Long getPlayersCountByDivision(Long nationId, Long divisionId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		
		BigInteger count = ((BigInteger)session.createSQLQuery("select count(*) from PLAYERS where team_id in (select id from TEAMS where division_id = " + divisionId + " and nation_id = " + nationId + ")").uniqueResult());
		return count.longValue();
	}
	
	
}