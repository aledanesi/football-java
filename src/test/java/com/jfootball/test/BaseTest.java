package com.jfootball.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@Ignore
@ContextConfiguration(locations = { "file:src/test/resources/config/web-application-config.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests 
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@BeforeClass
	public static void initializeClasse()
	{
		prTitles();		
	}
	
	public static void prTitles () 
	{
	    System.out.printf("%-35s %10s", "## Name test ##", "## Result ##");
	}	
	
	public static void prLine (String item, String result) 
	{
	    System.out.printf("\n%-35s %10s", item, result);
	}	

	protected void flush() {
		this.sessionFactory.getCurrentSession().flush();
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
