package com.progetto.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.progetto.dao.UserDao;
import com.progetto.domain.Role;
import com.progetto.domain.SecurityUser;
import com.progetto.domain.User;

public class UserDetailsServiceImpl implements UserDetailsService 
{    
	@Autowired 
	private UserDao dao;   
	
	@Transactional(readOnly = true)   
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException 
	{      
    
		User user = dao.findUserByName(username);     

		if (user != null) 
		{              
			// convert roles             
			List<GrantedAuthority> roles = obtainGrantedAuthority(user);             
             
			// initialize user             
			return makeSecurityUser(user, roles);         
		} 
		else 
		{             
			throw new UsernameNotFoundException("No user with username '" + username + "' found!");         
		} 
		
	}
	
	private List<GrantedAuthority> obtainGrantedAuthority(User user)
	{
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();             
		for (Role r : user.getRoles()) 
		{                 
			roles.add(new SimpleGrantedAuthority(r.getName()));             
		}  
		return roles;
	}
	
	private SecurityUser makeSecurityUser(User user, List<GrantedAuthority> roles)
	{
		SecurityUser securityUser = new SecurityUser(                 
				user.getUsername(),                 
				user.getPassword(), 
				user.getStatus() != User.Status.NOT_COMMITED, 
				user.getStatus() != User.Status.BLOCKED, 
				true, 
				true,                 
				roles             
		); 		
		securityUser.setUser(user);
		
		return securityUser;
	}
	 
}