package org.evoke.product.controller;


import org.evoke.product.model.LoginRequest;
import org.evoke.product.model.UserResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user-service")
@RibbonClient(name="user-service")
public interface UserService {

	
	@RequestMapping(method=RequestMethod.GET, value="user/check")
	public String getCheck();
	
	@RequestMapping(method=RequestMethod.POST, value="user/login")
	public UserResponse loginUser(@RequestBody LoginRequest request);
	
	@RequestMapping(method=RequestMethod.POST, value="user/getUser")
	public UserResponse	 getUser(@RequestBody LoginRequest request);
}
