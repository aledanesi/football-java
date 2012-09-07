package com.progetto.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;

/**
 * Team class. The class describe the team.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "TEAM")
public class Team implements Serializable {

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

	@Column(name = "NAME")
	private String name;

	@Column(name = "CITY")
	private String city;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STADIUM")
	private String stadium;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "POSTI")
	private String posti;

	@Column(name = "COLOR_TEAM")
	private String colorTeam;

	@ManyToOne
	private Nation nation = new Nation();

	@OneToOne
	private Division division = new Division();

	@Column(name = "IMAGE")
	private byte[] image;
	
	//@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	//@JoinColumn(name="team_id")
	//private List<Player> players = new ArrayList<Player>(); 	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosti() {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALIAN);
		if (!StringUtils.isEmpty(posti) && StringUtils.isNumeric(posti)) {
			posti = nf.format(new Integer(posti));
		}
		return posti;
	}

	public void setPosti(String posti) {
		this.posti = posti;
	}

	public String getColorTeam() {
		return colorTeam;
	}

	public void setColorTeam(String colorTeam) {
		this.colorTeam = colorTeam;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	/*public List<Player> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<Player> players)
	{
		this.players = players;
	}*/

	/**
	 * Method to view the object fields
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Team[");
		sb.append("id: " + id);
		sb.append(", name: " + name);
		sb.append(", city: " + city);
		sb.append(", address: " + address);
		sb.append(", stadium: " + stadium);
		sb.append(", email: " + email);
		sb.append(", phone: " + phone);
		sb.append(", posti: " + posti);
		sb.append(", colorTeam: " + colorTeam);
		if (division != null)
			sb.append(", divisionId: " + division.getId());
		if (nation != null) 
			sb.append(", nationId: " + nation.getId());
		sb.append("]");
		return sb.toString();
	}
}