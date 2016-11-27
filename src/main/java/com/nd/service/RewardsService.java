package com.nd.service;

import org.springframework.stereotype.Component;

import com.nd.request.RedeemRewardsRequest;
import com.nd.response.RedeemRewardsResponse;

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

	/**
	  * This method allows a user to redeem a reward.
	  * 
	  * @param userId
	  * @param rewardId
	  * @return redeemRewardsResponse
	  */
	public RedeemRewardsResponse redeemReward(RedeemRewardsRequest redeemRewardsRequest) throws Exception;
	
}
