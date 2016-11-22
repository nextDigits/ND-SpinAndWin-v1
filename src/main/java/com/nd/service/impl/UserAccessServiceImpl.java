package com.nd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.constants.MessageConstants;
import com.nd.dao.UserDao;
import com.nd.request.UserAccessRequest;
import com.nd.response.UserAccessResponse;
import com.nd.service.UserAccessService;
import com.nd.utils.TokenGenerator;

@Service
public class UserAccessServiceImpl implements UserAccessService {
	
	@Autowired
	public TokenGenerator tokenGenerator;
	
	@Autowired
	public UserDao userDao;
	
	@Override
	public UserAccessResponse signUp(UserAccessRequest signUpRequest) throws Exception {
		UserAccessResponse signUpResponse = new UserAccessResponse();
		
		//Create userId,accessToken and refreshToken
		signUpRequest.setUserId(tokenGenerator.generateUserId(signUpRequest.getAppName(),signUpRequest.getPhoneNumber(),signUpRequest.getFirstName()));
		signUpRequest.setAccessToken(tokenGenerator.generateToken());
		signUpRequest.setRefreshToken(tokenGenerator.generateToken());
		
		//Save user to user table
		userDao.saveUser(signUpRequest);
		//Save user to user access table
		userDao.saveUserAccess(signUpRequest);
		
		//Set response
		signUpResponse.setUserId(signUpRequest.getUserId());
		signUpResponse.setStatus(MessageConstants.SUCCESS_STATUS);
		signUpResponse.setResponseMessage(MessageConstants.SIGNUP_RESPONSE_MESSAGE);
		signUpResponse.setValidationRequired("Phone");
		signUpResponse.setAccessToken(signUpRequest.getAccessToken());
		signUpResponse.setRefreshToken(signUpRequest.getRefreshToken());
		return signUpResponse;
	}

	@Override
	public UserAccessResponse signIn(UserAccessRequest signUpRequest) throws Exception {
		UserAccessResponse signInResponse = new UserAccessResponse();
		signInResponse.setStatus("Success");
		signInResponse.setResponseMessage("It's time to spin n win!!!");
		signInResponse.setAccessToken(tokenGenerator.generateToken());
		signInResponse.setUserId(tokenGenerator.generateUserId(signUpRequest.getAppName(),signUpRequest.getPhoneNumber(),signUpRequest.getFirstName()));
		return signInResponse;
	}

	@Override
	public UserAccessResponse validate(UserAccessRequest signUpRequest) throws Exception {
		UserAccessResponse validateResponse = new UserAccessResponse();
		validateResponse.setStatus("Success");
		validateResponse.setResponseMessage("It's time to spin n win!!!");
		validateResponse.setAccessToken(tokenGenerator.generateToken());
		return validateResponse;
	}
	
	@Override
	public UserAccessResponse forgotPassword(UserAccessRequest signUpRequest) throws Exception {
		UserAccessResponse validateResponse = new UserAccessResponse();
		validateResponse.setStatus("Success");
		validateResponse.setResponseMessage("Reset password email send");
		return validateResponse;
	}

}
