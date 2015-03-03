package com.jfootball.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;

/**
 * Player class. The class describe the player.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "STAFFS")
@DataTransferObject(type = "hibernate3")
public class Staff extends BasePerson 
{


	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	// team data
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_prev_id")	
	private Team teamPrev;
	
	@Column(name = "TEAM_BRANCH")	
	private String teamBranch;	
	

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	
	// ********************************************************** //
	// 						team data						  	  //
	// ********************************************************** //	

	public Team getTeamPrev() {
		return teamPrev;
	}

	public void setTeamPrev(Team teamPrev) {
		this.teamPrev = teamPrev;
	}
	
	
	
	
	// ********************************************************** //
	// 						other data						  	  //
	// ********************************************************** //		
	
	public String getTeamBranch() {
		return teamBranch;
	}

	public void setTeamBranch(String teamBranch) {
		this.teamBranch = teamBranch;
	}	

	
	

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/*
	 * @Override public int hashCode() { return
	 * HashCodeBuilder.reflectionHashCode(this); }
	 */

}