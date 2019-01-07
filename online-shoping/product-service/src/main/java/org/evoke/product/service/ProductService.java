package org.evoke.product.service;

import java.util.List;

import org.evoke.product.model.Product;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.model.User;

public interface ProductService {
	 
	 public ProductResponseList deleteProduct(ProductRequest pr);
	 
	 public ProductResponseList getProducts();
	 
	 public ProductResponseList getProductById(int id) ;
	 
	 public ProductResponseList getProductsByUserId(int id);
	 
	 public ProductResponseList getProductsByCategoryId(int id);
	 
	public boolean IsProductExists(String productName);

	ProductResponseList addProduct(ProductRequest pr, User user);

	ProductResponseList updateProduct(ProductRequest pr, User user);
	 
}
