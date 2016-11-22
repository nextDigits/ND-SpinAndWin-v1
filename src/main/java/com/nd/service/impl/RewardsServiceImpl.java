package com.nd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nd.request.UserAccessRequest;
import com.nd.response.UserAccessResponse;
import com.nd.service.RewardsService;
import com.nd.utils.TokenGenerator;

/**
 * @author NextDigit
 *
 */

@Service
public class RewardsServiceImpl implements RewardsService {
	
	public final TokenGenerator tokenGenerator;
	
	@Autowired
	public RewardsServiceImpl(TokenGenerator tokenGenerator){
		this.tokenGenerator = tokenGenerator;
	}
}
