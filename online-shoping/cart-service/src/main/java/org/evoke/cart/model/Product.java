package org.evoke.cart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

public class Product extends AbstractTimestampEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int product_id;
	
	private String product_name;
	
	private String img_path;
	
	private String brand;
	
	private double price ;
	
	private String description;
	
	private int user_id;
	
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}


	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category; 
	
	//private String specification;
	
	//private String reviews;
	@NotNull
	private String is_deleted;
	
	private int qty;
	
	public String getIs_deleted() {
		return is_deleted;
	}

	
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}


	/**
	 * @param qty the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", img_path=" + img_path
				+ ", brand=" + brand + ", price=" + price + ", description=" + description + ", user_id=" + user_id
				+ ", is_deleted=" + is_deleted + ", qty=" + qty + "]";
	}



}
