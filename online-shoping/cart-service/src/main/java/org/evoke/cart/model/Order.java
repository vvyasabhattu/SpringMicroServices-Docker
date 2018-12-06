package org.evoke.cart.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order extends AbstractTimestampEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="order_id")
	private List<OrderItems> orderItems;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the orderItems
	 */
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

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

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", status=" + status + "]";
	}
	


}
