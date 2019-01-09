package org.evoke.cart.repository;

import java.util.List;

import org.evoke.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Integer>{

	public List<CartItem> findByUserId(int userId);
	
	public List<CartItem> findByUserIdAndStatus(int userId,String status);
		
	
}
