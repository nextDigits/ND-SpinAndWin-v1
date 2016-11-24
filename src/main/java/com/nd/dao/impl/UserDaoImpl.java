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
import com.google.cloud.datastore.Transaction;
import com.nd.dao.UserDao;
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
			//Set USER
			KeyFactory keyFactory = datastore.newKeyFactory().kind("USER");
			Key key = datastore.allocateId(keyFactory.newKey());
			Entity user = Entity.builder(key).set("userid", signUpRequest.getUserId())
					.set("addresslineone", signUpRequest.getAddressFirstLine())
					.set("addresslinetwo", signUpRequest.getAddressNextLine())
					.set("appname", signUpRequest.getAppName()).set("country", signUpRequest.getCountry())
					.set("deviceid", signUpRequest.getDeviceId()).set("emailid", signUpRequest.getEmailId())
					.set("firstname", signUpRequest.getFirstName()).set("lastname", signUpRequest.getLastName())
					.set("password", signUpRequest.getPassword()).set("phonenumber", signUpRequest.getPhoneNumber())
					.set("state", signUpRequest.getState()).build();
			transaction.put(user);
			
			//Set USER_ACCEESS
			keyFactory = datastore.newKeyFactory().kind("USER_ACCESS");
			key = datastore.allocateId(keyFactory.newKey());
			Entity userAccess = Entity.builder(key).set("userid", signUpRequest.getUserId())
					.set("accesstoken", signUpRequest.getAccessToken())
					.set("refreshtoken", signUpRequest.getRefreshToken()).build();
			transaction.put(userAccess);
			transaction.commit();
			
		} finally {
			if (transaction.active()) {
				transaction.rollback();
			}
		}

	}
}
