package org.evoke.product.error;

public interface ErrorDescription {
	
	String USER_ALREADY_EXIST = "User already exists";
	String USER_EMIAL_EXIST = "Account already exists";
	String USER_NOT_FOUND = "User not found";
	String PASSWORD_NOT_VALID = "Entered passowrd is not valid..try again!!";
	String VALID_NUMBER_REQUIRED = "Please enter a valid number";
	String USER_DETAILS_OBJECT_NOT_FOUND = "User Object not found"; 
	String USER_EMAIL_NOT_PROVIDED = "Please provide valid email";
	String PRODUCT_NOT_FOUND = "Product Object not found";
	String PRODUCT_ALREADY_EXISTS = "Product already exists" ;
	String INVALID_USER = "Invalid User role ";
	String PATH_ENCODING_ERROR = "Error in getting web app path";
	String IMG_SAVE_ERROR = "Error in saving image";
	String USER_ID_NOT_FOUND_IN_REQUEST = "User id not found in request";
}
