package com.jfootball.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.jfootball.domain.Division;
import com.jfootball.domain.Nation;
import com.jfootball.util.ProjectConstant;
import com.jfootball.web.validator.TeamValidator;

@Controller
public class DivisionController extends GenericController
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
    protected void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }	
	
	
	
	/*_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 * 
	 * 						METODI DI ACTION
	 * 
	 *=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=_=
	 * 
	 */
	
	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping("/divisions/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
	{

		ModelAndView view = new ModelAndView(ProjectConstant.LIST_DIVISION);
		
		List<Division> divisionList = footballManager.getDivisions();
		List<Nation> nationList = footballManager.getNations();
		
		
		view.addObject("division", new Division());
		view.addObject("divisionList", divisionList);
		view.addObject("nationList", nationList);
		

		return view;
	}

	/**
	 * 
	 * ----------------formBackingObject()----------------
	 * 
	 */
	@RequestMapping(value = "/divisions/delete", method = RequestMethod.GET)	
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("id") String divisionId)
	{
		footballManager.deleteDivision(Long.parseLong(divisionId));
				
		return list(request, response);
	}

	
	
	
	/**
	 * 
	 * ----------------onSubmit()----------------
	 * 
	 */
	@RequestMapping(value = "/divisions/save", method = RequestMethod.POST)
	protected ModelAndView processSubmit(@ModelAttribute("division") Division division, 
											BindingResult result, 
											SessionStatus status,
											HttpServletRequest request, 
											HttpServletResponse response) throws Exception
	{	
		new TeamValidator().validate(division, result);
        if (result.hasErrors()) 
        {
    		List<Division>divisionList = footballManager.getDivisions();

    		request.setAttribute("divisionList", divisionList);


    		return new ModelAndView(ProjectConstant.EDIT_DIVISION);
        }
        else 
        {
    		if (division.getImage().length == 0 && division.getId() != null)
    		{
    			Division divisionDb = footballManager.getDivision(division.getId());
    			if (divisionDb != null )
    			{
    				division.setImage(divisionDb.getImage());
    			}
    			//byte[] image = getFileBytes();
    			//team.setImage(image);
    		}
    		else 
    		{
        		if (division.getId() == null)
        		{
    				File file = new File(request.getSession().getServletContext().getRealPath("/") + "/images/players/notFound.jpg");
    				FileInputStream imageInFile;
    				try {
    					imageInFile = new FileInputStream(file);
    					byte imageData[] = new byte[(int) file.length()];            
    					imageInFile.read(imageData);	
    					division.setImage(imageData);
    					imageInFile.close();
    				} catch (FileNotFoundException e) {
    					//e.printStackTrace();
    					throw e;
    				}  catch (IOException e) {
    					//e.printStackTrace();
    					throw e;
    				}              			
        		}
        		else 
        		{
        			OutputStream outputStream = null;
        			
        			byte[] bytesFile = division.getImage();
        			if (bytesFile.length > 0) {
        				
        				String tomcat_home = System.getProperty("catalina.base") + "\\webapps\\images\\division\\";
        				outputStream = new FileOutputStream(tomcat_home + division.getId() + ".png");
    					outputStream.write(bytesFile);
    					outputStream.close();
        			}
        		}
    		}
    		    		
    		footballManager.saveDivision(division);

    		List<Division>divisionList = footballManager.getDivisions();

    		request.setAttribute("divisionList", divisionList);

    		return new ModelAndView(ProjectConstant.LIST_DIVISION);
        }		
		
	}


	/**
	 * 
	 * ----------------referenceData()----------------
	 * 
	 */	
	@ModelAttribute("nationList")
	protected List<Nation> populateNations(HttpServletRequest request)
			throws Exception 
	{
		List<Nation>nationList = footballManager.getNations();
		return nationList;
	}
	
	

}
