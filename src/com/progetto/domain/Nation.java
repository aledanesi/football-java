package com.progetto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Nation class.
 * The class describe the nation.
 *
 * @author Alessandro Danesi
 */
@Entity
@Table(name="NATION")
public class Nation implements Serializable 
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
}
