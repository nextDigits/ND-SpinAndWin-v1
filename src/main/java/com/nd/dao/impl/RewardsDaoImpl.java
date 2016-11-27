/**
 * 
 */
package com.nd.dao.impl;

import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.google.cloud.datastore.Transaction;
import com.nd.dao.RewardsDao;
import com.nd.entities.UserRewards;

/**
 * @author NextDigit
 *
 */
@Component
public class RewardsDaoImpl implements RewardsDao {

	private final Datastore datastore = DatastoreOptions.defaultInstance().service();

	/**
	 * Returns the available reward points of a user at any given point of time.
	 * 
	 * @param userId
	 * @return available points
	 */
	@Override
	public String getAvailablePoints(String userId) {
		Query<Entity> query = Query.entityQueryBuilder().kind("USER_REWARD_POINTS")
				.filter(PropertyFilter.eq("userid", userId)).build();
		QueryResults<Entity> tasks = datastore.run(query);
		String points = null;
		while (tasks.hasNext()) {
			Entity task = tasks.next();
			points = task.getString("availablepoints");
			break;
		}
		return points;
	}

	/**
	 * Returns the reward points of associated with the rewardId.
	 * 
	 * @param rewardId
	 * @return rewardPoints
	 */
	@Override
	public String getRewardPoints(String rewardId) {
		Query<Entity> query = Query.entityQueryBuilder().kind("REWARDS").filter(PropertyFilter.eq("rewardid", rewardId))
				.build();
		QueryResults<Entity> tasks = datastore.run(query);
		String rewardPoints = null;
		while (tasks.hasNext()) {
			Entity task = tasks.next();
			rewardPoints = task.getString("rewardpoints");
			break;
		}
		return rewardPoints;
	}

	@Override
	public void updateUserRewards(UserRewards userRewards) throws Exception {
		Transaction transaction = datastore.newTransaction();
		try {
			// Check for USER_REWARDS table error scenario.
			Query<Entity> userRewardsQuery = Query.entityQueryBuilder().kind("USER_REWARDS")
					.filter(PropertyFilter.eq("userid", userRewards.getUserId()))
					.filter(PropertyFilter.eq("rewardid", userRewards.getRewardId())).build();
			QueryResults<Entity> userRewardsTasks = datastore.run(userRewardsQuery);
			Entity userRewardsEntity = null;
			while (userRewardsTasks.hasNext()) {
				// Error response
				throw new Exception();
			}

			// Check for REWARDS table error scenario.
			Query<Entity> rewardsQuery = Query.entityQueryBuilder().kind("REWARDS")
					.filter(PropertyFilter.eq("rewardid", userRewards.getRewardId())).build();
			QueryResults<Entity> rewardsTasks = datastore.run(rewardsQuery);
			Entity rewardsEntity = null;
			if (!rewardsTasks.hasNext()) {
				// Error response
				throw new Exception();
			}else{
				rewardsEntity = rewardsTasks.next();
				if(null!=rewardsEntity && !rewardsEntity.getBoolean("isavailable")){
					// Error response
					throw new Exception();
				}
			}

			// Check for USER_REWARD_POINTS table error scenario.
			Query<Entity> userRewardPointsQuery = Query.entityQueryBuilder().kind("USER_REWARD_POINTS")
					.filter(PropertyFilter.eq("userid", userRewards.getUserId())).build();
			QueryResults<Entity> userRewardPointsTasks = datastore.run(userRewardPointsQuery);
			Entity userRewardPointsEntity = null;
			if (!userRewardPointsTasks.hasNext()) {
				// Error response
				throw new Exception();
			}

			// Update the USER_REWARDS table.
			KeyFactory userRewardsKeyFactory = datastore.newKeyFactory().kind("USER_REWARDS");
			Key userRewardsKey = datastore.allocateId(userRewardsKeyFactory.newKey());
			userRewardsEntity = Entity.builder(userRewardsKey).set("userid", userRewards.getUserId())
					.set("rewardid", userRewards.getRewardId()).build();
			transaction.put(userRewardsEntity);

			// Update the USER_REWARD_POINTS table.
			while (userRewardPointsTasks.hasNext()) {
				userRewardPointsEntity = userRewardPointsTasks.next();
				break;
			}
			if (null != userRewardPointsEntity) {
				userRewardPointsEntity = Entity.builder(datastore.get(userRewardPointsEntity.key()))
						.set("availablepoints", userRewards.getAvailablePoints()).build();
				transaction.put(userRewardPointsEntity);
			}

			// Update the REWARDS table.
			while (rewardsTasks.hasNext()) {
				rewardsEntity = rewardsTasks.next();
				break;
			}
			if (null != rewardsEntity) {
				rewardsEntity = Entity.builder(datastore.get(rewardsEntity.key())).set("isavailable", Boolean.FALSE)
						.build();
				transaction.put(rewardsEntity);
			}
			transaction.commit();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			if (transaction.active()) {
				transaction.rollback();
			}
		}
	}

}