package com.jfootball.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;

/**
 * Position class.
 * The class describe the position.
 *
 * @author Alessandro Danesi
 */
@Entity
@Table(name="POSITIONS")
@DataTransferObject(type = "hibernate3")
public class Position implements Serializable 
{
	
	/*************************************************
	  
	  FIELDS
	  
	 **************************************************/
	
	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;
	
	@Id
	private Long id;
	
	@Column(name="COD_RUOLO")
	private String codRuolo;

	@Column(name="DESC_RUOLO")
	private String descRuolo;

	@Column(name="COD_POSIZ")
	private String codPosizione;

	@Column(name="DESC_POSIZ")
	private String descPosizione;
	
	@Column(name="COD_CSS")
	private String codCss;
	

	
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
	
	
	public String getCodCss() 
	{
		return codCss;
	}
	public void setCodCss(String codCss) 
	{
		this.codCss = codCss;
	}
	
	
	public String getCodRuolo() {
		return codRuolo;
	}
	public void setCodRuolo(String codRuolo) {
		this.codRuolo = codRuolo;
	}
	public String getDescRuolo() {
		return descRuolo;
	}
	public void setDescRuolo(String descRuolo) {
		this.descRuolo = descRuolo;
	}
	public String getCodPosizione() {
		return codPosizione;
	}
	public void setCodPosizione(String codPosizione) {
		this.codPosizione = codPosizione;
	}
	public String getDescPosizione() {
		return descPosizione;
	}
	public void setDescPosizione(String descPosizione) {
		this.descPosizione = descPosizione;
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
