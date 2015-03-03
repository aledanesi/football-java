package com.jfootball.util;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

/**
 * This wrapper will take any common type and use our default converters to convert them to String
 */
public class DefaultDecorator implements ColumnDecorator
{
	private static final StringConverter sc = new StringConverter();
	
	public String decorate(Object obj) throws DecoratorException
	{
		//Simply call our StringConverter utility to convert this object to String
		return (String) sc.convert(String.class, obj);
	}
}