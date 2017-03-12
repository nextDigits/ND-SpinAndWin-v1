/**
 * 
 */
package com.nd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nd.service.RewardsService;

/**
 * @author NextDigit
 *
 */

@Validated
@RestController
public class RewardsController {
	
	@Autowired
	private RewardsService rewardsService;
	
	static Logger logger = Logger.getLogger(RewardsController.class);
	
	/**
	  * Returns the available reward points of a user atO any given point of time.
	  * 
	  * @param userId
	  * @return available points
	  */
	@RequestMapping(value = "/api/v1/rewards/view", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String viewRewardPoints(@NotNull(message="User Id cannot be null or empty") @RequestParam(value = "userId", required = true) String userId,
			final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		if(!StringUtils.isEmpty(userId)){
			logger.error("User Id: " + userId);
			return rewardsService.getAvailablePoints(userId);
		}
		logger.error("User Id null or empty");
		return null;
	}
}
