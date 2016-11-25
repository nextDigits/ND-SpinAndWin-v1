package com.nd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.dao.RewardsDao;
import com.nd.service.RewardsService;

/**
 * @author NextDigit
 *
 */

@Service
public class RewardsServiceImpl implements RewardsService {
	
	@Autowired
	private RewardsDao rewardsDao;
	
	/**
	  * Returns the available reward points of a user at any given point of time.
	  * 
	  * @param userId
	  * @return available points
	  */
	@Override
	public String getAvailablePoints(String userId) throws Exception {
		return rewardsDao.getAvailablePoints(userId);
	}
}
