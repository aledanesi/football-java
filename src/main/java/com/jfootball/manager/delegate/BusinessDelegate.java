/**
 * 
 */
package com.jfootball.manager.delegate;

import java.io.Serializable;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

/**
 * @author Alessandro Danesi
 *
 * 12/set/201422:59:09
 */
@Service("businessDelegate")
@RemoteProxy(name = "businessDelegate")
public class BusinessDelegate
{
	private BusinessLookUp lookupService = new BusinessLookUp();
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Serializable getEntityByID(Long id, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntityByID(id);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Serializable getEntityBySecondId(Long id, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntityBySecondId(id);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Serializable getEntityByDesc(String desc, String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntityByDesc(desc);
	}
		
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByID(Long id, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesByID(id);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByIDs(Long id1, Long id2, String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesByIDs(id1, id2);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByIDsNew(Long id1, Long id2, String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesByIDsNew(id1, id2);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByParams(String params, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesByParams(params);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntities(String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntities();
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getOtherEntities(String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getOtherEntities();
	}
	
	/**
	 * @param career
	 */
	public void saveEntity(Serializable entity, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		delegate.saveEntity(entity);
	}
	
	
	/**
	 * @param idCareer
	 */
	public void deleteEntity(Long id, String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		delegate.deleteEntity(id);		
	}
	
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */			
	public void doFirstJob(String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		delegate.doFirstJob();		
	}
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */			
	public void doSecondJob(String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		delegate.doSecondJob();			
	}
	
}
