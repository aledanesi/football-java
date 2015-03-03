package com.jfootball.domain.type;

public enum TeamBranchType 
{
	FIRST_TEAM(1),
	SECOND_TEAM(2),
	YOUTH_TEAM(3),
	OTHER_PLAYERS(4);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	TeamBranchType(int value){
		this.value = value;
	}
}
