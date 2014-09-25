package com.jfootball.test;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfootball.Constant;
import com.jfootball.business.impl.NationServiceImpl;
import com.jfootball.domain.Nation;

public class NationTest extends BaseTest 
{

	@Autowired
	protected NationServiceImpl nationManager;

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

		Nation nation = nationManager.getEntityByID(nationId);
		Assert.assertNotNull(nation);			

		prLine("Getting the nation by ID...", "test ok");
	}
	
	@Test
	public void getListNation() 
	{
		List<Nation> nationList =  nationManager.getEntities();
		Assert.assertNotNull("Nation list is null.", nationList);

		prLine("Getting the nation list...", "test ok");
	}
	
	@Test
	public void createNation() 
	{		
		nationManager.saveEntity(nation);

		prLine("Creating nation...", "test ok");
	}

	@Test
	public void deleteNation() {		
		
		List<Nation> nationList =  nationManager.getEntities();
		Nation obj = nationList.get(0);

		nationManager.deleteEntity(obj.getId());

		prLine("Deleting nation...", "test ok");
	}

}
