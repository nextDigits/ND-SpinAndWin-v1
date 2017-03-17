package com.nd.dao.impl;

import org.springframework.stereotype.Component;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.Transaction;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;
import com.nd.dao.StaticContentDao;

@Component
public class StaticContentDaoImpl implements StaticContentDao {

	private final Datastore datastore = DatastoreOptions.defaultInstance().service();

	@Override
	public String getStaticContent(String key) {
		Query<Entity> query = Query.entityQueryBuilder().kind("STATIC_CONTENT").filter(PropertyFilter.eq("key", key))
				.build();
		QueryResults<Entity> tasks = datastore.run(query);
		String value = null;
		while (tasks.hasNext()) {
			Entity task = tasks.next();
			value = task.getString("value");
		}
		return value;
	}
	
	@Override
	public void updateStaticContent(String staticKey,String value) {
		Transaction transaction = datastore.newTransaction();
		Entity staticContentEntity = null;
		Query<Entity> staticContentQuery = Query.entityQueryBuilder().kind("STATIC_CONTENT")
					.filter(PropertyFilter.eq("key", staticKey)).build();
			QueryResults<Entity> staticContenTask= datastore.run(staticContentQuery);
			while (staticContenTask.hasNext()) {
				staticContentEntity = staticContenTask.next();
				break;
			}
			if (null != staticContentEntity) {
				staticContentEntity = Entity.builder(datastore.get(staticContentEntity.key()))
						.set("value", value).build();
				transaction.put(staticContentEntity);
			}
			transaction.commit();
	}

}
