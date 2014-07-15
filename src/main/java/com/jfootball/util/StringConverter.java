package com.jfootball.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

public class StringConverter implements Converter 
{
	private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	private static final DateFormat tf = new SimpleDateFormat("dd/MM/yyyy");
	private static final DecimalFormat formatter = new DecimalFormat("###,##0.00");

	public Object convert(Class type, Object value) 
	{
		try 
		{
			if (value == null) 
			{
				return null;
			} 
			else if (value instanceof String) 
			{
				return (StringUtils.isEmpty((String)value)?null:value);
			} 
			else if (value instanceof Timestamp) 
			{
                //We need to remove the trailing microseconds, the formatter always returns them
                //String s = tf.format(value).substring(0,19);
				//return s;
				return df.format(value);
			}
			else if (value instanceof Date) 
			{
				return df.format(value);
			}
			else if (value instanceof Double) 
			{
				return formatter.format(value);
			}
			else if (value instanceof Long) 
			{
				return ((Long)value).toString();
			}
			else if (value instanceof Integer) 
			{
				return ((Integer) value).toString();
			}
            else if (value instanceof Short) 
            {
                return ((Short) value).toString();
            }
		}
		catch (Exception e) 
		{
			throw new ConversionException("Error converting " + value.getClass().getName() + " to " + type.getName() + ": " + e);
		}


		throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
	}
}
