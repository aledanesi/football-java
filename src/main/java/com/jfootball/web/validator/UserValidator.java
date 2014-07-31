package com.jfootball.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jfootball.domain.Career;

public class UserValidator implements Validator
{

	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(Career.class);
	}


	public void validate(Object obj, Errors errors)
	{


	}
}
