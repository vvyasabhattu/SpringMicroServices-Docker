package org.evoke.product.service;

import org.evoke.product.dao.ProductDaoImpl;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.model.User;
import org.evoke.product.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
		
	@Autowired
    private ProductDaoImpl productDao;
	
	  
	 @Override
	 public ProductResponseList addProduct(ProductRequest pr,User user) {
		  
		 ProductResponseList baseResponse  = null;
		 if(null != pr  && null != pr.getProduct() ) {
			 
			 pr.getProduct().setCreatedUser(user.getFirstName());
			 pr.getProduct().setUpdatedUser(user.getFirstName());
			 pr.getProduct().setCreatedDate(DateUtil.getDDMMYYDate());
			 pr.getProduct().setUpdatedDate(DateUtil.getDDMMYYDate());
			 pr.getProduct().setIs_deleted("no");
			 baseResponse =  productDao.addProduct(pr.getProduct());
		 }
		   return baseResponse;
		   
	  }
	 
	 
	 @Override
		public ProductResponseList updateProduct(ProductRequest pr,User user) {
			 
			ProductResponseList baseResponse  = null;
			 if(null != pr  && null != pr.getProduct() ) {
				 pr.getProduct().setUpdatedUser(user.getFirstName());
				 pr.getProduct().setUpdatedDate(DateUtil.getDDMMYYDate());
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
