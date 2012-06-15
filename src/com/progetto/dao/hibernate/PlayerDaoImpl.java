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
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.progetto.dao.PlayerDao;
import com.progetto.domain.Player;

/**
 * @author C_ICTDNS
 * 
 */
@Repository("playerDAO")
public class PlayerDaoImpl extends HibernateDaoSupport implements PlayerDao {

 
 
	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	/**
	 * Method to save player
	 * 
	 * @param career
	 *            the player to save
	 */
	@Override
	public void saveOrUpdatePlayer(Player player) {
		getHibernateTemplate().saveOrUpdate(player);
	}

	/**
	 * Method to list player of a team
	 * 
	 * @param teamId
	 *            the team ask for our players
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Player> listPlayers() {
		return getHibernateTemplate().find("from Player p order by p.lastName");
	}

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Player> listPlayersByTeam(Long idTeam) {
		return getHibernateTemplate()
				.findByNamedParam(
						"from Player p where p.teamId = :idTeam order by p.position, p.lastName",
						"idTeam", idTeam);
	}

	/**
	 * Method to list players
	 * 
	 * @return the players found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Player> listPlayersByLetter(String letter) {
		return getHibernateTemplate()
				.findByNamedParam(
						"from Player p where p.lastName like :letter order by p.lastName",
						"letter", letter + "%");
	}

	/**
	 * Method to get a player
	 * 
	 * @param playerId
	 *            the playerId id
	 * @return the players found
	 */
	@Override
	public Player getPlayerById(Long playerId) {
		return (Player) getHibernateTemplate().get(Player.class, playerId);
	}

	/**
	 * Method to delete a player
	 * 
	 * @param playerId
	 *            the playerId id
	 */
	@Override
	public void deletePlayer(Long playerId) {
		Player player = (Player) getHibernateTemplate().get(Player.class, playerId);
		getHibernateTemplate().delete(player);
	}
}