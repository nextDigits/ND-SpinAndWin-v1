/**
 * 
 */
package com.nd.dao;

import org.springframework.stereotype.Component;

import com.nd.entities.UserRewards;

/**
 * @author NextDigit
 *
 */
@Component
public interface RewardsDao {

	/**
	  * Returns the available reward points of a user at any given point of time.
	  * 
	  * @param userId
	  * @return available points
	  */
	public String getAvailablePoints(String userId);

	/**
	  * Returns the reward points of associated with the rewardId.
	  * 
	  * @param rewardId
	  * @return rewardPoints
	  */
	public String getRewardPoints(String rewardId);

	/**
	  * Updates the rewardId and available points of the user.
	  * 
	  * @param userRewards
	  * @return
	  */
	public void updateUserRewards(UserRewards userRewards) throws Exception;

}
