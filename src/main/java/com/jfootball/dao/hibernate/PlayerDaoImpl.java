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

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.PlayerDao;
import com.jfootball.domain.Player;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("playerDAO")
public class PlayerDaoImpl extends GenericDao implements PlayerDao
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
	public void saveOrUpdatePlayer(Player player)
	{
		logger.info("Saving player " + player.getFirstName() + " " + player.getLastName());

		//Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		
		// org.hibernate.SessionException: Session is closed!

		//try
		//{
			//session.beginTransaction();
			
			if (player.getId() != null)
			{
				hibernateTemplate.merge(player);
			} else
			{
				hibernateTemplate.saveOrUpdate(player);
			}

			//session.getTransaction().commit();

		/*} 
		catch (HibernateException e)
		{
			if (session.getTransaction() != null)
				session.getTransaction().rollback();
			
			logger.error("Save player error", e);

			throw e;
		}
		finally 
		{
			session.close();
		}*/

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
	public List<Player> listPlayers()
	{
		logger.info("Player list");

		List<Player> players = hibernateTemplate.find("from Player p order by p.lastName");

		logger.info("Players returned");

		return players;
	}

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	public List<Player> listPlayersByTeamAndCategory(Long teamId, String teamBranch)
	{
		logger.info("Player list by team " + teamId + " and category " + teamBranch);

		List<Player> players = hibernateTemplate.findByNamedParam("from Player p where p.team.id = :idTeam and p.teamBranch = :teamBranch "
				+ "order by p.position, p.number asc", new String[] { "idTeam", "teamBranch" }, new Object[] { teamId, teamBranch });

		logger.info("Players returned");

		return players;
	}

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	public List<Player> listPlayersByTeam(Long teamId, String teamBranch)
	{

		logger.info("Player list by team " + teamId + " and category " + teamBranch);

		List<Player> players = hibernateTemplate.findByNamedParam("from Player p where p.team.id = :idTeam and p.teamBranch = :teamBranch "
				+ "order by p.position, p.number asc", new String[] { "idTeam", "teamBranch" }, new Object[] { teamId, teamBranch });

		logger.info("Players returned");

		return players;
	}

	/***
	 * Method to list players
	 * 
	 * @return the players found
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Player> listPlayersByLetter(String letter, String searchType)
	{
		logger.info("Player list by letter "  + letter);
		
		String sql = "";
		if ("ALL".equals(searchType))
		{
			sql = "from Player p where p.lastName like :letter order by p.lastName";			
		}
		else if ("END_CAREER".equals(searchType))
		{
			sql = "from Player p where p.lastName like :letter and p.retired = TRUE order by p.lastName";			
		}
		else if ("WITHOUT_TEAM".equals(searchType))
		{
			sql = "from Player p where p.lastName like :letter and p.unemployed = TRUE order by p.lastName";			
		}
		else if ("OTHERS".equals(searchType))
		{
			sql = "from Player p where p.lastName like :letter and p.retired = FALSE order by p.lastName";			
		}
		
		logger.info("sql: "  + sql);
		
		List<Player> players =  hibernateTemplate.findByNamedParam(sql, "letter", letter + "%");

		logger.info("Players returned");

		return players;
	}

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */
	public Player getPlayerById(Long playerId)
	{
		//logger.info("Player by id " + playerId);

		Player player = hibernateTemplate.get(Player.class, playerId);
		
		//logger.info("Team returned");

		return player;
	}

	/**
	 * Method to delete a player
	 * 
	 * @param playerId
	 *            the playerId id
	 */
	public void deletePlayer(Long playerId)
	{
		logger.info("Delete player " + playerId);

		Player player = getPlayerById(playerId);

		hibernateTemplate.delete(player);

		logger.info("Player deleted");
	}

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */
	public HashMap<String, Object> getNextId(Long teamId, Integer counter)
	{
		logger.info("Next id team " + teamId + " counter " + counter);

		HashMap<String, Object> map = new HashMap<String, Object>();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Player.class);
		criteria.add(Restrictions.eq("team.id", teamId));
		criteria.addOrder(Order.asc("position"));
		criteria.addOrder(Order.asc("number"));
		criteria.addOrder(Order.asc("dateOfBirth"));

		criteria.setFirstResult(counter);
		criteria.setMaxResults(1);

		Long nextId = 0L;
		Player player = null;

		if (criteria.uniqueResult() != null)
		{
			player = (Player) criteria.uniqueResult();
			nextId = player.getId();

			map.put("counter", counter);
			map.put("id", nextId);
			map.put("player", player.getFirstName() + " " + player.getLastName());
		} else
		{
			nextId = -1L;

			map.put("counter", counter);
			map.put("id", -1L);
			map.put("player", null);
		}
		
		logger.info("Next id returned");

		return map;
	}

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */
	public HashMap<String, Object> getPrevId(Long teamId, Integer counter)
	{
		logger.info("Prev id team " + teamId + " counter " + counter);

		HashMap<String, Object> map = new HashMap<String, Object>();

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Player.class);
		criteria.add(Restrictions.eq("team.id", teamId));
		criteria.addOrder(Order.desc("position"));
		criteria.addOrder(Order.desc("number"));
		criteria.addOrder(Order.desc("dateOfBirth"));

		criteria.setFirstResult(counter);
		criteria.setMaxResults(1);

		Long prevId = 0L;
		Player player = null;

		if (criteria.uniqueResult() != null)
		{
			player = (Player) criteria.uniqueResult();
			prevId = player.getId();

			map.put("counter", counter);
		} else
		{
			map.put("counter", -1);
		}

		map.put("id", prevId);
		map.put("player", player.getFirstName() + " " + player.getLastName());

		logger.info("Prev id returned");

		return map;
	}
	
	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	public boolean findPlayerExists(String firstName, String lastName, String dateOfBirth)
	{
		boolean playerExists = false;
		
		List<Player> players = hibernateTemplate.findByNamedParam(""
				+ "from Player p "
				+ "where p.firstName = :firstName "
				+ "and p.lastName = :lastName "
				+ "and DATE_FORMAT(p.dateOfBirth, '%d/%m/%Y') = :dateOfBirth ", 
				new String[] { "firstName", "lastName", "dateOfBirth" }, new Object[] { firstName, lastName, dateOfBirth });
		
		if (players.size() > 0)
		{
			playerExists = true;
			
			logger.info("Players exists!");
		}

		return playerExists;
	}	

	/**
	 * 
	 * chiamata alla funzione mysql 'getRank(?,?)'
	 * 
	 * 
	 * */
	public String getRank(Long teamId, Long playerId)
	{
		logger.info("Get Rank team" + teamId + " playerId " + playerId);

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		SQLQuery query = session.createSQLQuery("select getRank(?, ?)");
		query.setParameter(0, teamId);
		query.setParameter(1, playerId);
		
		String rank = query.uniqueResult().toString();
		
		logger.info("Rank returned");		

		return rank;
	}
	
	public void endSeasonJob()
	{
		logger.info("Execute endSeason job");

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();

		try
		{
			Connection conn = sessionFactory.getConnectionProvider().getConnection();
			CallableStatement cstmt = conn.prepareCall("{ call endSeasonBatch(?) }");
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date(System.currentTimeMillis()));
			
			int year = gc.get(Calendar.YEAR);
			
			cstmt.setString("param_year", "30/06/"+year);     // current year.

			cstmt.execute();
			
			logger.info("EndSeason job executed");
		} catch (SQLException e)
		{
			logger.error(e);
		}

		logger.info("EndSeason job finished");
	}	

	public void careerPlayerJob()
	{
		logger.info("Fix CareerPlayer job");

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();

		try
		{
			Connection conn = sessionFactory.getConnectionProvider().getConnection();
			CallableStatement cstmt = conn.prepareCall("{ call careerPlayerBatch() }");
			cstmt.execute();
		} catch (SQLException e)
		{
			logger.error(e);
		}
		logger.info("Fix CareerPlayer job finished");
	}

}