package com.jfootball.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jfootball.domain.Career;

public class CareerValidator implements Validator
{

	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(Career.class);
	}


	public void validate(Object obj, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "squadra", "required", "*");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stagioni", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "periodo", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "serie", "required", "*");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "presenze", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reti", "required", "*");	
	}
}
