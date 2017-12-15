package com.ibm.kickster.gateway.dto;

public class Error {
	
	private String error;
	private String description;
	
	public Error(String error, String description) {
		this.error = error;
		this.description = description;
	}

	public String getError() {
		return error;
	}

	public String getDescription() {
		return description;
	}

}
