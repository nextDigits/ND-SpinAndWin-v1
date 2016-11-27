package com.nd.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nd.request.RedeemRewardsRequest;

@Component
public class RedeemRewardsValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return RedeemRewardsRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof RedeemRewardsRequest) {
			RedeemRewardsRequest redeemRewardsRequest = (RedeemRewardsRequest) target;
			validateInputRequest(redeemRewardsRequest, errors);
		}

	}

	private void validateInputRequest(RedeemRewardsRequest redeemRewardsRequest, Errors errors) {
		if (StringUtils.isEmpty(redeemRewardsRequest.getRewardId())) {
			errors.reject("INVALID_REWARD_ID", "RewardId is null or empty");
		}
		if (StringUtils.isEmpty(redeemRewardsRequest.getUserId())) {
			errors.reject("INVALID_USER_ID", "UserId is null or empty");
		}
	}
}
