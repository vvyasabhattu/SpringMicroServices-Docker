package org.evoke.product.model;

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

@Entity
@Table(name = "product")
@DynamicUpdate
public class Product extends AbstractTimestampEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	@Column(unique = true)
	@NotNull
	private String product_name;
	
	private String img_path;
	
	@NotNull
	private double price ;
	
	private String description;
	
	//private String specification;
	
	//private String reviews;
	@NotNull
	private String is_deleted;
	
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

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	private String brand;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category; 


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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", img_path=" + img_path
				+ ", price=" + price + ", description=" + description + ", is_deleted=" + is_deleted + ", brand="
				+ brand + "]";
	}


}
