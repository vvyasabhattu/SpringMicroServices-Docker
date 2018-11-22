package org.evoke.product.controller;

import org.evoke.product.error.ErrorCode;
import org.evoke.product.error.ErrorType;
import org.evoke.product.model.CategoryResponse;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	CategoryResponse response = null;
	
	@GetMapping
	public CategoryResponse getCategories() {
		
		try {
			return categoryService.getCategories();
			}
			catch(Exception e) {
				response = new CategoryResponse();
				response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
		
	}
	

}
