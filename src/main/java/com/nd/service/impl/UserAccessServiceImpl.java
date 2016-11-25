package com.nd.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.constants.MessageConstants;
import com.nd.dao.UserDao;
import com.nd.entities.User;
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

		// Create userId,accessToken and refreshToken
		signUpRequest.setUserId(tokenGenerator.generateUserId(signUpRequest.getAppName(),
				signUpRequest.getPhoneNumber(), signUpRequest.getFirstName()));
		signUpRequest.setAccessToken(tokenGenerator.generateToken());
		signUpRequest.setRefreshToken(tokenGenerator.generateToken());

		// Save user to user table
		userDao.saveUser(signUpRequest);

		// Set response
		signUpResponse.setUserId(signUpRequest.getUserId());
		signUpResponse.setStatus(MessageConstants.SUCCESS_STATUS);
		signUpResponse.setResponseMessage(MessageConstants.SIGNUP_RESPONSE_MESSAGE);
		signUpResponse.setValidationRequired("Phone");
		signUpResponse.setAccessToken(signUpRequest.getAccessToken());
		signUpResponse.setRefreshToken(signUpRequest.getRefreshToken());
		return signUpResponse;
	}

	@Override
	public UserAccessResponse signIn(UserAccessRequest signInRequest) throws Exception {
		UserAccessResponse signInResponse = new UserAccessResponse();
		User user = new User();
		if (StringUtils.isNotEmpty(signInRequest.getEmailId())) {
			user = userDao.getUser(signInRequest.getEmailId(), "emailid", false);
		} else {
			user = userDao.getUser(signInRequest.getPhoneNumber(), "phonenumber", false);
		}
		user = userDao.getUserAccessInfo(user);
		signInResponse.setUserId(user.getUserId());
		signInResponse.setAccessToken(user.getAccessToken());
		signInResponse.setResponseMessage(MessageConstants.SIGNUP_RESPONSE_MESSAGE);
		return signInResponse;
	}

	@Override
	public UserAccessResponse validate(UserAccessRequest validateRequest) throws Exception {
		UserAccessResponse validateResponse = new UserAccessResponse();
		User user = new User();
		user.setUserId(validateRequest.getUserId());
		user = userDao.getUserAccessInfo(user);
		if(StringUtils.isNotEmpty(validateRequest.getAccessToken()) && StringUtils.equals(validateRequest.getAccessToken(), user.getAccessToken())){
			validateResponse.setAccessToken(tokenGenerator.generateToken());
			validateResponse.setRefreshToken(user.getRefreshToken());
			validateResponse.setUserId(user.getUserId());
		}else if(StringUtils.isNotEmpty(validateRequest.getRefreshToken())){
			validateResponse.setAccessToken(tokenGenerator.generateToken());
			validateResponse.setRefreshToken(tokenGenerator.generateToken());
			validateResponse.setUserId(user.getUserId());
		}else{
			//Set error response
		}
		
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
