package org.evoke.product.dao;

import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;

public interface ProductDao {

	
	 public ProductResponseList addProduct(Product product) ;
}
