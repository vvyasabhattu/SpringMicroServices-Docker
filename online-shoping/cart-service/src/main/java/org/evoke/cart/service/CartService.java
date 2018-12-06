package org.evoke.cart.service;

import org.evoke.cart.dao.CartDao;
import org.evoke.cart.model.Order;
import org.evoke.cart.model.OrderItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao;
	
	public Order addToCart(Order order) {
		
		return cartDao.addToCart(order);
		
	}

}
