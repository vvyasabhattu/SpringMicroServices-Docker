package org.evoke.product.service;

import java.util.List;

import org.evoke.product.model.Product;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;

public interface ProductService {
	
	 public ProductResponseList addProduct(ProductRequest pr);
	 
	 public ProductResponseList updateProduct(ProductRequest pr);
	 
	 public ProductResponseList deleteProduct(ProductRequest pr);
	 
	 public ProductResponseList getProducts();
	 
	 public ProductResponseList getProductById(int id) ;
	 
	 public ProductResponseList getProductsByUserId(int id);
	 
	 public ProductResponseList getProductsByCategoryId(int id);
	 
	// public boolean CheckProductName(String productName);
	 
}
