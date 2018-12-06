package org.evoke.cart.controller;

import org.evoke.cart.model.Order;
import org.evoke.cart.model.OrderItems;
import org.evoke.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	
	@PostMapping("/addToCart")
	public Order addToCart(@RequestBody Order order) {
		
		return cartService.addToCart(order);
		
	}
	
/*	
	@GetMapping("/viewCart")
	public Order viewCart() {
		//return cartService.viewCart(order);
		
	}*/
	
}
