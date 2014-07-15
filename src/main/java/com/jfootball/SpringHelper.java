/*
 * Progetto, a new java application 
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Rattazzi Street, Fifth Floor
 * Pomezia, RM  00040  Italy
 */
package com.jfootball;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Alessandro Danesi
 *
 * 17/dic/2013 11:54
 */
@Component
public class SpringHelper implements ApplicationContextAware
{
    private static ApplicationContext ctx;
    
    static Logger log = Logger.getLogger(SpringHelper.class);
    
    // used by Spring to inject ApplicationContext, since this bean implements 
    //the ApplicationContextAware interface (and is defined in the spring.xml file)
    public void setApplicationContext(ApplicationContext ac) throws BeansException
    {
    	log.info("*********** setApplicationContext ****************");
    	ctx = ac;
    }

    //Utility function to return the Spring ApplicationContext
    public static ApplicationContext getApplicationContext() { 
        return ctx; 
    }  

    //Utility function to return a bean form the Spring BeanFactory
    public static Object getBean(String name) 
    {
    	log.info("in getBean di SpringHelper...");
    	log.info("ctx: " + ctx);
    	return ctx.getBean(name);
    }

}