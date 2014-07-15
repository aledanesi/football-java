package com.jfootball.test;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfootball.Constant;
import com.jfootball.domain.Nation;
import com.jfootball.manager.NationManager;

public class NationTest extends BaseTest 
{

	@Autowired
	protected NationManager nationManager;

	private Nation nation;

	@Before
	public void initialize() {
		nation = new Nation();
		nation.setId(100L);
		nation.setName("xxx");
		nation.setValue("xxx");

		Assert.assertNotNull("Nation manager is null.", nationManager);
	}
	
	@Test
	public void getNationByID() {		
		Long nationId = new Long(Constant.DEFAULT_NATION);

		Nation nation = nationManager.getNationByID(nationId);
		Assert.assertNotNull(nation);			

		prLine("Getting the nation by ID...", "test ok");
	}
	
	@Test
	public void getListNation() 
	{
		List<Nation> nationList =  nationManager.listNations();
		Assert.assertNotNull("Nation list is null.", nationList);

		prLine("Getting the nation list...", "test ok");
	}
	
	@Test
	public void createNation() 
	{		
		nationManager.saveOrUpdateNation(nation);

		prLine("Creating nation...", "test ok");
	}

	@Test
	public void deleteNation() {		
		
		List<Nation> nationList =  nationManager.listNations();
		Nation obj = nationList.get(0);

		nationManager.deleteNation(obj.getId());

		prLine("Deleting nation...", "test ok");
	}

}
