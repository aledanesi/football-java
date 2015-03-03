package com.jfootball.domain.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * @author zkessentials store
 * 
 *         This class provides a representation of a {@code User}
 * 
 */
@Entity
@Table(name = "USERS")
public class UserBean implements Serializable
{

	public class Status
	{
		public static final String NOT_COMMITED = "N";

		public static final String BLOCKED = "B";
		public static final String ENABLE = "E";
		
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "STATUS")
	private String status;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "id")
	private List<RoleBean> roles = new ArrayList<RoleBean>();
	
	@Transient
	private UserProfile profile;
	

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<RoleBean> getRoles()
	{
		return roles;
	}

	public void setRoles(List<RoleBean> roles)
	{
		this.roles = roles;
	}
	

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfiles(UserProfile profile) {
		this.profile = profile;
	}

	public Boolean getLdapAuth()
	{
		return false;
	}
	

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/*@Override
	public int hashCode()
	{
		return HashCodeBuilder.reflectionHashCode(this);
	}*/	
}