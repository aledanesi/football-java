package com.progetto.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataIntegrityViolationException;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.progetto.FileUploadAction;
import com.progetto.SpringHelper;
import com.progetto.domain.Division;
import com.progetto.domain.Nation;
import com.progetto.domain.Player;
import com.progetto.domain.Team;
import com.progetto.manager.NationManager;
import com.progetto.manager.PlayerManager;
import com.progetto.manager.TeamManager;
import com.progetto.util.ProjectUtil;

public class TeamAction extends FileUploadAction implements
		ServletRequestAware, ModelDriven<Team> {
	private static final long serialVersionUID = 1L;

	@SessionTarget
	Session session;

	@TransactionTarget
	Transaction transaction;

	private Team team;

	private Player player;

	private TeamManager teamManager;

	private PlayerManager playerManager;

	private NationManager nationManager;

	private List<Team> teamList;

	private List<Player> playerList;

	private List<Division> divisionList;

	private List<Nation> nationList;

	private ServletRequest servletRequest;

	private static final Long DEFAULT_DIVISION = 1L;

	public TeamAction() {
		teamManager = (TeamManager) SpringHelper.getBean("teamManager");
		playerManager = (PlayerManager) SpringHelper.getBean("playerManager");
		nationManager = (NationManager) SpringHelper.getBean("nationManager");

		team = new Team();
		nationList = nationManager.listNations();
		teamList = teamManager.listTeams();
		divisionList = teamManager.listDivisions();
	}

	@Override
	public Team getModel() {
		return team;
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * METODO VALIDATE
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */

	public void validate() {
		if (team.getName() != null && StringUtils.isEmpty(team.getName())) {
			addActionError("Il nome è obbligatorio.");
		}
		if (team.getPosti() != null && StringUtils.isEmpty(team.getPosti())) {
			addFieldError("team.posti", "I posti sono obbligatori.");
		}
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * METODI DI ACTION
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */
	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/insertTeam", results = { @Result(name = "editTeam", location = "/content/editTeam.jsp") })
	public String insert() {
		divisionList = teamManager.listDivisions();
		return ProjectUtil.EDIT_TEAM;
	}

	/**
	 * To save or update user.
	 * 
	 * @return String
	 */
	@Action(value = "/saveOrUpdateTeam", results = {
			@Result(name = "success", location = "/content/listTeam.jsp"),
			@Result(name = "input", location = "/content/editTeam.jsp") })
	public String saveOrUpdate() {
		if (userImage != null) {
			byte[] image = getFileBytes();
			team.setImage(image);
		} else if (team.getId() != null) {
			team.setImage(teamManager.getTeamById(team.getId()).getImage());
		}

		teamManager.saveOrUpdateTeam(team);

		return search();
	}

	/**
	 * To list all users.
	 * 
	 * @return String
	 */
	@Action(value = "/listTeam", results = { @Result(name = "success", location = "/content/listTeam.jsp") })
	public String list() {
		Long divisionId = DEFAULT_DIVISION;

		teamList = teamManager.listTeamsByDivision(divisionId);
		divisionList = teamManager.listDivisions();

		servletRequest.setAttribute("divisionId", divisionId);

		return SUCCESS;
	}

	/**
	 * To list all users.
	 * 
	 * @return String
	 */
	@Action(value = "/searchTeam", results = { @Result(name = "success", location = "/content/listTeam.jsp") })
	public String search() {
		Long divisionId = DEFAULT_DIVISION;

		if (team.getDivision().getId() != null) {
			divisionId = team.getDivision().getId();
		}

		teamList = teamManager.listTeamsByDivision(divisionId);
		divisionList = teamManager.listDivisions();
		servletRequest.setAttribute("divisionId", divisionId);
		return SUCCESS;
	}

	/**
	 * To delete a user.
	 * 
	 * @return String
	 */
	@Action(value = "/deleteTeam", results = { @Result(name = "success", location = "/content/listTeam.jsp") })
	public String delete() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			teamManager.deleteTeam(Long.parseLong(request.getParameter("id")));
		} catch (DataIntegrityViolationException die) {
			System.out.println("data integration exc in action...");
		}
		return search();
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/editTeam", results = { @Result(name = "editTeam", location = "/content/editTeam.jsp") })
	public String edit() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		team = teamManager.getTeamById(Long.parseLong(request
				.getParameter("id")));
		divisionList = teamManager.listDivisions();
		return ProjectUtil.EDIT_TEAM;
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/changeTeam", results = { @Result(name = "changeTeam", location = "/content/changeTeam.jsp") })
	public String change() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		player = playerManager.getPlayerById(Long.parseLong(request
				.getParameter("idPlayer")));
		teamList = teamManager.listTeams();

		return ProjectUtil.CHANGE_TEAM;
	}

	@Action(value = "/getDynamicImageTeam")
	public void getDynamicImage() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		ServletOutputStream out = null;
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			team = teamManager.getTeamById(Long.parseLong(request
					.getParameter("id")));
			assert team.getImage() != null : "Immagine non trovata";
			out.write(team.getImage());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * METODI GET E SET
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public List<Nation> getNationList() {
		return nationList;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public List<Division> getDivisionList() {
		return divisionList;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}
}