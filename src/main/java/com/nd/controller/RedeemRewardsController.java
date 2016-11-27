/**
 * 
 */
package com.nd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nd.request.RedeemRewardsRequest;
import com.nd.response.RedeemRewardsResponse;
import com.nd.service.RewardsService;
import com.nd.validator.RedeemRewardsValidator;

/**
 * @author NextDigit
 *
 */

@Validated
@RestController
public class RedeemRewardsController {
	
	@Autowired
	private RewardsService rewardsService;
	
	@Autowired
	RedeemRewardsValidator redeemRewardsValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(redeemRewardsValidator);
	}
	
	static Logger logger = Logger.getLogger(RedeemRewardsController.class);
	
	/**
	  * This method allows a user to redeem a reward.
	  * 
	  * @param userId
	  * @param rewardId
	  * @return remaining points
	  * @return used points
	  */
	@RequestMapping(value = "/api/v1/rewards/redeem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public RedeemRewardsResponse redeemReward(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody @Valid RedeemRewardsRequest redeemRewardsRequest) throws Exception {
		return rewardsService.redeemReward(redeemRewardsRequest);
	}

}
