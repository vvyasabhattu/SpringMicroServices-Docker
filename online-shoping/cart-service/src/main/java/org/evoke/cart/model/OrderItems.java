package org.evoke.cart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
public class OrderItems extends AbstractTimestampEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_item_id;
	
	@OneToOne
	private Product product;
	
	@Column
	//@NotNull
	private int qty;
	
	@Column
	//@NotNull
	private double total_price;
	
	private String status;
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;

	/**
	 * @return the order_item_id
	 */
	public int getOrder_item_id() {
		return order_item_id;
	}

	/**
	 * @param order_item_id the order_item_id to set
	 */
	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
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

	/**
	 * @return the total_price
	 */
	public double getTotal_price() {
		return total_price;
	}

	/**
	 * @param total_price the total_price to set
	 */
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
