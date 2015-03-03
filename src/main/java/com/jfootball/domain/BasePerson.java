/**
 * 
 */
package com.jfootball.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import com.jfootball.util.ProjectUtil;

/**
 * @author Alessandro Danesi
 *
 * 20/nov/201421:41:41
 */

@MappedSuperclass
public abstract class BasePerson extends ImageObject
{

	// personal data
	@Column(name = "FIRST_NAME")	
	private String firstName;
	
	@Column(name = "LAST_NAME")	
	private String lastName;
	
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "PLACE_OF_BIRTH")	
	private String placeOfBirth;
	
	// contract data
	@Column(name = "CONTRACT_UNTIL")
	private String contractUntil;
	
	@Column(name = "GROSS_WEEKLY_SALARY")
	private String grossWeeklySalary;

	@Column(name = "NET_ANNUAL_SALARY")	
	private String netAnnualSalary;	
	
	
	// team data
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")		
	private Team team;
	
	
	// nation data
	@ManyToOne(fetch = FetchType.EAGER)	
	private Nation nationality = new Nation();
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)	
	private Nation nationality2 = new Nation();
	
	
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
	
	
	// ********************************************************** //
	// 						team data						  	  //
	// ********************************************************** //	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	
	// ********************************************************** //
	// 						nation data						  	  //
	// ********************************************************** //		
	
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
	// 						flag data						  	  //
	// ********************************************************** //		

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
		return ProjectUtil.getAge(this.getDateOfBirth());
	}
	
}
