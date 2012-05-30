package com.progetto.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Player class. The class describe the player.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	// @ManyToOne(cascade = CascadeType.MERGE)
	// private Team team = new Team();

	@Column(name = "TEAM_ID")
	private Long teamId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "BIRTH_PLACE")
	private String birthPlace;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Nation nation = new Nation();

	@ManyToOne(cascade = CascadeType.MERGE)
	private Position position = new Position();

	@Column(name = "HEIGHT")
	private String height;

	@Column(name = "WEIGHT")
	private String weight;

	@Column(name = "IMAGE")
	private byte[] image;

	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public Team getTeam() { return team; } public void setTeam(Team team) {
	 * this.team = team; }
	 */

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * Method hashCode
	 */
	@Override
	public int hashCode() {
		return this.firstName.length() + this.lastName.length();
	}

	/**
	 * Method equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player playerObj = (Player) obj;
			if (this.firstName.equals(playerObj.getFirstName())
					&& this.lastName.equals(playerObj.getLastName())
					&& this.birthPlace.equals(playerObj.getBirthDate())
					&& this.birthPlace.equals(playerObj.getBirthPlace())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to view the object fields
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Player[");
		sb.append("id: " + id);
		// sb.append(", team id: " + team.getId());
		// sb.append(", team name: " + team.getName());
		sb.append(", team id: " + teamId);
		sb.append(", firstName: " + firstName);
		sb.append(", lastName: " + lastName);
		sb.append(", birthDate: " + birthDate);
		sb.append(", nation id: " + nation.getId());
		sb.append(", nation name: " + nation.getName());
		sb.append(", position id: " + position.getId());
		sb.append(", position name: " + position.getName());
		sb.append(", height: " + height);
		sb.append(", weight: " + weight);
		sb.append("]");
		return sb.toString();
	}
}