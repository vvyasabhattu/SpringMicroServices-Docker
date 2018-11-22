package org.evoke.product.dao;

import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;

public interface ProductDao {

	
	 public ProductResponseList addProduct(Product product) ;
	 
	 public ProductResponseList updateProduct(Product product) ;
	 
	 public ProductResponseList deleteProduct(Product product) ;
	 
	 public ProductResponseList getProducts();
	 
	 public ProductResponseList getProductById(int id);
	 
	 public ProductResponseList getProductsByCategoryId(int id);
	 
	 public ProductResponseList getProductsByUserId(int id);
	 
}
