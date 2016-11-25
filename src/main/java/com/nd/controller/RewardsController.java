/**
 * 
 *//*
package com.nd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nd.dao.RewardsDao;
import com.nd.request.UserAccessRequest;
import com.nd.response.UserAccessResponse;

*//**
 * @author NextDigit
 *
 *//*

@Validated
@RestController
public class RewardsController {
	
	@Autowired
	private RewardsDao rewardsDao;
	
	@RequestMapping(value = "/api/v1/rewards/view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String signUp(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String points = rewardsDao.getAvailablePoints("111");
		return points;
	}

}
*/