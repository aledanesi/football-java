package com.jfootball.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;

import com.jfootball.Constant;
import com.jfootball.util.ProjectUtil;

/**
 * Team class. The class describe the team.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "TEAM")
@DataTransferObject(type = "hibernate3")
public class Team extends ImageObject
{

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/


	@Column(name = "NAME")
	private String name;

	@Column(name = "FOUNDED")
	private String founded;

	@Column(name = "CITY")
	private String city;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "WEB_SITE")
	private String webSite;

	@Column(name = "STADIUM")
	private String stadium;

	@Column(name = "POSTI")
	private String posti;

	@Column(name = "COLOR_TEAM")
	private String colorTeam;

	
	@ManyToOne(fetch = FetchType.EAGER)
	private Nation nation = new Nation();

	@OneToOne(fetch = FetchType.EAGER)
	private Division division = new Division();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="team", fetch = FetchType.LAZY)
	@OrderBy("position, value desc")
    private Set<Player> players;
	
	

	/**
	 * Default Constructor
	 * */
	public Team()
	{
		this(new Long(Constant.DEFAULT_NATION), new Long(Constant.DEFAULT_DIVISION));
	}
	
	/**
	 * Other Constructor
	 * */
	public Team(Long nationId, Long divisionId)
	{
		this.nation.setId(nationId);
		this.division.setId(divisionId);		
	}	
	
	/**
	 * Other Constructor
	 * */
	public Team(Long continentId, Long nationId, Long divisionId)
	{
		this.nation.setId(nationId);
		this.nation.getContinent().setId(continentId);	
		this.division.setId(divisionId);		
	}	
	
	/**
	 * Other Constructor
	 * */
	public Team(Long teamId, String name, Long nationId, Long divisionId)
	{
		this(nationId, divisionId);
		this.id = teamId;
		this.name = name;		
	}	
	

	/***************************************************************************
	 * 
	 * GETTER AND SETTER METHODS
	 * 
	 **************************************************************************/


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getStadium()
	{
		return stadium;
	}

	public void setStadium(String stadium)
	{
		this.stadium = stadium;
	}
	
	public String getFounded()
	{
		return founded;
	}

	public void setFounded(String founded)
	{
		this.founded = founded;
	}	

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getWebSite()
	{
		return webSite;
	}

	public void setWebSite(String webSite)
	{
		this.webSite = webSite;
	}

	public String getPosti()
	{
		return ProjectUtil.getSeats(posti);
	}

	public void setPosti(String posti)
	{
		this.posti = posti;
	}

	public String getColorTeam()
	{
		return colorTeam;
	}

	public void setColorTeam(String colorTeam)
	{
		this.colorTeam = colorTeam;
	}

	public Nation getNation()
	{
		return nation;
	}

	public void setNation(Nation nation)
	{
		this.nation = nation;
	}

	public Division getDivision()
	{
		return division;
	}

	public void setDivision(Division division)
	{
		this.division = division;
	}
	
    public Set<Player> getPlayers() {
        return players;
    }   
    
    public void setPlayers(Set<Player> players) {
        this.players = players;
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