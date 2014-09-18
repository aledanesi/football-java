package com.jfootball.domain;

import java.util.Date;

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

import com.jfootball.util.ProjectUtil;

/**
 * Player class. The class describe the player.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "PLAYERS")
@DataTransferObject(type = "hibernate3")
public class Player extends ImageObject {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	// personal data
	@Column(name = "FIRST_NAME")	
	private String firstName;
	
	@Column(name = "LAST_NAME")	
	private String lastName;
	
	@Column(name = "COMPLETE_NAME")	
	private String completeName;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "PLACE_OF_BIRTH")	
	private String placeOfBirth;
	
	
	
	// contract data
	@Column(name = "CONTRACT_UNTIL")
	private String contractUntil;
	
	@Column(name = "NET_WEEKLY_SALARY")
	private String netWeeklySalary;
	
	@Column(name = "NET_ANNUAL_SALARY")	
	private String netAnnualSalary;
	
	@Column(name = "GROSS_WEEKLY_SALARY")
	private String grossWeeklySalary;

	@Column(name = "GROSS_ANNUAL_SALARY")
	private String grossAnnualSalary;
	
	@Column(name = "COST")	
	private String cost;
	
	

	// team data
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")		
	private Team team;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_owner_id")		
	private Team teamOwner;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_prev_id")	
	private Team teamPrev;
	
	
	
	// nation data
	@Column(name = "NATIONAL")	
	private String national;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	private Nation nationality = new Nation();
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)	
	private Nation nationality2 = new Nation();
	
	
	
	// other data
	@Column(name = "NUMBER")		
	private Integer number;
	
	@Column(name = "HEIGHT")	
	private String height;
	
	@Column(name = "WEIGHT")	
	private String weight;
	
	@Column(name = "FOOT")
	private String foot;	

	@Column(name = "TEAM_BRANCH")	
	private String teamBranch;

	@ManyToOne(fetch = FetchType.EAGER)	
	private Position position = new Position();



	// flag data
	@Column(name = "CAPTAIN")			
	private Boolean captain;
	
	@Column(name = "ON_LOAN")	
	private Boolean onLoan;

	@Column(name = "UNEMPLOYED")	
	private Boolean unemployed;

	@Column(name = "RETIRED")	
	private Boolean retired;



	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	// ********************************************************** //
	// 						personal data						  //
	// ********************************************************** //
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	
	
	// ********************************************************** //
	// 						contract data						  //
	// ********************************************************** //
	public String getContractUntil() {
		return contractUntil;
	}

	public void setContractUntil(String contractUntil) {
		this.contractUntil = contractUntil;
	}
	
	public String getNetWeeklySalary() {
		return netWeeklySalary;
	}

	public void setNetWeeklySalary(String netWeeklySalary) {
		this.netWeeklySalary = netWeeklySalary;
	}
	
	public String getNetAnnualSalary() {
		return netAnnualSalary;
	}

	public void setNetAnnualSalary(String netAnnualSalary) {
		this.netAnnualSalary = netAnnualSalary;
	}	

	public String getGrossWeeklySalary() {
		return grossWeeklySalary;
	}

	public void setGrossWeeklySalary(String grossWeeklySalary) {
		this.grossWeeklySalary = grossWeeklySalary;
	}

	public String getGrossAnnualSalary() {
		return grossAnnualSalary;
	}

	public void setGrossAnnualSalary(String grossAnnualSalary) {
		this.grossAnnualSalary = grossAnnualSalary;
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
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

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
	
	public Nation getNationality() {
		return nationality;
	}

	public void setNationality(Nation nationality) {
		this.nationality = nationality;
	}

	public Nation getNationality2() {
		return nationality2;
	}

	public void setNationality2(Nation nationality2) {
		this.nationality2 = nationality2;
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

	public Boolean getRetired() {
		return retired;
	}

	public void setRetired(Boolean retired) {
		this.retired = retired;
	}

	public Boolean getUnemployed() {
		return unemployed;
	}

	public void setUnemployed(Boolean unemployed) {
		this.unemployed = unemployed;
	}






	public String getAge() {
		return ProjectUtil.getAge(this.dateOfBirth);
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