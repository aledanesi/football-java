package com.jfootball.domain.type;

public enum FootType 
{

	RIGHT(1),
	LEFT(2),
	BOTH(3);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	FootType(int value){
		this.value = value;
	}
	
}
