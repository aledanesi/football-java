package com.jfootball.test;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfootball.Constant;
import com.jfootball.domain.Division;
import com.jfootball.manager.DivisionManager;

public class DivisionTest extends BaseTest 
{

	@Autowired
	protected DivisionManager divisionManager;

	private Division division;

	@Before
	public void initialize() {
		division = new Division();
		division.setId(1L);
		division.setName("xxx");
		division.setValue("xxx");
		division.setNationId(11L);
	
		Assert.assertNotNull("Division manager is null.", divisionManager);
	}

	@Test
	public void getDivisionById() 
	{		

		Long divisionId = new Long(Constant.DEFAULT_DIVISION);

		Division division = divisionManager.getDivisionByID(divisionId);
		Assert.assertNotNull(division);			

		prLine("Getting the division by ID...", "test ok");
	}
	
	@Test
	public void getListDivision() 
	{
		List<Division> divisionList =  divisionManager.listDivisions();
		Assert.assertNotNull("Division list is null.", divisionList);

		prLine("Getting the division list...", "test ok");
	}
		
	
	@Test
	public void createDivision() {		

		divisionManager.saveOrUpdateDivision(division);

		prLine("Creating division...", "test ok");		
	}
	
	@Test
	public void deleteDivision() {		
		
		List<Division> divisionList =  divisionManager.listDivisions();
		Division obj = divisionList.get(0);

		divisionManager.deleteDivision(obj.getId());

		prLine("Deleting division...", "test ok");
	}	
	
}
