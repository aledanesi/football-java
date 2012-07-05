package com.progetto;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport
{

	protected File userImage;
	private String userImageContentType;
	private String userImageFileName;

	final int RESIZED_WIDTH = 160;
	final int RESIZED_HEIGHT = 226;
	
	final int MIN_WIDTH = 160;
	final int MIN_HEIGHT = 226;
	

	public byte[] getFileBytes() {
		
		byte[] bFile = new byte[0];		
		
        try 
        {	    
        	resizeImage(RESIZED_WIDTH, RESIZED_HEIGHT);
        	
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
	
	private void resizeImage(int targetWidth, int targetHeight) throws IOException
	{		
		BufferedImage bsrc = ImageIO.read(userImage);
		
		if (bsrc.getWidth() > MIN_WIDTH && bsrc.getHeight() > MIN_HEIGHT)
		{
			BufferedImage bdest = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bdest.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			
			double scalex = (double) targetWidth / bsrc.getWidth();
			double scaley = (double) targetHeight / bsrc.getHeight();
			
			AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
			g.drawRenderedImage(bsrc, at);
			g.dispose();
			
			ImageIO.write(bdest, "JPG", userImage);			
		}
					
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
