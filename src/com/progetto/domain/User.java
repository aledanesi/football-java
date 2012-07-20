package com.progetto.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author zkessentials store
 * 
 *         This class provides a representation of a {@code User}
 * 
 */
@Entity
@Table(name="USER")
public class User implements Serializable
{

	public class Status 
	{
		public static final String NOT_COMMITED = "N";
		public static final String BLOCKED = "N";
	}
	
	private static final long serialVersionUID = 1L;	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")	
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ROLE")
	private String role;
	
	@Column(name="STATUS")
	private String status;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="id")
	private List<Role> roles = new ArrayList<Role>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
		
}