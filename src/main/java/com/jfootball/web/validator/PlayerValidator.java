package com.jfootball.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jfootball.domain.Player;

public class PlayerValidator implements Validator
{

	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(Player.class);
	}


	public void validate(Object obj, Errors errors)
	{
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required", "*");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "required", "*");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthPlace", "required", "*");	
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "height", "required", "*");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "required", "*");	
	}
}
