/**
 * 
 */
package com.nd.dao.impl;

import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.nd.dao.RewardsDao;
import com.nd.response.UserAccessResponse;

/**
 * @author NextDigit
 *
 */
@Component
public class RewardsDaoImpl implements RewardsDao {

	private final Datastore datastore = DatastoreOptions.defaultInstance().service();
	
	@Override
	public String getAvailablePoints(String userId) {
		Query<Entity> query = Query.entityQueryBuilder()
			    .kind("USER_REWARDS")
			    .filter(PropertyFilter.eq("userid", userId))
			    .build();
		QueryResults<Entity> tasks = datastore.run(query);
		String points = null;
		while(tasks.hasNext()){
			Entity task = tasks.next();
			points = task.getString("availablepoints");
			System.out.println("Success");
			break;
		}
		return points;
	}

}
