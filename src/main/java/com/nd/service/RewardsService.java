package com.nd.service;

import org.springframework.stereotype.Component;

/**
 * @author NextDigit
 *
 */
@Component
public interface RewardsService {

	/**
	  * Returns the available reward points of a user at any given point of time.
	  * 
	  * @param userId
	  * @return available points
	  */
	public String getAvailablePoints(String userId) throws Exception;
	
}
