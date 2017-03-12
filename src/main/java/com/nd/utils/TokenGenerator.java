package com.nd.utils;

import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * @author Jyotsna
 *
 */

@Component
public class TokenGenerator {

	public String generateToken() {
		UUID token = UUID.randomUUID();
		return token.toString();
	}

	public String generateUserId(String appName,String phoneNumber) {
		Random random = new Random(System.nanoTime());
		String randomInt = String.valueOf(random.nextInt(10000));
		return appName.concat(phoneNumber.replace(phoneNumber.substring(0, 4), randomInt));
	}
}
