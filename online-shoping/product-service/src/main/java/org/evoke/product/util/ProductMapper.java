package org.evoke.product.util;

import java.util.List;

import org.evoke.product.model.Category;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
	
	@Autowired
	private Session session;
	
	Category category = null;
	
	public void map(List<Product> products,List<ProductResponse> productResponses) {
		
		ProductResponse productResponse =null;
		
		if(products!=null) {
		for (Product product : products) {
			 
			productResponse = new ProductResponse();
			
			productResponse.setProduct_id(product.getProduct_id());
			productResponse.setProduct_name(product.getProduct_name());
			productResponse.setDescription(product.getDescription());
			productResponse.setPrice(product.getPrice());
			productResponse.setBrand(product.getBrand());
			productResponse.setQty(product.getQty());
			productResponse.setIs_deleted(product.getIs_deleted());
			
			category = session.get(Category.class, product.getCategory().getCategory_id());
			
			productResponse.setCategory(category);
			productResponse.setImg_path(product.getImg_path());
			productResponse.setSeller_id(product.getUser_id());
			
			productResponses.add(productResponse);
			
		}
		}
		
			
			
		}
		
	}
	



