package org.evoke.product.error;

import java.io.Serializable;

public class ErrorMessage implements Serializable{

	private int errorCode;
	private String errorDesc;
	private String errorType;
	
	public ErrorMessage() {
		
		this.errorCode = 0;
		this.errorType = "SUCCESS";
		this.errorDesc = "SUCCESS";
	}
	public ErrorMessage (int errorCode, String errorType, String errorDesc) {
		
		
		this.errorCode = errorCode;
		this.errorType = errorType;
		this.errorDesc = errorDesc;
		 
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	
	
	
}
