package com.progetto.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.progetto.FileUploadAction;
import com.progetto.SpringHelper;
import com.progetto.domain.Career;
import com.progetto.domain.Nation;
import com.progetto.domain.Player;
import com.progetto.domain.Position;
import com.progetto.domain.Team;
import com.progetto.domain.Division;
import com.progetto.manager.CareerManager;
import com.progetto.manager.DivisionManager;
import com.progetto.manager.NationManager;
import com.progetto.manager.PlayerManager;
import com.progetto.manager.PositionManager;
import com.progetto.manager.TeamManager;
import com.progetto.util.ProjectUtil;

public class PlayerAction extends FileUploadAction implements ServletRequestAware, ModelDriven<Player>
{
	private static final long serialVersionUID = 1L;

	private TeamManager teamManager;

	private PlayerManager playerManager;

	private CareerManager careerManager;

	private PositionManager positionManager;

	private NationManager nationManager;

	private DivisionManager divisionManager;

	private List<Team> teamList;

	private List<Player> playerList;

	private List<Nation> nationList;

	private List<Position> positionList;

	private List<Career> careerList;

	private List<Division> divisionList;

	private Player player;

	// private Career career = new Career();

	private List<String> lettereRicerca;

	private ServletRequest request;

	Logger log = Logger.getLogger(this.getClass());

	public PlayerAction()
	{
		player = new Player();

		teamManager = (TeamManager) SpringHelper.getBean("teamManager");
		playerManager = (PlayerManager) SpringHelper.getBean("playerManager");
		careerManager = (CareerManager) SpringHelper.getBean("careerManager");
		positionManager = (PositionManager) SpringHelper.getBean("positionManager");
		nationManager = (NationManager) SpringHelper.getBean("nationManager");
		divisionManager = (DivisionManager) SpringHelper.getBean("divisionManager");

		teamList = teamManager.listTeams();
		nationList = nationManager.listNations();
		positionList = positionManager.listPositions();
		divisionList = divisionManager.listDivisions();
	}

	@Override
	public Player getModel()
	{
		return player;
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * _=
	 * 
	 * METODO VALIDATE
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * _=
	 */

	public void validate()
	{
		if (player.getFirstName() != null && StringUtils.isEmpty(player.getFirstName()))
		{
			addActionError("Il nome è obbligatorio.");
		}
		if (player.getLastName() != null && StringUtils.isEmpty(player.getLastName()))
		{
			addFieldError("player.lastName", "Il cognome è obbligatorio.");
		}
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * _=
	 * 
	 * METODI DI ACTION
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * _=
	 */

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/insertPlayer", results =
	{ @Result(name = "editPlayer", location = "/content/secure/editPlayer.jsp") })
	public String insert()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);

