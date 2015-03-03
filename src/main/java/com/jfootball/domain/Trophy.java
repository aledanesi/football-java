/**
 * 
 */
package com.jfootball.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;

/**
 * @author Alessandro Danesi
 *
 * 10/gen/201500:11:34
 */
@Entity
@Table(name = "TROPHY")
@DataTransferObject(type = "hibernate3")
public class Trophy
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable=true)
	protected Long id;	
	
	@Column(name = "NAME")
	private String name;

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
	
}
