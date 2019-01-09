package org.evoke.cart.util;


import org.evoke.cart.model.LoginRequest;
import org.evoke.cart.model.UserResponse;
import org.evoke.cart.model.ProductResponseList;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="product-service")
@RibbonClient(name="product-service")
public interface ProductService {
	
	@RequestMapping(method=RequestMethod.POST, value="product/{product_id}")
	public @ResponseBody ProductResponseList getProductById(@PathVariable("product_id") int product_id); 

}
