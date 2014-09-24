package com.jfootball.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.jfootball.domain.user.UserBean;
import com.jfootball.util.ProjectConstant;
import com.jfootball.web.validator.UserValidator;

/**
 * @author Alessandro Danesi
 *
 * 15/mar/2014 15:18:17
 */
@Controller
public class UserController extends GenericController
{


	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_
	 * 
	 * 
	 * 						BINDER
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */
	
	/** 
	 * ----------------initBinder()----------------
	 * @see com.jfootball.FileUpload#initBinder(org.springframework.web.bind.WebDataBinder)
	 */
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TeamValidator());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }	

	
	
	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_
	 * 
	 * 
	 * 						METODI DI ACTION
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 */
	
	/**
	 * ----------------formBackingObject()----------------
	 * @param request
	 * @param response
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/users/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
	{

		logger.info("--------------------- User Controller : list --------------------- ");
		
		ModelAndView view = new ModelAndView(ProjectConstant.LIST_TEAM);
		
		List<UserBean> userList = (List<UserBean>)businessDelegate.getEntities("USER");				
		view.addObject("userList", userList);

		logger.info("view: LIST_USER");

		return view;
	}


	/**
	 * ----------------formBackingObject()----------------
	 * @param request
	 * @param response
	 * @param teamId
	 * @param nationId
	 * @param divisionId
	 * @return
	 */
	@RequestMapping(value = "/users/delete", method = RequestMethod.GET)	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("id") String userId)
	{

		logger.info("--------------------- Team Controller : delete --------------------- ");

		businessDelegate.deleteEntity(Long.parseLong(userId), "USER");

		logger.info("Team " + userId + "deleted");

		return list(request, response);
	}


	/**
	 * ----------------onSubmit()----------------
	 * @param team
	 * @param result
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	protected ModelAndView processSubmit(@ModelAttribute("user") UserBean user, 
											BindingResult result, 
											SessionStatus status,
											HttpServletRequest request, 
											HttpServletResponse response) throws Exception
	{	
		logger.info("--------------------- Team Controller : processSubmit --------------------- ");

		new UserValidator().validate(user, result);
				
        if (result.hasErrors()) 
        {
    		
    		request.setAttribute("user", user);
    		
    		ModelAndView view  = new ModelAndView(ProjectConstant.EDIT_USER);

    		logger.info("Team didn't save");

			logger.info("view: EDIT_TEAM");

    		return view;
        }
        else 
        {
    		//user.setLastUserModify(getUsername());
    		//user.setLastTimeModify(new Timestamp(System.currentTimeMillis()));
    		
        	businessDelegate.saveEntity(user, "USER");
    		
    		logger.info("User saved");
    		
    		return list(request, response);
        }		
	}

}