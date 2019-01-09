package org.evoke.cart.model;

import java.util.List;

public class CartItemResponse {
	
	private CartItem cartItem;
	
	private String product_name;
	
	private double price;
	
	private String img_path;


	/**
	 * @return the product_name
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * @param product_name the product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the img_path
	 */
	public String getImg_path() {
		return img_path;
	}

	/**
	 * @param img_path the img_path to set
	 */
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	/**
	 * @param cartItem the cartItem to set
	 */
	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	/**
	 * @return the cartItem
	 */
	public CartItem getCartItem() {
		return cartItem;
	}

	
}
