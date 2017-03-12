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
import com.nd.dao.UserDao;
import com.nd.entities.User;
import com.nd.request.UserAccessRequest;

/**
 * @author NextDigit
 *
 */
@Component
public class UserDaoImpl implements UserDao {

	private final Datastore datastore = DatastoreOptions.defaultInstance().service();

	@Override
	public void saveUser(UserAccessRequest signUpRequest) {
		Transaction transaction = datastore.newTransaction();
		try {
			// Set USER
			KeyFactory keyFactory = datastore.newKeyFactory().kind("USER");
			Key key = datastore.allocateId(keyFactory.newKey());
			Entity user = Entity.builder(key).set("userid", signUpRequest.getUserId())
					.set("appname", signUpRequest.getAppName()).set("deviceid", signUpRequest.getDeviceId())
					.set("emailid", signUpRequest.getEmailId()).set("firstname", signUpRequest.getFirstName())
					.set("lastname", signUpRequest.getLastName()).set("phonenumber", signUpRequest.getPhoneNumber())
					.build();
			transaction.put(user);

			// Set USER_ACCEESS
			keyFactory = datastore.newKeyFactory().kind("USER_ACCESS");
			key = datastore.allocateId(keyFactory.newKey());
			Entity userAccess = Entity.builder(key).set("userid", signUpRequest.getUserId())
					.set("accesstoken", signUpRequest.getAccessToken())
					.set("refreshtoken", signUpRequest.getRefreshToken()).set("password", signUpRequest.getPassword())
					.build();
			transaction.put(userAccess);
			transaction.commit();

		} finally {
			if (transaction.active()) {
				transaction.rollback();
			}
		}

	}

	@Override
	public User getUser(String id, String loginType, boolean getCompleteProfile) {
		Query<Entity> query = Query.entityQueryBuilder().kind("USER").filter(PropertyFilter.eq(loginType, id)).build();
		QueryResults<Entity> tasks = datastore.run(query);
		User user = null;
		while (tasks.hasNext()) {
			Entity task = tasks.next();
			user = new User();
			user.setUserId(task.getString("userid"));
			if (getCompleteProfile) {
				user.setAddresslineone(task.getString("addresslineon"));
				user.setAddresslinetwo(task.getString("addresslinetwo"));
				user.setAppName(task.getString("appname"));
				user.setCountry(task.getString("country"));
				user.setDeviceId(task.getString("deviceid"));
				user.setEmailId(task.getString("emailid"));
				user.setFirstName(task.getString("firstname"));
				user.setLastName(task.getString("lastname"));
				user.setPhoneNumber(task.getString("phonenumber"));
				user.setState(task.getString("state"));
			}
			break;
		}
		return user;
	}

	@Override
	public User getUserAccessInfo(User user) {
		Query<Entity> query = Query.entityQueryBuilder().kind("USER_ACCESS")
				.filter(PropertyFilter.eq("userid", user.getUserId())).build();
		QueryResults<Entity> tasks = datastore.run(query);
		while (tasks.hasNext()) {
			Entity task = tasks.next();
			user.setAccessToken(task.getString("accesstoken"));
			user.setRefreshToken(task.getString("refreshtoken"));
			user.setPassword(task.getString("password"));
		}
		return user;

	}

	@Override
	public void updateUserAccessInfo(User user) {
		// Set USER_ACCEESS
		KeyFactory keyFactory = datastore.newKeyFactory().kind("USER_ACCESS");
		Key key = datastore.allocateId(keyFactory.newKey());
		//Entity task = Entity.newBuilder(datastore.get(taskKey)).set("priority", 5).build();
		Entity userAccess = Entity.builder(key).set("userid", user.getUserId())
				.set("accesstoken", user.getAccessToken()).set("refreshtoken", user.getRefreshToken())
				.set("password", user.getPassword()).build();
		 datastore.update(userAccess);
	}

}
