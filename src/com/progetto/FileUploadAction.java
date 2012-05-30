package com.progetto;

import java.io.File;
import java.io.FileInputStream;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport
{

	protected File userImage;
	private String userImageContentType;
	private String userImageFileName;

	public byte[] getFileBytes() {
	
		byte[] bFile = new byte[(int) userImage.length()];
		 
        try 
        {
		    FileInputStream fileInputStream = new FileInputStream(userImage);
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
