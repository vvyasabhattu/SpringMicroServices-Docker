package org.evoke.product.service;

import org.evoke.product.dao.CategoryDao;
import org.evoke.product.model.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	 public CategoryResponse getCategories() {
		  return categoryDao.getCategories();
	  }

}
