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
package com.jfootball.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.validator.NotEmpty;

/**
 * Simple JavaBean domain object representing the career of a player.
 * 
 * @author Alessandro Danesi
 * 
 */
@Entity
@Table(name = "CAREERS")
@DataTransferObject(type = "hibernate3")
public class Career extends BaseEntity
{

	/*************************************************
	 * FIELDS
	 **************************************************/

	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;
	

	@Column(name = "SQUADRA")
	@NotEmpty
	private String squadra;

	@Transient
	private String stagioni;

	@Column(name = "PERIODO")
	private String periodo;

	@Column(name = "SERIE")
	private String serie;

	@Column(name = "PRESENZE")
	private String presenze;

	@Column(name = "RETI")
	private String reti;

	@Column(name = "ON_LOAN")
	private Boolean onLoan;	
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="player_id")          
    private Player player;   	



	/*************************************************
	 * METHODS
	 **************************************************/
	
	public String getSerie()
	{
		return serie;
	}

	public void setSerie(String serie)
	{
		this.serie = serie;
	}

	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}

	public String getStagioni() {
		return stagioni;
	}

	public void setStagioni(String stagioni) {
		this.stagioni = stagioni;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPresenze() {
		return presenze;
	}

	public void setPresenze(String presenze) {
		this.presenze = presenze;
	}

	public String getReti() {
		return reti;
	}

	public void setReti(String reti) {
		this.reti = reti;
	}
	
	public Boolean getOnLoan() {
		return onLoan;
	}

	public void setOnLoan(Boolean onLoan) {
		this.onLoan = onLoan;
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}	

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/*@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}*/
}