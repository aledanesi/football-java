package com.progetto;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport
{

	protected File userImage;
	private String userImageContentType;
	private String userImageFileName;

	public byte[] getFileBytes() {
		
		byte[] bFile = new byte[0];
        try 
        {	    
			BufferedImage bsrc = ImageIO.read(userImage);
			BufferedImage bdest = new BufferedImage(160, 226, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bdest.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance((double) 160 / bsrc.getWidth(), (double) 226 / bsrc.getHeight());
			g.drawRenderedImage(bsrc, at);			
			
			ImageIO.write(bdest, "JPG", userImage);	

			FileInputStream fileInputStream = new FileInputStream(userImage);
			bFile = new byte[(int) userImage.length()];

			//convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		    
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }	
		return bFile;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

}