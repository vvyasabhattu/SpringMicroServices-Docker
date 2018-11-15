package org.evoke.product.util;

import java.util.List;

import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.User;
import org.evoke.product.model.User_address;
import org.evoke.product.model.User_product;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
	
	/*@Autowired
	  private HibernateTemplate ht;*/
	
	public void map(List<Product> products,List<Product> productsTos,HibernateTemplate ht) {
		Product productTo =null;
		List<User> user = null;
		List<User_product>  up = null;
		List<User_address> ua = null;
		
		for (Product product : products) {
			 
			productTo = new Product();
			
			productTo.setProduct_id(product.getProduct_id());
			productTo.setProduct_name(product.getProduct_name());
			productTo.setDescription(product.getDescription());
			productTo.setPrice(product.getPrice());
			
		 up = (List<User_product>) ht.findByNamedParam("from User_product where product_id=:pi", new String[] { "pi" }, new Object[] {product.getProduct_id()});
		
		 
		if(up!=null && up.size()!=0)
		  user = (List<User>) ht.findByNamedParam("from UserDetails where id=:i", new String[] { "i" }, new Object[] { up.get(0).getUser_id() });
		
		
		if(user!=null && user.size()!=0) {
			
			//ua = (List<User_address>) ht.findByNamedParam("from User_address where user_id=ui", new String[] { "ui" }, new Object[] {user.get(0).getId()});
			
			
			
			
			productTo.setUser(user.get(0));
		}
		
			 productsTos.add(productTo);	
			
		}
		
	}
	


}