		teamList = teamManager.listTeams();
		nationList = nationManager.listNations();
		positionList = positionManager.listPositions();
		if (!StringUtils.isEmpty(request.getParameter("teamId")))
		{
			request.setAttribute("teamId", Long.parseLong(request.getParameter("teamId")));
		}
		if (!StringUtils.isEmpty(request.getParameter("divisionId")))
		{
			request.setAttribute("divisionId", Long.parseLong(request.getParameter("divisionId")));
		}
		return ProjectUtil.EDIT_PLAYER;
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/editPlayer", results =
	{ @Result(name = "editPlayer", location = "/content/secure/editPlayer.jsp") })
	public String edit()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		player = playerManager.getPlayerById(Long.parseLong(request.getParameter("id")));

		return insert();
	}

	/**
	 * To save or update user.
	 * 
	 * @return String
	 */
	@Action(value = "/savePlayer", results =
	{ @Result(name = "success", location = "/content/listPlayer.jsp"), 
	  @Result(name = "input", location = "/content/secure/editPlayer.jsp") 
	})
	public String save()
	{
		Position position = positionManager.getPositionById(player.getPosition().getId());
		// Team team = teamManager.getTeamById(player.getTeam().getId());
		Team team = teamManager.getTeamById(player.getTeamId());
		Nation nation = nationManager.getNationById(player.getNation().getId());

		player.setPosition(position);
		player.setTeamId(team.getId()); // ???
		player.setNation(nation);

		if (userImage != null)
		{
			byte[] image = getFileBytes();
			player.setImage(image);
		}
		else
			if (player.getId() != null)
			{
				player.setImage(playerManager.getPlayerById(player.getId()).getImage());
			}

		playerManager.saveOrUpdatePlayer(player);

		return listByTeam();
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/viewPlayer", results =
	{ @Result(name = "viewPlayer", location = "/content/viewPlayer.jsp") })
	public String view()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);

		Long idPlayer = Long.parseLong(request.getParameter("id"));

		player = playerManager.getPlayerById(idPlayer);
		careerList = careerManager.listCareer(idPlayer);

		return ProjectUtil.VIEW_PLAYER;
	}

	@Action(value = "/saveChangeTeamPlayer", results =
	{ @Result(name = "success", type = "redirect", location = "/listByTeamPlayer.action?teamId=${player.teamId}"), 
	  @Result(name = "input", location = "/content/secure/moveTeam.jsp") 
	})
	public String saveChangeTeam()
	{

		if (player.getTeamId() == null)
		{
			addFieldError("player.teamId", "La squadra è obbligatoria.");

			player = playerManager.getPlayerById(player.getId());
			Team team = teamManager.getTeamById(player.getTeamId());
			divisionList = divisionManager.listDivisionsByNation(team.getNation().getId());

			return INPUT;
		}

		Player bean = playerManager.getPlayerById(player.getId());
		playerManager.updateTeam(player.getId(), player.getTeamId());
		player.setTeamId(bean.getTeamId());

		return SUCCESS;
	}

	/**
	 * To list all users.
	 * 
	 * @return String
	 */
	@Action(value = "/listByTeamPlayer", results =
	{ @Result(name = "success", location = "/content/listPlayer.jsp") })
	public String listByTeam()
	{
		if (player.getTeamId() != null)
		{
			playerList = playerManager.listPlayersByTeam(player.getTeamId());
			Team team = teamManager.getTeamById(player.getTeamId());
			request.setAttribute("team", team);
		}
		return SUCCESS;
	}

	/**
	 * To list all users.
	 * 
	 * @return String
	 */
	@Action(value = "/listPlayer", results =
	{ @Result(name = "success", location = "/content/listPlayer.jsp") })
	public String list()
	{
		playerList = playerManager.listPlayers();
		return SUCCESS;
	}

	/**
	 * To delete a user.
	 * 
	 * @return String
	 */
	@Action(value = "/deletePlayer", results =
	{ @Result(name = "success", location = "/content/listPlayer.jsp") })
	public String delete()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		Long idPlayer = Long.parseLong(request.getParameter("id"));
		playerManager.deletePlayer(idPlayer);

		return listByTeam();
	}

	/**
	 * To list a single user by Id.
	 * 
	 * @return String
	 */
	@Action(value = "/searchPlayer", results =
	{ @Result(name = "searchPlayer", location = "/content/searchPlayer.jsp") })
	public String search()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String[] lettere =
		{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		lettereRicerca = Arrays.asList(lettere);

		String iniziale = request.getParameter("iniziale");

		if (!StringUtils.isEmpty(iniziale))
		{
			playerList = playerManager.listPlayersByLetter(iniziale);
		}

		return ProjectUtil.SEARCH_PLAYER;
	}

	@Action(value = "/getDynamicImagePlayer")
	public void getDynamicImage()
	{
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		ServletOutputStream out = null;
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("image/jpeg");
			out = response.getOutputStream();
			player = playerManager.getPlayerById(Long.parseLong(request.getParameter("id")));
			assert player.getImage() != null : "Immagine non trovata";
			out.write(player.getImage());
			out.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (out != null)
				try
				{
					out.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
	}

	/*
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * _=
	 * 
	 * METODI GET E SET
	 * 
	 * _=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * _=
	 */

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public List<Player> getPlayerList()
	{
		return playerList;
	}

	public List<Team> getTeamList()
	{
		return teamList;
	}

	public List<Career> getCareerList()
	{
		return careerList;
	}

	public List<Nation> getNationList()
	{
		return nationList;
	}

	public List<Position> getPositionList()
	{
		return positionList;
	}

	public List<Division> getDivisionList()
	{
		return divisionList;
	}

	public List<String> getLettereRicerca()
	{
		return lettereRicerca;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest)
	{
		this.request = servletRequest;
	}
}