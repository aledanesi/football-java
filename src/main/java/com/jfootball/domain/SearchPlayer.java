/**
 * 
 */
package com.jfootball.domain;

/**
 * @author Alessandro Danesi
 *
 * 09/lug/2014 23:41:14
 */
public class SearchPlayer
{
	private String type = "OTHERS";
	private String iniziale;
	private String hiddenIniziale;
	
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getIniziale()
	{
		return iniziale;
	}
	public void setIniziale(String iniziale)
	{
		this.iniziale = iniziale;
	}
	public String getHiddenIniziale()
	{
		return hiddenIniziale;
	}
	public void setHiddenIniziale(String hiddenIniziale)
	{
		this.hiddenIniziale = hiddenIniziale;
	}
	
	
	
}
