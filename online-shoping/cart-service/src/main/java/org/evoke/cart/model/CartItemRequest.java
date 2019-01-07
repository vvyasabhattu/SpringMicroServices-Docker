package org.evoke.cart.model;

import java.io.Serializable;

public class CartItemRequest extends BaseRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CartItem cartItem;

	/**
	 * @return the cartItem
	 */
	public CartItem getCartItem() {
		return cartItem;
	}

	/**
	 * @param cartItem the cartItem to set
	 */
	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}


}
