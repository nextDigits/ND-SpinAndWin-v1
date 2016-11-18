package com.nd.service;

import com.nd.request.UserAccessRequest;
import com.nd.response.UserAccessResponse;

public interface UserAccessService {
	UserAccessResponse signUp(UserAccessRequest signUpRequest) throws Exception;
	
	UserAccessResponse signIn(UserAccessRequest signUpRequest) throws Exception;
	
	UserAccessResponse validate(UserAccessRequest signUpRequest) throws Exception;
	
	UserAccessResponse forgotPassword(UserAccessRequest signUpRequest) throws Exception;
}
