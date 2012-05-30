package com.progetto;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect 
{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private String beforeMsg;
	private String afterMsg;
	
	
	@Around("execution(* com.progetto.manager.impl.PlayerManagerImpl.*(..))")
	public Object log(ProceedingJoinPoint pjp) throws Throwable
	{
		
		log.info("********** " + String.format(this.beforeMsg, pjp.getSignature().getName(), Arrays.toString(pjp.getArgs())));
		Object ret = pjp.proceed();
		log.info("********** " + String.format(this.afterMsg, pjp.getSignature().getName(), Arrays.toString(pjp.getArgs())));
		return ret;
	}


	public void setAfterMsg(String afterMsg) {
		this.afterMsg = afterMsg;
	}


	public void setBeforeMsg(String beforeMsg) {
		this.beforeMsg = beforeMsg;
	}
	
	
}
