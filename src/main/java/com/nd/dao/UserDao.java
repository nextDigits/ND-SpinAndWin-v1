package com.nd.dao;

import org.springframework.stereotype.Component;

import com.nd.request.UserAccessRequest;

@Component
public interface UserDao {
	
	//Save user and user access information
	void saveUser(UserAccessRequest signUpRequest);
}
