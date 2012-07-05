package com.progetto.action;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.progetto.FileUploadAction;
import com.progetto.SpringHelper;
import com.progetto.domain.Career;
import com.progetto.domain.Player;
import com.progetto.domain.Team;
import com.progetto.manager.CareerManager;
import com.progetto.manager.PlayerManager;
import com.progetto.manager.TeamManager;
import com.progetto.util.ProjectUtil;

public class CareerAction extends FileUploadAction implements ServletRequestAware, ModelDriven<Career> 
{
	private static final long serialVersionUID = 1L;

	private TeamManager teamManager;
	private PlayerManager playerManager;
	private CareerManager careerManager;

	private List<Team> teamList;
	private List<Career> careerList;

	private Career career;
	private Player player;

	private ServletRequest request;

	public CareerAction() {
		teamManager = (TeamManager) SpringHelper.getBean("teamManager");
		playerManager = (PlayerManager) SpringHelper.getBean("playerManager");
		careerManager = (CareerManager) SpringHelper.getBean("careerManager");

		teamList = new ArrayList<Team>();
		careerList = new ArrayList<Career>();

		career = new Career();
	}

	@Override
	public Career getModel() {
		return career;
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * METODI DI ACTION
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/insertCareer", results = { @Result(name = "editCareer", location = "/content/secure/editCareer.jsp") })
	public String insert() {
		teamList = teamManager.listTeams();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		request.setAttribute("playerId", Long.parseLong(request.getParameter("playerId")));

		return ProjectUtil.EDIT_CAREER;
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/editCareer", results = { @Result(name = "editCareer", location = "/content/secure/editCareer.jsp") })
	public String edit() {
		career = careerManager.getCareerById(Long.parseLong(request.getParameter("id")));
		teamList = teamManager.listTeams();

		return insert();
	}

	/**
	 * To save or update career.
	 * 
	 * @return String
	 */
	@Action(value = "/saveCareer", results = {
			@Result(name = "success", type = "redirect", location = "/viewPlayer.action?id=${player.id}"),
			@Result(name = "input", location = "/content/editPlayer.jsp") }

	)
	public String save() 
	{
		Team team = teamManager.getTeamById(Long.parseLong(request.getParameter("team.id")));
		career.setTeam(team);
		
		if (userImage != null) 
		{
			byte[] image = getFileBytes();
			career.setImage(image);
		} 
		else if (career.getId() != null) 
		{
			career.setImage(careerManager.getCareerById(career.getId()).getImage());
		}

		careerManager.saveOrUpdateCareer(career);
		return SUCCESS;
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/viewCareer", results = { @Result(name = "viewCareer", location = "/content/viewCareer.jsp") })
	public String view() {
		Long idPlayer = Long.parseLong(request.getParameter("playerId"));
		Long idCareer = Long.parseLong(request.getParameter("id"));
		player = playerManager.getPlayerById(idPlayer);
		career = careerManager.getCareerById(idCareer);

		request.setAttribute("player", player);
		request.setAttribute("career", career);

		return ProjectUtil.VIEW_CAREER;
	}

	@Action(value = "/getDynamicImageCareer")
	public void getDynamicImage() {

		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		ServletOutputStream out = null;

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			career = careerManager.getCareerById(Long.parseLong(request.getParameter("id")));

			assert career.getImage() != null : "Immagine non trovata";

			out.write(getResizeImage(career.getImage()));
			out.flush();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	private byte[] getResizeImage(byte[] image) throws IOException
	{		
		BufferedImage bsrc = ImageIO.read(new ByteArrayInputStream(image));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		BufferedImage bdest = new BufferedImage(MIN_WIDTH, MIN_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bdest.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		double scalex = (double) MIN_WIDTH / bsrc.getWidth();
		double scaley = (double) MIN_HEIGHT / bsrc.getHeight();
		
		AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
		g.drawRenderedImage(bsrc, at);
		g.dispose();
		
		ImageIO.write(bdest, "JPG", baos);
		
		return baos.toByteArray();
	}	

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_
	 * 
	 * METODI GET E SET
	 * 
	 * =_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public List<Career> getCareerList() {
		return careerList;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.request = servletRequest;
	}
}
