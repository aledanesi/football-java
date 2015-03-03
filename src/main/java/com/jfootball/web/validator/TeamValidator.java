package com.jfootball.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jfootball.domain.Team;

/**
 * Validatore della classe Team
 * */
public class TeamValidator implements Validator
{


	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(Team.class);
	}


	public void validate(Object obj, Errors errors)
	{
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", "*");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "required", "*");
		/*
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "colorTeam", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required", "*");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stadium", "required", "*");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posti", "required", "*");	
		*/
	}
}
