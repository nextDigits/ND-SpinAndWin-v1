package com.nd.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class RedeemRewardsResponse {
	private String status;
	private String responseMessage;
	private String remainingPoints;
	private String usedPoints;
}
