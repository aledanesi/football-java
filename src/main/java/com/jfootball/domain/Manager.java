package com.jfootball.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Player class. The class describe the player.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "MANAGER")
public class Manager extends ImageObject implements Serializable {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;


	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "BIRTH_PLACE")
	private String birthPlace;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Nation nation = new Nation();

	@OneToOne
	@JoinColumn(name="team_id", referencedColumnName="id", nullable=true)          
    private Team team;   
    
	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/
	
	 public Team getTeam() 
	 { 
		 return team; 
	 } 
	 
	 public void setTeam(Team team) 
	 {
		 this.team = team; 
	 }
	 

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if (lastName != null) {
			lastName = lastName.toUpperCase();
		}
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
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
	
	@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}
}