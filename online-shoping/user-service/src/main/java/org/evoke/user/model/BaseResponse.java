package org.evoke.user.model;

import java.util.Map;

import org.evoke.user.web.error.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseResponse extends ErrorMessage{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,Object> response;

	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
		
	
}
