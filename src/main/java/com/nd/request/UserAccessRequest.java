package com.nd.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
public class UserAccessRequest {
	private String appName;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNumber;
	private String password;
	private String verifyPassword;
	private String addressFirstLine;
	private String addressNextLine;
	private String state;
	private String country;
	private String type;
	private String accessToken;
	private String refreshToken;
	private String deviceId;
	private String userId;
}
