package org.evoke.product.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.evoke.product.dao.ProductDaoImpl;
import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class ProductServiceImpl implements ProductService {
		
	@Autowired
    private ProductDaoImpl productDao;
	
	private Product product;
	
	 @PostConstruct
	 public void init() {
	    	product = new Product();
	 }
	  
	 @Override
	 public ProductResponseList addProduct(ProductRequest pr) {
		  
		 ProductResponseList baseResponse  = null;
		 if(null != pr  && null != pr.getProduct() ) {
			 baseResponse =  productDao.addProduct(pr.getProduct());
		 }
		   return baseResponse;
		   
	  }
	 
	 
	 @Override
		public ProductResponseList updateProduct(ProductRequest pr) {
			 
			ProductResponseList baseResponse  = null;
			 if(null != pr  && null != pr.getProduct() ) {
				 baseResponse =  productDao.updateProduct(pr.getProduct());
			 }
			   return baseResponse;
			   
		}
	 
	 @Override
	 public ProductResponseList deleteProduct(ProductRequest pr) {
		 
			ProductResponseList baseResponse  = null;
				 baseResponse =  productDao.deleteProduct(pr.getProduct());
			   return baseResponse;
			   
		}
	 
	 @Override
	  public ProductResponseList getProducts() {
		  return productDao.getProducts();
	  }
	  
	 @Override
	  public ProductResponseList getProductById(int id) {
		  return productDao.getProductById(id);
	  }
	  
	 @Override
	  public ProductResponseList getProductsByUserId(int id) {
		  return productDao.getProductsByUserId(id);
	  }

	 @Override
	public ProductResponseList getProductsByCategoryId(int id) {
		
		 return productDao.getProductsByCategoryId(id);
	}
	 
	 
	 public ProductResponseList updateProductImgPath(int id,String path) {
		 return productDao.updateProductImgPath(id,path);
	 }

	@Override
	public boolean IsProductExists(String productName) {
		return productDao.IsProductExists(productName);
	}
	  
	
}
