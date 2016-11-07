package com.nd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nd.entities.UserAccessRequest;
import com.nd.entities.UserAccessResponse;
import com.nd.utils.TokenGenerator;

@Service
public class UserAccessServiceImpl implements UserAccessService {
	
	public final TokenGenerator tokenGenerator;
	
	@Autowired
	public UserAccessServiceImpl(TokenGenerator tokenGenerator){
		this.tokenGenerator = tokenGenerator;
	}
	
	@Override
	public UserAccessResponse signUp(UserAccessRequest signUpRequest) throws Exception {
		UserAccessResponse signUpResponse = new UserAccessResponse();
		signUpResponse.setStatus("Success");
		signUpResponse.setResponseMessage("It's time to spin n win!!!");
		signUpResponse.setAccessToken(tokenGenerator.generateToken());
		signUpResponse.setRefreshToken(tokenGenerator.generateToken());
		signUpResponse.setValidationRequired("Phone");
		signUpResponse.setUserId(tokenGenerator.generateUserId(signUpRequest.getAppName(),signUpRequest.getPhoneNumber(),signUpRequest.getFirstName()));
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
