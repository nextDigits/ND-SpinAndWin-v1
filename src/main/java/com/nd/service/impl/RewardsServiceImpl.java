package com.nd.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nd.constants.MessageConstants;
import com.nd.dao.RewardsDao;
import com.nd.entities.UserRewards;
import com.nd.request.RedeemRewardsRequest;
import com.nd.response.RedeemRewardsResponse;
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

	/**
	  * This method allows a user to redeem a reward.
	  * 
	  * @param userId
	  * @param rewardId
	  * @return redeemRewardsResponse
	  */
	@Override
	public RedeemRewardsResponse redeemReward(RedeemRewardsRequest redeemRewardsRequest) throws Exception {		
		//Get reward points for the given rewardId
		String rewardPoints = rewardsDao.getRewardPoints(redeemRewardsRequest.getRewardId());		
		//Get the available points of the user
		String availablePoints = rewardsDao.getAvailablePoints(redeemRewardsRequest.getUserId());
		RedeemRewardsResponse redeemRewardsResponse = new RedeemRewardsResponse();;
		if(StringUtils.isEmpty(rewardPoints) || StringUtils.isEmpty(availablePoints)){
			// Error Response
			redeemRewardsResponse.setStatus(MessageConstants.FAILURE_STATUS);
			redeemRewardsResponse.setResponseMessage(MessageConstants.REDEEMREWARDS_RESPONSE_FAILURE);
		}else if (new BigInteger(availablePoints).compareTo(new BigInteger(rewardPoints)) < 0){
			// Error Response
			redeemRewardsResponse.setStatus(MessageConstants.FAILURE_STATUS);
			redeemRewardsResponse.setResponseMessage(MessageConstants.REDEEMREWARDS_RESPONSE_FAILURE);
		}else{
			UserRewards userRewards = new UserRewards();
			BigInteger remainingPoints = new BigInteger(availablePoints).subtract(new BigInteger(rewardPoints));
			userRewards.setUserId(redeemRewardsRequest.getUserId());
			userRewards.setRewardId(redeemRewardsRequest.getRewardId());
			userRewards.setAvailablePoints(remainingPoints.toString());
			
			//update the rewardId and available points of the user
			try{
				rewardsDao.updateUserRewards(userRewards);
				redeemRewardsResponse.setRemainingPoints(remainingPoints.toString());
				redeemRewardsResponse.setUsedPoints(rewardPoints);
				redeemRewardsResponse.setStatus(MessageConstants.SUCCESS_STATUS);
				redeemRewardsResponse.setResponseMessage(MessageConstants.REDEEMREWARDS_RESPONSE_SUCCESS);
			}catch(Exception e){
				// Error Response
				redeemRewardsResponse.setStatus(MessageConstants.FAILURE_STATUS);
				redeemRewardsResponse.setResponseMessage(MessageConstants.REDEEMREWARDS_RESPONSE_FAILURE);
			}
		}
		return redeemRewardsResponse;
	}
}
