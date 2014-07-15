/**
 * 
 */
package com.jfootball.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Alessandro Danesi
 *
 * 05/giu/2014 21:51:31
 */
public class InitServlet extends HttpServlet
{
	protected Logger logger = Logger.getLogger(this.getClass());


	@Override
	public void init() throws ServletException {
		super.init();
		
		logger.info("init di InitServlet...");
		
		Properties prop = new Properties();
	    InputStream inputStream = getServletContext().getResourceAsStream("/META-INF/maven/com.jfootball.app/JFootball/pom.properties");

		try
		{
			prop.load(inputStream);
			logger.info("pom.properties loaded");
			
			String version = (String)prop.get("version");
			logger.info("Version JFootball: " + version);			
			
			getServletContext().setAttribute("version", version);
			
		} 
		catch (FileNotFoundException e)
		{
			logger.error(e);
		}
		catch (IOException e)
		{
			logger.error(e);
		}		
				
	}

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		logger.info("service di InitServlet...");
}
	
	
	@Override
	public void destroy() {
		super.destroy();

		logger.info("destroy di InitServlet...");
}
}
