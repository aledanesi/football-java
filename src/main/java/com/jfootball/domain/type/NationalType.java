package com.jfootball.domain.type;

public enum NationalType 
{
	NATIONAL_U18(1),
	NATIONAL_U19(2),
	NATIONAL_U20(3),
	NATIONAL_U21(4),
	NATIONAL(5),
	RETIRED(6);
	
	private int value;
	
	public int getValue() {
		return value;
	}

	NationalType(int value){
		this.value = value;
	}
}
