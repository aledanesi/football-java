package com.jfootball;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author Alessandro Danesi
 *
 * 15/feb/2014 23:31
 */
public class Constant {

	public static final String FIRST_TEAM  = "1";
	public static final String SECOND_TEAM = "2";
	public static final String YOUNG_TEAM  = "3";	
	public static final String OTHER_PLRS  = "4";
	
	
	public static final String DEFAULT_DIVISION = "1";
	public static final String DEFAULT_NATION = "11";
	public static final String DEFAULT_CONTINENT = "1";

	public static final String USER_IN_SESSION  = "user_in_session";
	
	public static final GrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
	public static final GrantedAuthority USER = new SimpleGrantedAuthority("ROLE_USER");	

}
