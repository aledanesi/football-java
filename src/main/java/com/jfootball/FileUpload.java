package com.jfootball;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.jfootball.web.GenericController;

/**
 * @author Alessandro Danesi
 *
 * 17/dic/2013 11:54
 */
public class FileUpload extends GenericController {

	protected File userImage;

	private String userImageContentType;

	private String userImageFileName;

	final int RESIZED_WIDTH = 160;

	final int RESIZED_HEIGHT = 226;

	public final int MIN_WIDTH = 130;

	public final int MIN_HEIGHT = 184;

	protected Logger logger = Logger.getLogger(this.getClass());

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		// Convert multipart object to byte[]
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());

	}

	public byte[] getFileBytes() {

		byte[] bFile = new byte[0];

		try {
			resizeImage(RESIZED_WIDTH, RESIZED_HEIGHT);

			FileInputStream fileInputStream = new FileInputStream(userImage);
			bFile = new byte[(int) userImage.length()];

			// convert file into array of bytes
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}

	private void resizeImage(int targetWidth, int targetHeight)
			throws IOException {
		BufferedImage bsrc = ImageIO.read(userImage);

		if (bsrc.getWidth() < MIN_WIDTH && bsrc.getHeight() < MIN_HEIGHT) {
			targetWidth = MIN_WIDTH;
			targetHeight = MIN_HEIGHT;
		}

		BufferedImage bdest = new BufferedImage(targetWidth, targetHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bdest.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		double scalex = (double) targetWidth / bsrc.getWidth();
		double scaley = (double) targetHeight / bsrc.getHeight();

		AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
		g.drawRenderedImage(bsrc, at);
		g.dispose();

		ImageIO.write(bdest, "JPG", userImage);

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
