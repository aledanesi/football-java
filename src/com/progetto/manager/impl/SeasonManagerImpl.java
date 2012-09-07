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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.progetto.dao.SeasonDao;
import com.progetto.domain.Season;
import com.progetto.domain.SeasonYear;
import com.progetto.manager.SeasonManager;

/**
 * @author C_ICTDNS
 *
 */
@Service("seasonManager")
@Transactional(readOnly = true)
public class SeasonManagerImpl implements SeasonManager
{
  @Autowired
  private SeasonDao seasonDao;

  
  /**
   * Method to save season
   * 
   * @param career the season to save
   */  
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public void saveOrUpdateSeason(Season season)
  {
    seasonDao.saveOrUpdateSeason(season);
  }  
  
  /**
   * Method to list season of a team
   * 
   * @param idSeason the season ask for our team
   */  
  public List<Season> listSeason()
  {
    return seasonDao.listSeason();
  }
  
  /**
   * Method to list season of a team
   * 
   * @param idSeason the season ask for our team
   */  
  public List<SeasonYear> listYears()
  {
    return seasonDao.listYears();
  }  
  
  /**
   * Method to get a year of player career 
   * 
   * @param idSeason the season ask for our team
   */  
  public Season getSeasonById(Long seasonID)
  {
    return seasonDao.getSeasonById(seasonID);
  }  
  
  /**
   * Method to get a year of player career 
   * 
   * @param idSeason the season ask for our team
   */  
  public SeasonYear getSeasonYearById(Long seasonYearID)
  {
    return seasonDao.getSeasonYearById(seasonYearID);
  }   
  
  /**
   * Method to get a year of player career 
   * 
   * @param idSeason the season ask for our team
   */
  @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
  public void deleteAll(Season bean)
  {    
    Long yearID = bean.getSeasonYear().getId();
    Long divisionID = bean.getDivision().getId();
    
    
    List<Season> lista = seasonDao.getSeasonsByYearID(yearID, divisionID);
    for (Season champ: lista)
    {
      seasonDao.delete(champ.getId());
    }
  }
    
    public void setSeasonDao(SeasonDao seasonDao) {
    this.seasonDao = seasonDao;
  }

}
