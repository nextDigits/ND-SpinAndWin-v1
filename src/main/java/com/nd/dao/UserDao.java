package com.nd.dao;

import org.springframework.stereotype.Component;

import com.nd.request.UserAccessRequest;

@Component
public interface UserDao {
	void saveUser(UserAccessRequest signUpRequest);
	void saveUserAccess(UserAccessRequest signUpRequest);
}
