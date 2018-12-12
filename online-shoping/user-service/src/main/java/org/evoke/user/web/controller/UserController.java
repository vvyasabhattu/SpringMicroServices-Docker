
/*
 * Licensed to the Evoke Technologies under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The Evoke Application  licenses this file to You under the Evoke License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.evoketechnologies.org/licenses/LICENSE-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.evoke.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.evoke.user.model.AddressReq;
import org.evoke.user.model.AddressResponse;
import org.evoke.user.model.AddressResponseLst;
import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.LoginRequest;
import org.evoke.user.model.UserResponse;
import org.evoke.user.service.UserServiceImpl;
import org.evoke.user.web.error.ErrorCode;
import org.evoke.user.web.error.ErrorDescription;
import org.evoke.user.web.error.ErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**********************************************************************************
 * Name : UserController.java Desc : This is class is used for User actions
 * Error: Modification : Version Name Date Desc Initial UserController.java
 * 05-Nov-2018 User Controller
 * 
 ***********************************************************************************/

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserServiceImpl userService;

	@GetMapping("/check")
	public String check() {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");
		return "successful";
	}

	@PostMapping(value = "/registerUser")
	public UserResponse add(@RequestBody LoginRequest request) {

		UserResponse response = null;
		if (null != request && null != request.getUser()) {

			response = userService.registerUser(request.getUser());

		} else {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_EMIAL_EXIST);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}

	@PostMapping(value = "/getUser")
	public UserResponse getUser(@RequestBody LoginRequest request) {

		UserResponse response = null;
		if (null != request && null != request.getUser()) {

			response = userService.getUser(request.getUser().getId());
		} else {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}
	

	@PostMapping(value = "/login")
	public UserResponse loginUser(@RequestBody LoginRequest request) {

		UserResponse response = null;
		if (null != request && null != request.getUser()) {

			response = userService.userLogin(request.getUser());

		} else if (null != request.getUser().getEmail() && StringUtils.isNotEmpty(request.getUser().getEmail())) {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.EMAIL_NOT_VALID);
			response.setErrorDesc(ErrorDescription.USER_EMAIL_NOT_PROVIDED);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);

		} else {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);

		}

		return response;

	}

	@PostMapping(value = "/updateUser")
	public UserResponse updateUser(@RequestBody LoginRequest request) {
		UserResponse response = null;

		if (null != request && null != request.getUser()) {

			response = userService.updateUser(request.getUser());
		} else {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}

	@PostMapping(value = "/updateAddress")
	public AddressResponse updateUserAddress(@RequestBody AddressReq request) {

		AddressResponse response = null;

		if (null != request && null != request.getAddress()) {

			response = userService.updateUserAddress(request);
		} else {
			response = new AddressResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}
	
	@PostMapping(value = "/updateDefaultAddress")
	public AddressResponse updateUserDefaultAddress(@RequestBody AddressReq request) {

		AddressResponse response = null;

		if (null != request && null != request.getAddress()) {

			response = userService.updateUserDefaultAddress(request);
		} else {
			response = new AddressResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}

	@PostMapping(value = "/updateRole")
	public UserResponse updateRole(@RequestBody LoginRequest request) {
		UserResponse response = null;

		if (null != request && null != request.getUser()) {

			response = userService.updateUserRole(request.getUser());
		} else {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}

		return response;

	}

	@PostMapping(value = "/addAddress")
	public AddressResponse addAddress(@RequestBody AddressReq adrReq) {
		AddressResponse response = null;

		if (null != adrReq && null != adrReq.getAddress()) {
			response = userService.insertAddress(adrReq);
		} else {
			response = new AddressResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}
		return response;
	}

	@PostMapping(value = "/deleteAddress")
	public BaseResponse deleteAddress(@RequestBody AddressReq adrReq) {
		return userService.deleteAddress(adrReq);
	}

	@PostMapping(value = "/deleteUser")
	public BaseResponse deleteUser(@RequestBody LoginRequest request) {
		return userService.deleteUser(request.getUser());

	}
	
	@PostMapping(value = "/getAddress")
	public AddressResponseLst getAddress(@RequestBody AddressReq request) {

		AddressResponseLst response = null;
		if (null != request && null != request.getAddress()) {

			response = userService.getAddress(request.getAddress().getUser().getId());
		}

		return response;

	}
	
}
