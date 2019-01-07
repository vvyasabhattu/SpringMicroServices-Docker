package org.evoke.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.evoke.cart.model.CartItem;
import org.evoke.cart.model.CartItemRequest;
import org.evoke.cart.model.CartItemResponse;
import org.evoke.cart.repository.CartRepository;
import org.evoke.cart.util.DateUtil;
import org.evoke.cart.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	public CartItemResponse addToCart(CartItemRequest cartItemReq) {
		
		/*if(pRequest.getProduct().getUser_id()!=0) {
			user=new User();
			user.setId(pRequest.getProduct().getUser_id());	
		}else {
			response.setErrorCode(ErrorCode.USER_ID_NOT_FOUND_IN_REQUEST);
			response.setErrorDesc(ErrorDescription.USER_ID_NOT_FOUND_IN_REQUEST);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}*/
		
		
		/*User user=new User();
		user.setId(cart.getUserId()	);
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUser(user);
		
		UserResponse userResponse = userService.getUser(loginRequest);
		
		if (null != userResponse && null != userResponse.getUserLst() && userResponse.getUserLst().size()>0) {*/
		
		cartItemReq.getCartItem().setCreatedDate(DateUtil.getDDMMYYDate());
		cartItemReq.getCartItem().setUpdatedDate(DateUtil.getDDMMYYDate());
		cartItemReq.getCartItem().setCreatedUser("");
		cartItemReq.getCartItem().setUpdatedUser("");
		
		CartItemResponse  cartItemResponse = new CartItemResponse();
		List<CartItem> cartItemResponseList  = new ArrayList<CartItem>();
		cartItemResponseList.add(cartRepository.save(cartItemReq.getCartItem()));
		 cartItemResponse.setCartItem(cartItemResponseList);
		 return cartItemResponse;
		
		/*}
		else {
			response.setErrorCode(ErrorCode.INVALID_USER);
			response.setErrorDesc(ErrorDescription.INVALID_USER);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}*/
	}
	
	public CartItemResponse updateCartItems(CartItemRequest cartItemReq) {
		
		CartItemResponse  cartItemResponse = new CartItemResponse();
		List<CartItem> cartItemResponseList  = new ArrayList<CartItem>();
		cartItemResponseList.add(cartRepository.save(cartItemReq.getCartItem()));
		 cartItemResponse.setCartItem(cartItemResponseList);
		 return cartItemResponse;
		
	}
	
	public void deleteCartItems(CartItemRequest cartItemReq) {
		
			cartRepository.delete(cartItemReq.getCartItem().getId());
		/*
		CartItemResponse  cartItemResponse = new CartItemResponse();
		List<CartItem> cartItemResponseList  = new ArrayList<CartItem>();
		cartItemResponseList.add(cartRepository.delete(entity);(cartItemReq.getCartItem()));
		 cartItemResponse.setCartItem(cartItemResponseList);
		 return cartItemResponse;*/
		
	}
	
		
	public CartItemResponse getAllCartItems(int user_id) {
		
		CartItemResponse  cartItemResponse = new CartItemResponse();
		List<CartItem> cartItemResponseList  = cartRepository.findByUserId(user_id);
		 cartItemResponse.setCartItem(cartItemResponseList);
		 return cartItemResponse;
		//return  new ResponseEntity<cartItemResponse>(cartItemResponse,HttpStatus.OK);
		
	}
	
	
	

}
