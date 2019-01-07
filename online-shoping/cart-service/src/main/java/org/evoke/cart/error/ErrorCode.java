package org.evoke.cart.error;

public interface ErrorCode {

	int USER_ALREADY_EXISTS = 1000;
	int EMAIL_ALREADY_EXISTS = 1001;
	int USER_NOT_FOUND = 1002;
	int PASSWORD_NOT_VALID = 1003;
	int VALID_NUMBER_REQUIRED = 1004;
	int USER_DETAILS_OBJECT_NOT_FOUND = 1005;
	int EMAIL_NOT_VALID = 1006;
	int PRODUCT_NOT_VALID = 1007;
	int PRODUCT_NOT_FOUND = 1008;
	int INTERNAL_SERVER_ERROR = 500 ;
	int PRODUCT_ALREADY_EXISTS = 1009;
	int INVALID_USER = 1010;
	int PATH_ENCODING_ERROR = 1011;
	int DB_ERROR = 1012;
	int IMG_SAVE_ERROR = 1013;
	int PRODUCT_RESPONSE_MAPPING_ERROR = 1014;
	int invalid_input_paramater = 1016;
	//int PRODUCT_NOT_VALID 
}
