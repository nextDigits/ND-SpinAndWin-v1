/**
 * 
 */
package com.nd.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

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
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		try {
		  Message msg = new MimeMessage(session);
		  msg.setFrom(new InternetAddress("damodaranamith@gmail.com", "Admin"));
		  msg.addRecipient(Message.RecipientType.TO,
		                   new InternetAddress("kirankuriangits@gmail.com", "Mr. User"));
		  msg.setSubject("Your have"+userId+"points available!!!");
		  Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return points;
	}

}
