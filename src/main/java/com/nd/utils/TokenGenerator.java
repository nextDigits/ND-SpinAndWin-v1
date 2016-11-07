package com.nd.utils;

import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * @author Kiran
 *
 */

@Component
public class TokenGenerator {

	public String generateToken() {
		UUID token = UUID.randomUUID();
		return token.toString();
	}

	public String generateUserId(String appName,String phoneNumber, String firstName) {
		return appName.concat(phoneNumber.replace(phoneNumber.substring(0, 4), String.valueOf(Instant.now().toEpochMilli()))
				.concat(firstName.toUpperCase()));
	}
}
