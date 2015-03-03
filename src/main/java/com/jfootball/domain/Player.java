package com.jfootball.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PLAYERS")
@DataTransferObject(type = "hibernate3")
public class Player extends BasePerson {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	// personal data
	
	@Column(name = "COMPLETE_NAME")	
	private String completeName;
	
	
	@Column(name = "COST")	
	private String cost;
	
	

	// team data
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_owner_id")		
	private Team teamOwner;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_prev_id")	
	private Team teamPrev;
	
	
	
	// nation data
	@Column(name = "NATIONAL")	
	private String national;
	
	
	// other data
	@Column(name = "NUMBER")		
	private Integer number;
	
	@Column(name = "HEIGHT")	
	private String height;
	
	@Column(name = "WEIGHT")	
	private String weight;
	
	@Column(name = "FOOT")
	private String foot;	
	
	@Column(name = "STATUS")
	private String status;		

	@Column(name = "TEAM_BRANCH")	
	private String teamBranch;

	@ManyToOne(fetch = FetchType.EAGER)	
	private Position position = new Position();



	// flag data
	@Column(name = "CAPTAIN")			
	private Boolean captain;
	
	@Column(name = "ON_LOAN")	
	private Boolean onLoan;

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	// ********************************************************** //
	// 						personal data						  //
	// ********************************************************** //

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	
	
	// ********************************************************** //
	// 						team data						  	  //
	// ********************************************************** //	

	public Team getTeamOwner() {
		return teamOwner;
	}

	public void setTeamOwner(Team teamOwner) {
		this.teamOwner = teamOwner;
	}

	public Team getTeamPrev() {
		return teamPrev;
	}

	public void setTeamPrev(Team teamPrev) {
		this.teamPrev = teamPrev;
	}


	
	// ********************************************************** //
	// 						nation data						  	  //
	// ********************************************************** //		
	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}
	
	
	
	// ********************************************************** //
	// 						other data						  	  //
	// ********************************************************** //		
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}
	
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getTeamBranch() {
		return teamBranch;
	}

	public void setTeamBranch(String teamBranch) {
		this.teamBranch = teamBranch;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


	// ********************************************************** //
	// 						flag data						  	  //
	// ********************************************************** //		

	public Boolean getCaptain() {
		return captain;
	}

	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

	public Boolean getOnLoan() {
		return onLoan;
	}

	public void setOnLoan(Boolean onLoan) {
		this.onLoan = onLoan;
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