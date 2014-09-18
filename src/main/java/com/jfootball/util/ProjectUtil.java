/**
 * 
 */
package com.jfootball.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

/**
 * @author Alessandro Danesi
 *
 * 28/mar/2014 21:44:11
 */
public class ProjectUtil {

	public static String getAge(Date birthDate)
	{
		long msYear = 1000L * 60 * 60 * 24 * 365;

		Calendar firstDate = Calendar.getInstance();
		firstDate.setTime(birthDate);
		
		Calendar secondDate = Calendar.getInstance();
		secondDate.setTime(new Date(System.currentTimeMillis()));
		
		long msDiff = secondDate.getTimeInMillis() - firstDate.getTimeInMillis();
		
		int yearDiff = (int)(msDiff / msYear);
		
		firstDate.add(Calendar.YEAR, yearDiff);
		
		if(firstDate.after(secondDate))
			yearDiff--;
		
		return String.valueOf(yearDiff);
	}
	
	public static String getSeats(String posti)
	{
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALIAN);
		if (!StringUtils.isEmpty(posti) && StringUtils.isNumeric(posti))
		{
			posti = nf.format(new Integer(posti));
		}
		return posti;
	}	
	
	public static String getNationalDesc(String code, String nation)
	{
		nation = getNationCapitalize(nation);
		
		String description = "Attualmente in nazionale";
		
		Integer codice = Integer.parseInt(code);
		
		switch (codice.intValue()) {

			case 0:
				description = "Attualmente non fa parte di una nazionale";				
				break;
	
			case 1:
				description += " U18: " + nation;
				break;

			case 2:
				description += " U19: " + nation;
				break;
				
			case 3:
				description += " U20: " + nation;
				break;

			case 4:
				description += " U21: " + nation;
				break;
				
			case 5:
				description += ": " + nation;				
				break;

			case 6:
				description = "Ritirato dalla nazionale";				
				break;

			default:
				description = "";				
				break;
		}
		return description;
	}
	
	public static String getNationCapitalize(String nation)
	{
		if (nation == null || nation.length() == 0)
			return "";
		
		return WordUtils.capitalizeFully(nation);
	}
	
	public static String getNextSeason(String season)
	{
		String year1 = ""+ (Integer.parseInt(season.substring(0, 4)) + 1);
		
		String year2 = ""+ (Integer.parseInt(season.substring(5, 9)) + 1);
		
		return year1 + "-" + year2;
	}
	
	public static String getShortCurrencyValue(String value)
	{
		if ("".equals(value))
			value = "?";
		
		if ("?".equals(value))
			return value;
		
		DecimalFormat dFormat = new DecimalFormat();
		
		Long n = 0L;
		try 
		{
			n = (Long)dFormat.parse(value);
		} 
		catch (ParseException e) 
		{
			n = 0L;
		}

		return coolFormat(n.longValue(), 0);
	}
	
	public static String getCurrencyValue(double value)
	{
		NumberFormat currencyFormat = NumberFormat.getInstance();
		return currencyFormat.format(value);
	}	
	
	
	public static String getSymbol(String language, String country)
	{
		Locale locale = new Locale(language, country);
		Currency c  = Currency.getInstance(locale);
		return c.getSymbol(locale);
	}	
	
	private static String coolFormat(double n, int iteration) {
		char[] c = new char[]{'K', 'M', 'B', 'T'};
		
	    double d = ((long) n / 100) / 10.0;
	    boolean isRound = (d * 10) %10 == 0;//
	    return (d < 1000? 
	        ((d > 99.9 || isRound || (!isRound && d > 9.99)? 
	         (int) d * 10 / 10 : d + "" 
	         ) + "" + c[iteration]) 
	        : coolFormat(d, iteration+1));

	}	
}
