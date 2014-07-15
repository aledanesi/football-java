package com.jfootball.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;

/**
 * Career class. The class describe the division.
 * 
 * @author Alessandro Danesi
 */
@Entity
@Table(name = "DIVISION")
@DataTransferObject(type = "hibernate3")
public class Division implements Serializable
{

	/***************************************************************************
	 * 
	 * FIELDS
	 * 
	 **************************************************************************/

	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable=true)
	protected Long id;	
	
	@Transient
	private byte[] image;
		
	@Column(name = "NAME")
	private String name;

	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "LEVEL")
	private Long level;
	
	@Column(name = "TEAM_NUMBER")
	private Long teamNumber;

	@Column(name = "NATION_ID")
	private Long nationId;
	
	/***************************************************************************
	 * 
	 * METHODS
	 * 
	 **************************************************************************/

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}			
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Long teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Long getNationId() {
		return nationId;
	}

	public void setNationId(Long nationId) {
		this.nationId = nationId;
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