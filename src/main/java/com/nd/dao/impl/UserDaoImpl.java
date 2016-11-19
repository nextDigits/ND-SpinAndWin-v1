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
import com.nd.dao.UserDao;
import com.nd.response.UserAccessResponse;

/**
 * @author NextDigit
 *
 */
@Component
public class UserDaoImpl implements UserDao {

	private final Datastore datastore = DatastoreOptions.defaultInstance().service();
	
	// Create a Key factory to construct keys associated with this project.
	private final KeyFactory keyFactory = datastore.newKeyFactory().kind("USER");

	@Override
	public void saveUser() {
		Key key = datastore.allocateId(keyFactory.newKey());
		  Entity task = Entity.builder(key)
		      .set("userid", "test2")
		      .set("addresslineone", "3501 Xenium Ln")
		      .set("addresslinetwo", "Plymouth")
		      .set("appname", "clover")
		      .set("country", "India")
		      .set("deviceid", "asdf")
		      .set("emailid", "test@gmail.com")
		      .set("firstname", "Kiran")
		      .set("lastname", "Kurian")
		      .set("password", "asdf1234")
		      .set("phonenumber", "2241232121")
		      .set("state", "Kerala")
		      .build();
		  datastore.put(task);
		
	}
	


}
