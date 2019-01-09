package org.evoke.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.evoke.cart.model.CartItem;
import org.evoke.cart.model.CartItemRequest;
import org.evoke.cart.model.CartItemResponse;
import org.evoke.cart.model.CartItemResponseList;
import org.evoke.cart.model.ProductResponseList;
import org.evoke.cart.repository.CartRepository;
import org.evoke.cart.util.DateUtil;
import org.evoke.cart.util.ProductService;
import org.evoke.cart.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	public CartItem addToCart(CartItemRequest cartItemReq) {
		
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
		cartItemReq.getCartItem().setCreatedUser("");  //should get these details either in the UI request or by communicating with user-service
		cartItemReq.getCartItem().setUpdatedUser("");
		return cartRepository.save(cartItemReq.getCartItem());  //this method return type should be changed to CartItemResponse
		
		/*}
		else {
			response.setErrorCode(ErrorCode.INVALID_USER);
			response.setErrorDesc(ErrorDescription.INVALID_USER);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}*/
	}
	
	public CartItem updateCartItems(CartItemRequest cartItemReq) {//should send entire object(including created_date....) in the req.
		
		cartItemReq.getCartItem().setUpdatedDate(DateUtil.getDDMMYYDate());
		return cartRepository.save(cartItemReq.getCartItem());
		
	}
	
	public void deleteCartItems(int id) {
		
			cartRepository.delete(id);
		/*
		CartItemResponse  cartItemResponse = new CartItemResponse();
		List<CartItem> cartItemResponseList  = new ArrayList<CartItem>();
		cartItemResponseList.add(cartRepository.delete(entity);(cartItemReq.getCartItem()));
		 cartItemResponse.setCartItem(cartItemResponseList);
		 return cartItemResponse;*/
		
	}
	
		
	public CartItemResponseList getAllCartItems(int user_id) {
		
		CartItemResponse  cartItemResponse = null;
		CartItemResponseList cartItemResponseList = new CartItemResponseList();
		ProductResponseList prs = null;
		List<CartItem> cartItemList  = cartRepository.findByUserIdAndStatus(user_id,"In Cart");
		List<CartItemResponse> cartItemResponseLst = new ArrayList<CartItemResponse>();
 		
		for(CartItem cartItem:cartItemList) {
			cartItemResponse = new CartItemResponse();
			prs = productService.getProductById(cartItem.getProduct_id());
			cartItemResponse.setCartItem(cartItem);
			cartItemResponse.setImg_path(prs.getProductResponse().get(0).getImg_path());
			cartItemResponse.setPrice(prs.getProductResponse().get(0).getPrice());
			cartItemResponse.setProduct_name(prs.getProductResponse().get(0).getProduct_name());
			cartItemResponseLst.add(cartItemResponse);
		}
		
		cartItemResponseList.setCartItemResponse(cartItemResponseLst);
		
		 return cartItemResponseList;
		//return  new ResponseEntity<cartItemResponse>(cartItemResponse,HttpStatus.OK);
		
	}
	

}
