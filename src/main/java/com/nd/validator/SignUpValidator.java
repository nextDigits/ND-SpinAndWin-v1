package com.nd.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nd.request.UserAccessRequest;

@Component
public class SignUpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserAccessRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof UserAccessRequest) {
			UserAccessRequest signUpRequest = (UserAccessRequest) target;
			validateInputRequest(signUpRequest, errors);
		}

	}

	private void validateInputRequest(UserAccessRequest signUpRequest, Errors errors) {
		if (StringUtils.isEmpty(signUpRequest.getFirstName())) {
			errors.reject("INVALID_FIRST_NAME", "First name is null or empty");
		}

		if (StringUtils.isEmpty(signUpRequest.getLastName())) {
			errors.reject("INVALID_LAST_NAME", "Last name is null or empty");
		}

		if (StringUtils.isEmpty(signUpRequest.getEmailId())) {
			errors.reject("INVALID_EMAIL_ID", "Email id null or empty");
		}

		if (StringUtils.isEmpty(signUpRequest.getPhoneNumber())) {
			errors.reject("INVALID_PHONE_NUMBER", "Phone number is null or empty");
		}
		
		if (StringUtils.isEmpty(signUpRequest.getCountry())) {
			errors.reject("INVALID_COUNTRY", "Country is null or empty");
		}
		
		if (StringUtils.isEmpty(signUpRequest.getState())) {
			errors.reject("INVALID_STATE", "State is null or empty");
		}
		
		if (StringUtils.isEmpty(signUpRequest.getDeviceId())) {
			errors.reject("INVALID_DEVICE_ID", "Device id is null or empty");
		}
		
		if (StringUtils.isEmpty(signUpRequest.getPassword())) {
			errors.reject("INVALID_PASSWORD", "Password is null or empty");
		}else if (!(signUpRequest.getPassword().equals(signUpRequest.getVerifyPassword()))) {
			errors.reject("INVALID_PASSWORD_MATCH", "Password and verify password not matching");
		}
	}
}
