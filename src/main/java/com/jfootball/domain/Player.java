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
@Table(name = "PLAYER")
@DataTransferObject(type = "hibernate3")
public class Player extends ImageObject {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "COMPLETE_NAME")
	private String completeName;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "BIRTH_PLACE")
	private String birthPlace;

	@Column(name = "HEIGHT")
	private String height;

	@Column(name = "WEIGHT")
	private String weight;

	@Column(name = "FOOT")
	private String foot;

	@Column(name = "VALUE")
	private String value;

	@Column(name = "DATE_CONTRACT")
	private String dateContract;

	@Column(name = "INCOME")
	private String income;

	@Column(name = "NUMBER")
	private Integer number;

	@Column(name = "CAPTAIN")
	private Boolean captain;

	@Column(name = "NATIONAL")
	private String national;

	@Column(name = "ON_LOAN")
	private Boolean onLoan;

	@Column(name = "WITHOUT_TEAM")
	private Boolean withoutTeam;

	@Column(name = "END_CAREER")
	private Boolean endCareer;

	@Column(name = "TEAM_CATEGORY")
	private String teamCategory;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_owner_id")
	private Team teamOwner;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_prev_id")
	private Team teamPrev;

	@ManyToOne(fetch = FetchType.EAGER)
	private Nation nation = new Nation();

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	private Nation nation2 = new Nation();

	@ManyToOne(fetch = FetchType.EAGER)
	private Position position = new Position();

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

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

	public Nation getNation2() {
		return nation2;
	}

	public void setNation2(Nation nation2) {
		this.nation2 = nation2;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getCaptain() {
		return captain;
	}

	public void setCaptain(Boolean captain) {
		this.captain = captain;
	}

	public Boolean getEndCareer() {
		return endCareer;
	}

	public void setEndCareer(Boolean endCareer) {
		this.endCareer = endCareer;
	}

	public Boolean getWithoutTeam() {
		return withoutTeam;
	}

	public void setWithoutTeam(Boolean withoutTeam) {
		this.withoutTeam = withoutTeam;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getDateContract() {
		return dateContract;
	}

	public void setDateContract(String dateContract) {
		this.dateContract = dateContract;
	}

	public Boolean getOnLoan() {
		return onLoan;
	}

	public void setOnLoan(Boolean onLoan) {
		this.onLoan = onLoan;
	}

	public String getTeamCategory() {
		return teamCategory;
	}

	public void setTeamCategory(String teamCategory) {
		this.teamCategory = teamCategory;
	}

	public String getEta() {
		return ProjectUtil.getAge(this.birthDate);
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