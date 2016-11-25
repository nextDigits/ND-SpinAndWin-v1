package com.nd.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nd.request.UserAccessRequest;

@Component
public class ValidateValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccessRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof UserAccessRequest) {
			UserAccessRequest validateRequest = (UserAccessRequest) target;
			validateInputRequest(validateRequest, errors);
		}

	}

	private void validateInputRequest(UserAccessRequest validateRequest, Errors errors) {
		if (StringUtils.isEmpty(validateRequest.getAccessToken()) && StringUtils.isEmpty(validateRequest.getRefreshToken())) {
			errors.reject("INVALID_TOKEN", "Invalid access token");
		}

		if (StringUtils.isEmpty(validateRequest.getUserId())) {
			errors.reject("INVALID_USER_ID", "Invalid user id");
		}
	}
}
