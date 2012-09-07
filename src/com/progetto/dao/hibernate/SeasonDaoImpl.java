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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.progetto.dao.SeasonDao;
import com.progetto.domain.Season;
import com.progetto.domain.SeasonYear;

/**
 * Class of a career object
 * 
 * @author Alessandro Danesi
 */
@Repository("seasonDAO")
public class SeasonDaoImpl extends HibernateDaoSupport implements SeasonDao 
{
  
  /*************************************************
    
    METHODS
    
   **************************************************/  
  
  /**
   * Method to save career
   * 
   * @param career the career to save
   * 
   */  
  public void saveOrUpdateSeason(Season championship) 
  {    
    getHibernateTemplate().saveOrUpdate(championship);  
  }  
  
  /**
   * Method to list career of a player
   * 
   * @param idPlayer the player ask for our career
   */  
  @SuppressWarnings("unchecked")
  public List<Season> listSeason() 
  {
    return getHibernateTemplate().find("from Season s order by year asc");
  }  
  
  /**
   * Method to list career of a player
   * 
   * @param idPlayer the player ask for our career
   */  
  @SuppressWarnings("unchecked")
  public List<SeasonYear> listYears() 
  {
    return getHibernateTemplate().find("from SeasonYear s order by value desc");
  }    
  
  /**
   * Method to list season of a team
   * 
   * @param idSeason the season ask for our team
   */  
  @SuppressWarnings("unchecked")
  public List<Season> getSeasonsByYearID(Long yearID, Long divisionID)
  {
    Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    Criteria criteria = session.createCriteria(Season.class);
    criteria.add(Restrictions.eq("seasonYear.id", yearID));
    criteria.add(Restrictions.eq("division.id", divisionID));
    List<Season> lista = criteria.list();  
    
    return lista;
  }  
  
  
  /**
   * Method to get a year of player career 
   * 
   * @param idPlayer the player ask for our career
   * @return the career
   */    
  public Season getSeasonById(Long seasonID) 
  {
    return (Season)getHibernateTemplate().get(Season.class, seasonID);
  }  
  
  /**
   * Method to get a year of player career 
   * 
   * @param idPlayer the player ask for our career
   * @return the career
   */    
  public SeasonYear getSeasonYearById(Long seasonYearID) 
  {
    return (SeasonYear)getHibernateTemplate().get(SeasonYear.class, seasonYearID);
  }    
  
  /**
   * Method to save career
   * 
   * @param career the career to save
   * 
   */  
  public void delete(Long seasonID) 
  {    
    Season team = (Season)getHibernateTemplate().get(Season.class, seasonID);
    getHibernateTemplate().delete(team);
  }    

}
