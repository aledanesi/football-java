package com.jfootball.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jfootball.dao.UserDao;
import com.jfootball.domain.user.RoleBean;
import com.jfootball.domain.user.UserBean;
import com.jfootball.domain.user.UserLogged;

public class UserService implements UserDetailsService 
{

	protected Logger log = Logger.getLogger(this.getClass());

	private final UserDao userDao;

	@Autowired
	public UserService(UserDao userDao) 
	{
		this.userDao = userDao;
	}

	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException 
	{
		log.info("--------------------- User Login --------------------- ");

		UserBean user = userDao.findUserByName(username);
		
		if (user != null) {
			// convert roles
			List<GrantedAuthority> roles = obtainGrantedAuthority(user);
			
			// save user logged
			UserLogged userLogged = new UserLogged();
			userLogged.setUserId(user.getId());
			userLogged.setTimeLogged(new Timestamp(System.currentTimeMillis()));
			
			userDao.saveOrUpdateUserLogged(userLogged);

			// initialize user
			log.info("User '" + user.getUsername() + "' logged!");

			return makeSecurityUser(user, roles);
		} else {			
			log.error("No user with username '" + username + "' found!");
			throw new UsernameNotFoundException("No user with username '" + username + "' found!");
		}
	}

	private List<GrantedAuthority> obtainGrantedAuthority(UserBean user) 
	{
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		for (RoleBean r : user.getRoles()) {
			roles.add(new SimpleGrantedAuthority(r.getName()));
		}
		return roles;
	}

	private UserDetails makeSecurityUser(UserBean user, List<GrantedAuthority> roles) 
	{
		UserDetails userDetails = new MyUserDetails(user, roles);

		return userDetails;
	}
	
	public static class MyUserDetails implements UserDetails {
	    private UserBean user;
	    private List<GrantedAuthority> roles;

	    public MyUserDetails(UserBean user, List<GrantedAuthority> roles) {
	      this.user = user;
	      this.roles = roles;
	    }

	    public List<GrantedAuthority> getAuthorities() {
	      return roles;
	    }

	    public String getPassword() {
	      return user.getPassword();
	    }

	    public String getUsername() {
	      return user.getUsername();
	    }
	    
	    public UserBean getUser() {
			return user;
		}

		public void setUser(UserBean user) {
			this.user = user;
		}

		public boolean isEnabled() {
	    	return true;
	    }
	    
	    public boolean isAccountNonLocked() {
	    	return true;
	    }
	    
	    public boolean isAccountNonExpired() {
	    	return true;
	    }
	    
	    public boolean isCredentialsNonExpired() {
	    	return true;
	    }

	  }	

}