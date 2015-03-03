/**
 * 
 */
package com.jfootball.business;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private BusinessLookUp lookupService;
	
	/**
	 * @param idCareer
	 * @return
	 */
	@RemoteMethod
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
	public List<? extends Serializable> getEntitiesByIDAndDesc(Long id, String desc, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesByIDAndDesc(id, desc);
	}	
	
	/**
	 * @param idCareer
	 * @return
	 */	
	@RemoteMethod
	public List<? extends Serializable> getEntitiesBySecondID(Long id, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesBySecondID(id);		
	}	
	
	/**
	 * @param idCareer
	 * @return
	 */
	@RemoteMethod
	public List<? extends Serializable> getEntitiesByIDs(Long id1, Long id2, String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getEntitiesByIDs(id1, id2);
	}
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Long getIntegerByTwoParams(Long id1, Long id2, String serviceType)	
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getIntegerByTwoParams(id1, id2);
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
	@RemoteMethod
	public List<? extends Serializable> getEntitiesByParams(String... params)
	{
		if (params.length == 3)
		{
			BusinessService delegate =  lookupService.getBusinessService(params[2]);
			return delegate.getEntitiesByParams(params[0], params[1]);
			
		}
		else 
		{
			BusinessService delegate =  lookupService.getBusinessService(params[1]);
			return delegate.getEntitiesByParams(params[0]);			
		}
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
	 * @param career
	 */
	@RemoteMethod
	public void updateEntityByParams(String... params)
	{
		int length = params.length;
		
		BusinessService delegate =  lookupService.getBusinessService((String)params[length-1]);
		
		delegate.updateEntityByParams(params);
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
	 * @param idCareer
	 */	
	@RemoteMethod
	public boolean findEntityExists(String... params)
	{
		BusinessService delegate =  lookupService.getBusinessService(params[3]);
		return delegate.findEntityExists(params[0], params[1], params[2]);
	}	
	
	/**
	 * @param idCareer
	 */
	public HashMap<String, Object> getHashMap(Long param1, Integer param2, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getHashMap(param1, param2);		
	}
	
	/**
	 * @param idCareer
	 */
	public String getString(Long teamId, Long playerId, String serviceType)
	{
		BusinessService delegate =  lookupService.getBusinessService(serviceType);
		return delegate.getString(teamId, playerId);		
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

	public void setLookupService(BusinessLookUp lookupService)
	{
		this.lookupService = lookupService;
	}
	
	public BusinessLookUp getLookupService()
	{
		return lookupService;
	}
	
	
	
	
}
