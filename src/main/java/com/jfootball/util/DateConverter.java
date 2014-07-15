package com.jfootball.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

/**
 * This class is converts a String to a java.util.Date
 * It is used by BeanUtils when copying properties.  
 * Registered for use in BaseAction.
 */
public class DateConverter implements Converter 
{
    private static DateFormat df = new SimpleDateFormat(DateUtil.getDatePattern());

    public Object convert(Class type, Object value) 
    {
        if (type == Date.class)
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
	
					return df.parse(s);
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
