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
package com.progetto.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Season class.
 * The class describe the season.
 *
 * @author Alessandro Danesi
 */
@Entity
@Table(name="SEASON")
public class Season implements Serializable 
{
	
	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;
	
	/*************************************************
	  
	  FIELDS
	  
	 **************************************************/	

	@Id
	@GeneratedValue
	@Column(name="ID")	
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)	
	private SeasonYear seasonYear;
	
	@ManyToOne(cascade = CascadeType.MERGE)	
	private Nation nation;	
	
	@ManyToOne(cascade = CascadeType.MERGE)	
	private Division division;
	
	@ManyToOne(cascade = CascadeType.MERGE)	
	private Team team;
	
	
	
	//private Long[] teamSelected;
	@Transient 
	private Long[] arr;
	
	
	
	
	public Long[] getArr() {
		return arr;
	}
	public void setArr(Long[] arr) {
		this.arr = arr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setSeasonYear(SeasonYear seasonYear) {
		this.seasonYear = seasonYear;
	}
	public SeasonYear getSeasonYear() {
		return seasonYear;
	}	
	public Division getDivision() {
		return division;
	}
	public void setDivision(Division division) {
		this.division = division;
	}
	public Nation getNation() {
		return nation;
	}
	public void setNation(Nation nation) {
		this.nation = nation;
	}	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
 	

	/**
	 * Method to view the object fields
	 * */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Season[");
		sb.append(", season.id: " + seasonYear.getId());
		sb.append("]");
		return sb.toString();
	}	
	
	
}
