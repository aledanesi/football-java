package com.progetto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Career class.
 * The class describe the division.
 *
 * @author Alessandro Danesi
 */
@Entity
@Table(name="DIVISION")
public class Division implements Serializable 
{
	
	/*************************************************
	  
	  FIELDS
	  
	 **************************************************/
	
	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;
	
	@Id
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="LEVEL")
	private String level;
	
	@Column(name="NATION_ID")
	private Long nationId;
	
	/*************************************************
	  
	  METHODS
	  
	 **************************************************/	
	
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	
	public String getValue() 
	{
		return value;
	}
	public void setValue(String value) 
	{
		this.value = value;
	}	
	
	
	public String getLevel() 
	{
		return level;
	}
	
	public void setLevel(String level) 
	{
		this.level = level;
	}
	
	
	public Long getNationId() 
	{
		return nationId;
	}
	
	public void setNationId(Long nationId) 
	{
		this.nationId = nationId;
	}
	
}