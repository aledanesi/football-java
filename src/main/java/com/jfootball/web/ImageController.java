package com.jfootball.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jfootball.domain.ImageObject;
import com.jfootball.domain.Player;
import com.jfootball.domain.Team;
import com.jfootball.manager.FootballManager;

@Controller
public class ImageController extends GenericController
{

	/*@Autowired
	public ImageController(TeamManager teamManager, PlayerManager playerManager)
	{
		this.teamManager = teamManager;
		this.playerManager = playerManager;
	}*/
	
	@Autowired
	public ImageController(FootballManager footballManager)
	{
		this.footballManager = footballManager;
	}	

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/teams/image", method = RequestMethod.GET)
	public void getImageTeam(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String teamId)
	{
		Team team = footballManager.getTeamByID(Long.parseLong(teamId));

		try
		{
			getImage(request, response, team, "team");

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/players/image", method = RequestMethod.GET)
	public void getImagePlayer(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String playerId)
	{
		Player player = footballManager.getPlayerByID(Long.parseLong(playerId));

		try
		{
			getImage(request, response, player, "player");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void getImage(HttpServletRequest request, HttpServletResponse response, ImageObject imageObject, String typeImage) throws Exception
	{
		ServletOutputStream out = null;

		String tomcat_home = System.getProperty("catalina.base") + "\\webapps\\images\\" + typeImage + "\\";

		File file = null;
		FileInputStream imageInFile = null;

		try
		{
			response.setContentType("image/jpeg");
			out = response.getOutputStream();

			file = new File(tomcat_home + imageObject.getId() + ".png");
			imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			out.write(imageData);
			imageInFile.close();

		} catch (Exception e)
		{
			file = new File(request.getSession().getServletContext().getRealPath("/") + "/images/players/notFound.jpg");
			imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			out.write(imageData);
			imageInFile.close();
		} finally
		{
			out.flush();

			if (out != null)
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}

	}

}
