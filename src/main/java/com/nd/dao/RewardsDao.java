/**
 * 
 */
package com.nd.dao;

import org.springframework.stereotype.Component;

import com.nd.response.UserAccessResponse;

/**
 * @author NextDigit
 *
 */
@Component
public interface RewardsDao {

	String getAvailablePoints(String string);

}
