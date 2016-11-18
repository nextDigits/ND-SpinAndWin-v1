package com.nd.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nd.request.UserAccessRequest;

@Component
public class ForgotPasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccessRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof UserAccessRequest) {
			UserAccessRequest signInRequest = (UserAccessRequest) target;
			validateInputRequest(signInRequest, errors);
		}

	}

	private void validateInputRequest(UserAccessRequest signInRequest, Errors errors) {
		if (StringUtils.isEmpty(signInRequest.getEmailId()) || StringUtils.isEmpty(signInRequest.getPhoneNumber())) {
			errors.reject("INVALID_LOGIN_ID", "Email/phone number is null or empty");
		}
	}
}
