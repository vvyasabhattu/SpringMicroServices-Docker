package org.evoke.cart.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "order")
@DynamicUpdate
public class Order {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@Column(name = "user_id")
		private int userId;
		
		@Column
		private String status;

		@OneToMany
		private List<OrderItems> orderItems;

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the userId
		 */
		public int getUserId() {
			return userId;
		}

		/**
		 * @param userId the userId to set
		 */
		public void setUserId(int userId) {
			this.userId = userId;
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
		
		
}
