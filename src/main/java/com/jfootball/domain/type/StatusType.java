package com.jfootball.domain.type;

public enum StatusType 
{
	SUMMER_SIGNING(1),
	WINTER_SIGNING(2),
	RETURN_FROM_LOAN(3);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	StatusType(int value){
		this.value = value;
	}
}
