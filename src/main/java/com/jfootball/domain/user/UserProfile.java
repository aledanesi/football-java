package com.jfootball.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfile implements Serializable
{
	
	@Id
	private Long id;

	//SQUADRE
	@Column(name = "VIEW_TEAM")
	private boolean viewTeam;
	
	@Column(name = "UPDATE_TEAM")
	private boolean updateTeam;
	
	@Column(name = "INSERT_TEAM")
	private boolean insertTeam;


	// GIOCATORI
	
	@Column(name = "VIEW_PLAYER")
	private boolean viewPlayer;
	
	@Column(name = "UPDATE_PLAYER")
	private boolean updatePlayer;
	
	@Column(name = "INSERT_PLAYER")
	private boolean insertPlayer;


	// CARRIERE
	
	@Column(name = "VIEW_CAREER")
	private boolean viewCareer;
	
	@Column(name = "UPDATE_CAREER")
	private boolean updateCareer;
	
	@Column(name = "INSERT_CAREER")
	private boolean insertCareer;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isViewTeam() {
		return viewTeam;
	}
	public void setViewTeam(boolean viewTeam) {
		this.viewTeam = viewTeam;
	}
	public boolean isUpdateTeam() {
		return updateTeam;
	}
	public void setUpdateTeam(boolean updateTeam) {
		this.updateTeam = updateTeam;
	}
	public boolean isInsertTeam() {
		return insertTeam;
	}
	public void setInsertTeam(boolean insertTeam) {
		this.insertTeam = insertTeam;
	}
	public boolean isViewPlayer() {
		return viewPlayer;
	}
	public void setViewPlayer(boolean viewPlayer) {
		this.viewPlayer = viewPlayer;
	}
	public boolean isUpdatePlayer() {
		return updatePlayer;
	}
	public void setUpdatePlayer(boolean updatePlayer) {
		this.updatePlayer = updatePlayer;
	}
	public boolean isInsertPlayer() {
		return insertPlayer;
	}
	public void setInsertPlayer(boolean insertPlayer) {
		this.insertPlayer = insertPlayer;
	}
	public boolean isViewCareer() {
		return viewCareer;
	}
	public void setViewCareer(boolean viewCareer) {
		this.viewCareer = viewCareer;
	}
	public boolean isUpdateCareer() {
		return updateCareer;
	}
	public void setUpdateCareer(boolean updateCareer) {
		this.updateCareer = updateCareer;
	}
	public boolean isInsertCareer() {
		return insertCareer;
	}
	public void setInsertCareer(boolean insertCareer) {
		this.insertCareer = insertCareer;
	}
	

}
