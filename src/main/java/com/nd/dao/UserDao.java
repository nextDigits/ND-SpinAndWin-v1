package com.nd.dao;

import org.springframework.stereotype.Component;

import com.nd.entities.User;
import com.nd.request.UserAccessRequest;
import com.nd.response.UserAccessResponse;

@Component
public interface UserDao {
	
	//Save user and user access information
	void saveUser(UserAccessRequest signUpRequest);
	
	//Get user information
	User getUser(String userId,String userType,boolean getCompleteProfile);
	
	//Get user information
	User getUserAccessInfo(User user);
}
