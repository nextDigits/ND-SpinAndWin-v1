package com.nd.entities;

import lombok.Data;

@Data
public class User {

	 public String appName;
	 public String firstName;
	 public String lastName;
	 public String emailId;
	 public String phoneNumber;
	 public String password;
	 public String verifyPassword;
	 public String addresslineone;
	 public String addresslinetwo;
	 public String state;
	 public String country;
	 public String type;
	 public String deviceId;
	 public String userId;
	 private String accessToken;
	 private String refreshToken;
}
