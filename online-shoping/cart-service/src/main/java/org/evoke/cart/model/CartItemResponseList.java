package org.evoke.cart.model;

import java.util.List;

public class CartItemResponseList extends BaseResponse{
	
	private List<CartItemResponse> cartItemResponse;

	/**
	 * @return the cartItemResponse
	 */
	public List<CartItemResponse> getCartItemResponse() {
		return cartItemResponse;
	}

	/**
	 * @param cartItemResponse the cartItemResponse to set
	 */
	public void setCartItemResponse(List<CartItemResponse> cartItemResponse) {
		this.cartItemResponse = cartItemResponse;
	}

	
	

}
