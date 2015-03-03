package com.jfootball.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Timestamp;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * This class is converts a String to a java.sql.Timestamp
 * It is used by BeanUtils when copying properties.  
 * Registered for use in BaseAction.
 */
public class TimestampConverter implements Converter 
{
	private static DateFormat timestampFormatter1 = new SimpleDateFormat(DateUtil.getDateTimePattern1());
    private static DateFormat timestampFormatter2 = new SimpleDateFormat(DateUtil.getDateTimePattern2());
    private static DateFormat timestampFormatter3 = new SimpleDateFormat(DateUtil.getDateTimePattern3());
	private static DateFormat timestampFormatter4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

	public Object convert(Class type, Object value) 
	{
		if (type == Timestamp.class)
		{
			if (value == null) 
			{
				return null;
			} 
			if (value instanceof String) 
			{
                String s = ((String)value).trim();
				try 
				{
					if (StringUtils.isEmpty(s)) 
					{
						return null;
					}
                    
                    //Try formatting in first pattern
                    String tms1 = s;
                    //If the trailing zero's (seconds) are missing, add them in
                    if (tms1.length()<=16) tms1 += ":00";
                    try
                    {
                        return new Timestamp(timestampFormatter1.parse(tms1).getTime());
                    }
                    catch (ParseException ex1)
                    {
                        //Try formatting in second pattern
                        String tms2 = s;
                        //If the trailing zero's (seconds) are missing, add them in
                        if (tms2.length()<=13) tms2 += "00";
                        try
                        {
                            return new Timestamp(timestampFormatter2.parse(tms2).getTime());
                        }
                        catch (ParseException ex2)
                        {
                            //Try formatting in second pattern
                            String tms3 = s;
                            //If the trailing zero's (seconds) are missing, add them in
                            if (tms3.length()<=15) tms3 += "00";
                            try
                            {
                                return new Timestamp(timestampFormatter3.parse(tms3).getTime());
                            }
                            catch(ParseException ex3)
                            {
                                
								//Try formatting in second pattern
								String tms4 = s;
								//If the trailing zero's (seconds) are missing, add them in
								//if (tms3.length()<=15) tms3 += "00";
								try
								{
									return new Timestamp(timestampFormatter4.parse(tms4).getTime());
								}
								catch(ParseException ex4)
								{
	                                //We've tried all, now just try date conversion
	                                DateConverter dc = new DateConverter();
	                                Date d = (Date)dc.convert(Date.class, s);
	                                return new Timestamp(d.getTime());
								}
                            }
                        }
                    }
					
				} 
				catch (Exception e) 
				{
					throw new ConversionException("Error converting " + value.getClass().getName() + " to " + type.getName()+ ":"  + e);
				}
			}
		}

		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}
}
