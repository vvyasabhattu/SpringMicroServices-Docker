package org.evoke.cart.util;


import org.evoke.cart.model.LoginRequest;
import org.evoke.cart.model.UserResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user-service")
@RibbonClient(name="user-service")
public interface UserService {
	
	@RequestMapping(method=RequestMethod.POST, value="user/getUser")
	public UserResponse	 getUser(@RequestBody LoginRequest request);
	
}
