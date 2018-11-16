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
	
	
	
	public void map(List<Product> products,List<ProductResponse> productResponses) {
		
		ProductResponse productResponse =null;
		
		
		for (Product product : products) {
			 
			productResponse = new ProductResponse();
			
			productResponse.setProduct_id(product.getProduct_id());
			productResponse.setProduct_name(product.getProduct_name());
			productResponse.setDescription(product.getDescription());
			productResponse.setPrice(product.getPrice());
			productResponse.setBrand(product.getBrand());
			productResponse.setCategory(product.getCategory());
			productResponse.setImg_path(product.getImg_path());
			productResponse.setSeller_id(product.getUser().getId());
			
		}
		
		productResponses.add(productResponse);	
			
		}
		
	}
	



