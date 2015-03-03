package com.jfootball.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable=true)
	protected Long id;
	
	@Column(name = "LAST_USER_MODIFY")
	private String lastUserModify;

	@Column(name = "LAST_TIME_MODIFY")
	private Timestamp lastTimeModify;

		
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
    public boolean isNew() {
        return (this.id == null);
    }	

	public String getLastUserModify() {
		return lastUserModify;
	}

	public void setLastUserModify(String lastUserModify) {
		this.lastUserModify = lastUserModify;
	}

	public Timestamp getLastTimeModify() {
		return lastTimeModify;
	}

	public void setLastTimeModify(Timestamp lastTimeModify) {
		this.lastTimeModify = lastTimeModify;
	}
	
}
