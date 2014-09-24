package com.jfootball.manager.delegate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface BusinessService {

	/**
	 * @param idCareer
	 * @return
	 */
	public Serializable getEntityByID(Long id);
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Serializable getEntityBySecondId(Long id);
	
	/**
	 * @param idCareer
	 * @return
	 */
	public Serializable getEntityByDesc(String desc);
		
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByID(Long id);
	
	/**
	 * @param idCareer
	 * @return
	 */	
	public List<? extends Serializable> getEntitiesBySecondID(Long id);
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByIDs(Long id1, Long id2);	
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntitiesByIDsNew(Long id1, Long id2);
	
	/**
	 * @param idCareer
	 * @return
	 */	
	public List<? extends Serializable> getEntitiesByIDAndDesc(Long id, String desc);

	/**
	 * @param idCareer
	 * @return
	 */	
	public List<? extends Serializable> getEntitiesByParams(String... params);
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getEntities();
	
	/**
	 * @param idCareer
	 * @return
	 */
	public List<? extends Serializable> getOtherEntities();
	
	/**
	 * @param career
	 */
	public void saveEntity(Serializable entity);
	
	
	/**
	 * @param career
	 */
	public void updateEntityByParams(Object... params);

	/**
	 * @param idCareer
	 */
	public void deleteEntity(Long id);
	
	/**
	 * @param idCareer
	 */	
	public HashMap<String, Object> getHashMap(Long param1, Integer param2);
	
	/**
	 * @param idCareer
	 */	
	public String getString(Long teamId, Long playerId);
	
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */			
	public void doFirstJob();
	
	/**
	 * Method set the team owner
	 * 
	 * @return the players modified
	 */			
	public void doSecondJob();
}
