package com.jfootball.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class ImageObject extends BaseEntity implements Serializable
{

	@Transient
	private byte[] image;
		
	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}	

}
