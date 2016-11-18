package com.nd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.nd.request.UserAccessRequest;
import com.nd.response.UserAccessResponse;
import com.nd.service.UserAccessService;
import com.nd.validator.ForgotPasswordValidator;
import com.nd.validator.SignInValidator;

@Validated
@RestController
public class ForgotPasswordController {

	private final ForgotPasswordValidator forgotPasswordValidator;

	private final UserAccessService userAccessService;

	@Autowired
	public ForgotPasswordController(ForgotPasswordValidator forgotPasswordValidator, UserAccessService userAccessService) {
		this.forgotPasswordValidator = forgotPasswordValidator;
		this.userAccessService = userAccessService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(forgotPasswordValidator);
	}
	
	@RequestMapping(value = "/nextdigit/v1/useraccess/forgotpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserAccessResponse signIn(final HttpServletRequest request, final HttpServletResponse response,
			@RequestBody @Valid UserAccessRequest signInRequest) throws Exception {
		return userAccessService.forgotPassword(signInRequest);
	}
}
