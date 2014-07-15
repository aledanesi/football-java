package com.jfootball.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;

/**
 * Nation class.
 * The class describe the nation.
 *
 * @author Alessandro Danesi
 */
@Entity
@Table(name="NATION")
@DataTransferObject(type = "hibernate3")
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	protected Continent continent;		

	
	
	/*************************************************
	  
	  METHODS
	  
	 **************************************************/	
	
	/**
	 * Default Constructor
	 * */
	public Nation()
	{
	}
	
	/**
	 * Other Constructor
	 * */
	public Nation(Long id, String name, String value)
	{
		this.id = id;
		this.name = name;		
		this.value = value;		
	}
	
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
	
	public Continent getContinent()
	{
		return continent;
	}

	public void setContinent(Continent continent)
	{
		this.continent = continent;
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
