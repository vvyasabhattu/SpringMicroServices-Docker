package org.evoke.cart.controller;

import org.evoke.cart.error.ErrorCode;
import org.evoke.cart.error.ErrorMessage;
import org.evoke.cart.error.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
	   
		@ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
			ErrorMessage error = new ErrorMessage();
	        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        error.setErrorDesc("Please contact your administrator");
	        error.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
	        return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	    }
		
		@ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<ErrorMessage> illegalArgumentExceptionHandler(Exception ex) {
			ErrorMessage error = new ErrorMessage();
	        error.setErrorCode(ErrorCode.invalid_input_paramater);
	        error.setErrorDesc("invalid input paramater");
	        error.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
	        return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	    }
	
}

