package com.nd.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class CloverAppException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public CloverAppException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public CloverAppException() {
		super();
	}
}
