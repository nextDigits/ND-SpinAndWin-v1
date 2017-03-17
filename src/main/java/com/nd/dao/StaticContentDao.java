package com.nd.dao;

import org.springframework.stereotype.Component;

@Component
public interface StaticContentDao {

	String getStaticContent(String key);
	
	void updateStaticContent(String staticKey,String value);
}
