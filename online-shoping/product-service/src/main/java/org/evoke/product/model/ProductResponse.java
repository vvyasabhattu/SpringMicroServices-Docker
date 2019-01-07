package org.evoke.product.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


public class ProductResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	@NotNull
	private String product_name;
	
	private int seller_id;
	
	private String img_path;
	
	@NotNull
	private double price ;
	
	private String description;
	
	private int qty;
	
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

	/**
	 * @return the is_deleted
	 */
	public String getIs_deleted() {
		return is_deleted;
	}

	/**
	 * @param is_deleted the is_deleted to set
	 */
	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	private String is_deleted;
	
	//private String specification;
	
	//private String reviews;
	
	/**
	 * @return the brand
	 */

	private String brand;
	
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category; 

	
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
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
	 * @return the seller_id
	 */
	public int getSeller_id() {
		return seller_id;
	}

	/**
	 * @param seller_id the seller_id to set
	 */
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductResponse [product_id=" + product_id + ", product_name=" + product_name + ", seller_id="
				+ seller_id + ", img_path=" + img_path + ", price=" + price + ", description=" + description
				+ ", brand=" + brand + "]";
	}
	


	
	
}
