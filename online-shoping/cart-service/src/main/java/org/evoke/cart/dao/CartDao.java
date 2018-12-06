package org.evoke.cart.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.evoke.cart.model.Order;
import org.evoke.cart.model.OrderItems;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {
	
	@Autowired
	private Session session;
	
	public Order addToCart(Order order) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		String currentDate = sdf.format(new Date());
		Query query = session.createQuery("from Order where user_id=:id and status='pending' ");//get(entityType, id); oi.getProduct().getProduct_id();
		query.setInteger("id", order.getUser().getId());
		query.list();
		
		order.setStatus("pending");
		order.setCreatedDate("");
		order.setUpdatedDate("");
		order.setCreatedUser("");
		order.setUpdatedUser("");
		
		List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
		List<OrderItems> orderItems = order.getOrderItems();
		if(orderItems !=null && orderItems.size() >0) {
		for (OrderItems orderItems2 : orderItems) {
			
			orderItems2.setCreatedDate(currentDate);
			orderItems2.setCreatedUser("");
			orderItems2.setUpdatedDate("");
			orderItems2.setUpdatedUser("");
			orderItemsList.add(orderItems2);
		}
		order.setOrderItems(orderItemsList);
		}
		
		
		if(query.list().size()==0) {  //create order and then add orderitem
			session.save(order);
			session.flush();
			
		}else { // directly add OrderItem
			
			 orderItems = order.getOrderItems();
			if(orderItems !=null && orderItems.size() >0) {
			for (OrderItems orderItems1 : orderItems) {
				
				session.save(orderItems1);
				session.flush();
				
			}
			}
		}
		
		return order;
		
	}

}
