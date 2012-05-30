package com.progetto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CareerDetail class.
 * The class describe the career of any player.
 *
 * @author Alessandro Danesi
 */

@Embeddable
public class CareerDetail implements Serializable 
{

	/*************************************************
	  
	  FIELDS
	  
	 **************************************************/
	
	// serial version UID
	private static final long serialVersionUID = 7526472295622776147L;
	
	@Column(name="PARTITE_GIOCATE")
	private String partiteGiocate;
	
	@Column(name="PARTITE_TOTALI")
	private String partiteTotali;
	
	@Column(name="MINUTI_GIOCATI")
	private String minutiGiocati;
	
	@Column(name="MINUTI_TOTALI")
	private String minutiTotali;
	
	@Column(name="SOST_FATTE")
	private String sostFatte;
	
	@Column(name="SOST_AVUTE")
	private String sostAvute;
	
	@Column(name="AMMONIZIONI")
	private String ammonizioni;
	
	@Column(name="ESPULSIONI")
	private String espulsioni;
	
	@Column(name="GIORNATE_SQUALIFICA")
	private String giornateSqualifica;
	
	
	/*************************************************
	  
	  METHODS
	  
	 **************************************************/	
	
	public String getPartiteGiocate() 
	{
		return partiteGiocate;
	}	
	public void setPartiteGiocate(String partiteGiocate) 
	{
		this.partiteGiocate = partiteGiocate;
	}
	
	
	public String getPartiteTotali() 
	{
		return partiteTotali;
	}
	public void setPartiteTotali(String partiteTotali) 
	{
		this.partiteTotali = partiteTotali;
	}
		
	
	public String getMinutiGiocati() 
	{
		return minutiGiocati;
	}
	public void setMinutiGiocati(String minutiGiocati) 
	{
		this.minutiGiocati = minutiGiocati;
	}
	
	
	public String getMinutiTotali() 
	{
		return minutiTotali;
	}
	public void setMinutiTotali(String minutiTotali) 
	{
		this.minutiTotali = minutiTotali;
	}
	
	
	public String getSostFatte() 
	{
		return sostFatte;
	}
	public void setSostFatte(String sostFatte) 
	{
		this.sostFatte = sostFatte;
	}
	
	
	public String getSostAvute() 
	{
		return sostAvute;
	}
	public void setSostAvute(String sostAvute) 
	{
		this.sostAvute = sostAvute;
	}	
	
	
	public String getAmmonizioni() 
	{
		return ammonizioni;
	}
	public void setAmmonizioni(String ammonizioni) 
	{
		this.ammonizioni = ammonizioni;
	}
	
	
	public String getEspulsioni() 
	{
		return espulsioni;
	}
	public void setEspulsioni(String espulsioni) 
	{
		this.espulsioni = espulsioni;
	}
	
	
	public String getGiornateSqualifica() 
	{
		return giornateSqualifica;
	}
	public void setGiornateSqualifica(String giornateSqualifica) 
	{
		this.giornateSqualifica = giornateSqualifica;
	}	
}
