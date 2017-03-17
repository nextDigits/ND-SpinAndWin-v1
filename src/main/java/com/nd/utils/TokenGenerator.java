package com.nd.utils;

import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
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

	public String generateUserId(String userType, String counter) {
		Random random = new Random();
		return StringUtils.join(userType,"-9",counter,String.valueOf(random.nextInt(10000)));
	}
}
