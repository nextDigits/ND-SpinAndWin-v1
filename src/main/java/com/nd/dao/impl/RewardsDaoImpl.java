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
		Query<Entity> query = Query.entityQueryBuilder()
			    .kind("USER_REWARDS")
			    .filter(PropertyFilter.eq("userid", userId))
			    .build();
		QueryResults<Entity> tasks = datastore.run(query);
		String points = null;
		while(tasks.hasNext()){
			Entity task = tasks.next();
			points = task.getString("availablepoints");
			break;
		}
//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);
//		try {
//		  Message msg = new MimeMessage(session);
//		  msg.setFrom(new InternetAddress("damodaranamith@gmail.com", "Admin"));
//		  msg.addRecipient(Message.RecipientType.TO,
//		                   new InternetAddress("kirankuriangits@gmail.com", "Mr. User"));
//		  msg.setSubject("Your have"+userId+"points available!!!");
//		  Transport.send(msg);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		return points;
	}

}