package org.evoke.cart.model;

import java.util.List;

public class CartItemResponse extends BaseResponse{

	
	private List<CartItem> cartItem;

	/**
	 * @return the cartItem
	 */
	public List<CartItem> getCartItem() {
		return cartItem;
	}

	/**
	 * @param cartItem the cartItem to set
	 */
	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}
	
	
}
