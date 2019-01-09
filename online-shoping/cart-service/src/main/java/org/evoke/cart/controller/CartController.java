package org.evoke.cart.controller;

import java.util.List;

import org.evoke.cart.model.CartItem;
import org.evoke.cart.model.CartItemRequest;
import org.evoke.cart.model.CartItemResponse;
import org.evoke.cart.model.CartItemResponseList;
import org.evoke.cart.service.CartService;
import org.evoke.cart.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/addCartItem")
	public CartItem addCartItem(@RequestBody CartItemRequest cartItem) {
		
		return cartService.addToCart(cartItem);
		
	}
	
	
	@PutMapping("/updateCartItem")
	public  CartItem  updateCartItems(@RequestBody CartItemRequest cartItem) {
		
		return cartService.updateCartItems(cartItem);
		
	}
	
	@DeleteMapping("/deleteCartItem/{id}")
	public  void  deleteCartItems(@PathVariable int id) {
		
		 cartService.deleteCartItems(id);
		
	}
	
	
	@GetMapping("/getAllCartItemsByUser/{user_id}")
	public CartItemResponseList getAllCartItems(@PathVariable int user_id){
		
		return cartService.getAllCartItems(user_id);
	}
	
}
