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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.CareerDao;
import com.jfootball.domain.Career;
import com.jfootball.util.ProjectUtil;

/**
 * Class of a career object
 * 
 * @author Alessandro Danesi
 */
@Repository("careerDAO")
public class CareerDaoImpl extends GenericDao implements CareerDao
{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	@Transactional
	public void saveOrUpdateCareer(Career career)
	{
		logger.info("Saving career...");

		if (career.getId() != null)
		{
			hibernateTemplate.merge(career);
		} else
		{
			if (career.getStagioni() == null || "".equals(career.getStagioni()) || !(StringUtils.isNumeric(career.getStagioni())))
				career.setStagioni("1");

			int stagioni = Integer.parseInt(career.getStagioni());

			if ("1".equals(stagioni))
			{
				hibernateTemplate.saveOrUpdate(career);
			} else
			{
				for (int i = 0; i < stagioni; i++)
				{
					Career bean = new Career();
					bean.setId(null);
					bean.setPlayer(career.getPlayer());
					bean.setSquadra(career.getSquadra());
					bean.setStagioni(career.getStagioni());
					bean.setPeriodo(career.getPeriodo());
					bean.setSerie(career.getSerie());
					bean.setPresenze("?");
					bean.setReti("?");

					hibernateTemplate.saveOrUpdate(bean);

					career.setPeriodo(ProjectUtil.getNextSeason(career.getPeriodo()));
				}
			}

		}
		logger.info("Career saved.");

	}

	@SuppressWarnings("unchecked")
	public List<Career> listCareer(Long idPlayer)
	{
		return hibernateTemplate.find("from Career c where c.player.id = " + idPlayer + " order by periodo asc");
	}

	public Career getCareerByID(Long careerId)
	{
		return (Career) hibernateTemplate.get(Career.class, careerId);
	}

	public void deleteCareer(Long careerId)
	{
		Career career = getCareerByID(careerId);

		hibernateTemplate.delete(career);
	}

}
