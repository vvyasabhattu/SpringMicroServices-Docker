package org.evoke.product.dao;

import java.util.List;

import org.evoke.product.model.Category;
import org.evoke.product.model.CategoryResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao {
	
	@Autowired
	private Session session;
	
	CategoryResponse response = null;
	
	public CategoryResponse getCategories() {

		response  =  new CategoryResponse();
		
		List<Category> categoryList = session.createCriteria(Category.class).list();// ht.find("from Product");
		 response.setCategory(categoryList);
		 
		 return response;
		
	}

}
