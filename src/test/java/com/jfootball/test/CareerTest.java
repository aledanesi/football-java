package com.jfootball.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jfootball.business.impl.CareerServiceImpl;
import com.jfootball.domain.Career;

public class CareerTest extends BaseTest 
{
	@Autowired
	protected CareerServiceImpl careerManager;

	private Career career;

	@Before
	public void initialize() {
		career = new Career();
		career.setSquadra("XXX");
		career.setPeriodo("XXX");
		career.setSerie("XXX");
		career.setReti("XXX");
	}

	@Test
	public void sampleTest() {

		System.out.println("Creating a new career");

		try {
			careerManager.saveEntity(career);
			System.out.println("Career created.");
		} catch (Exception e) {
			logger.error("Career creation has launched an error.");
		}
	}
}
