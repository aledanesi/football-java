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
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Career class.
 * The class describe the career of any player.
 *
 * @author Alessandro Danesi
 */
@Entity
@Table(name="CAREER")
public class Career implements Serializable 
{
	
	/*************************************************
	  
	  FIELDS
	  
	 **************************************************/
	
	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;

	@Id
	@GeneratedValue
	@Column(name="ID")	
	private Long id;
	
	@Column(name="GOAL")
	private String goal;
	
	@ManyToOne(cascade = CascadeType.MERGE)	
	private SeasonYear stagione = new SeasonYear();
	
	@ManyToOne(cascade = CascadeType.MERGE)	
	private Player player = new Player();
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Team team;
	
	@Column(name="SERIE")
	private String serie;
	
	@Column(name="IMAGE")
	private byte[] image;	
	
	
	@Embedded 
	private CareerDetail dettaglioCarriera;		
	
	/*************************************************
	  
	  METHODS
	  
	 **************************************************/	
	

	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}		
	
	public Player getPlayer() 
	{
		return player;
	}
	public void setPlayer(Player player) 
	{
		this.player = player;
	}
		
	public SeasonYear getStagione() 
	{
		return stagione;
	}
	public void setStagione(SeasonYear stagione) 
	{
		this.stagione = stagione;
	}
		
	public Team getTeam() 
	{
		return team;
	}
	public void setTeam(Team team) 
	{
		this.team = team;
	}
	
	
	public String getSerie() 
	{
		return serie;
	}
	public void setSerie(String serie) 
	{
		this.serie = serie;
	}
		
	public String getGoal() 
	{
		return goal;
	}
	public void setGoal(String goal) 
	{
		this.goal = goal;
	}
	
	public CareerDetail getDettaglioCarriera() 
	{
		return dettaglioCarriera;
	}
	public void setDettaglioCarriera(CareerDetail dettaglioCarriera) 
	{
		this.dettaglioCarriera = dettaglioCarriera;
	}		
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	/**
	 * Method to view the object fields
	 * */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Career[");
		sb.append("id: " + id);
		sb.append(", player.id: " + player);
		sb.append(", stagione: " + stagione);
		sb.append(", sost fatte: " + dettaglioCarriera.getSostFatte());
		sb.append(", sost avute: " + dettaglioCarriera.getSostAvute());	
		sb.append(", team: " + team);
		sb.append(", serie: " + serie);
		sb.append(", goal: " + goal);
		sb.append("]");
		return sb.toString();
	}
}