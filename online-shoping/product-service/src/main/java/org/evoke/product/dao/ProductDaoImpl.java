package org.evoke.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.evoke.product.error.ErrorCode;
import org.evoke.product.error.ErrorDescription;
import org.evoke.product.error.ErrorType;
import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.util.DateUtil;
import org.evoke.product.util.ProductMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private Session session;
	
	@Autowired
	ProductMapper productMapper;
	
	ProductResponseList productResponseList = null;
	ProductResponse productResponse = null;
	Product product = null;
	ProductResponseList prl = null;
	Product product_db = null;
	int cnt = 0;

    @Override
	public ProductResponseList addProduct(Product product) {
    	
    	ProductResponseList response = new ProductResponseList();
		
		int id = (int) session.save(product);
		//session.flush();
		//session.evict(product);
		
		try {
		//product.setProduct_id(id);
		List<Product> productList = new ArrayList<Product>() ;
		productList.add(product);
		return MapProductResponse(productList);
		}catch(Exception e) {
			response.setErrorCode(ErrorCode.PRODUCT_RESPONSE_MAPPING_ERROR);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}
		
	}
	
    @Override
	public ProductResponseList updateProduct(Product product) {

		 product_db = session.get(Product.class,product.getProduct_id());
		
		 if(product_db==null) {
			 productResponseList = new ProductResponseList();
			  productResponseList.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND);
			  productResponseList.setErrorDesc(ErrorDescription.PRODUCT_NOT_FOUND);
			  productResponseList.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
				return productResponseList;
		 }
		 else {
			 if(product.getCategory()!=null)
		        product_db.setCategory(product.getCategory());
			 if(product.getBrand()!=null)
		       product_db.setBrand(product.getBrand());
			 if(product.getDescription()!=null)
		        product_db.setDescription(product.getDescription());
			 if(product.getPrice()!=0.0)
				 product_db.setPrice(product.getPrice());
			 if(product.getProduct_name()!=null)
				 product_db.setProduct_name(product.getProduct_name());
			 if(product.getIs_deleted()!=null)
				 product_db.setIs_deleted(product.getIs_deleted());
			 if(product.getQty()!=0)
				 product_db.setQty(product.getQty());
		
				session.clear();
				session.update(product_db);
				session.flush();
				
				List<Product> productList = new ArrayList<Product>() ;
				productList.add(product_db);
				
				return MapProductResponse(productList);
		 }
	    
	}

    @Override
	public ProductResponseList deleteProduct(Product product) {
		
			 product_db = session.get(Product.class,product.getProduct_id());
			
			 if(product_db== null) {
				 productResponseList = new ProductResponseList();
				  productResponseList.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND);
				  productResponseList.setErrorDesc(ErrorDescription.PRODUCT_NOT_FOUND);
				  productResponseList.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
					return productResponseList;
			 }else {
				 product_db.setIs_deleted("yes");
				 session.clear();
				 session.update(product_db);
				 session.flush();
				 
				 List<Product> productList = new ArrayList<Product>() ;
					productList.add(product_db);
					
					return MapProductResponse(productList);
			 }
	}

    @Override
	public ProductResponseList getProducts() {

		ProductResponse response =  new ProductResponse();
		
		Query query = session.createQuery("from Product where is_deleted='no'");
		List<Product> productList = (List<Product>) query.list();
				

		return MapProductResponse(productList);
		
	}

    @Override
	public ProductResponseList getProductById(int id) {
		
		product = session.get(Product.class, id);
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		
		return MapProductResponse(productList);

	}

    @Override
	public ProductResponseList getProductsByUserId(int id) {
		
		 Query query = session.createQuery("from Product where User_id=:id and is_deleted='no'");  //get(User.class, id);
		 query.setInteger("id", id);
    	 List<Product> productList = query.list() ;
    	 return MapProductResponse(productList);

	}
	
    @Override
	public ProductResponseList getProductsByCategoryId(int id) {
		
    	 Query query = session.createQuery("from Product where category_id=:id and is_deleted='no'");  //get(User.class, id);
		 query.setInteger("id", id);
    	 List<Product> productList = query.list() ;
    	 return MapProductResponse(productList);

	}
	
	
	public ProductResponseList MapProductResponse(List<Product> productList) {
		List<ProductResponse> productResList = new ArrayList<ProductResponse>();
		if(!CollectionUtils.isEmpty(productList)) {
			productMapper.map(productList, productResList);
		}
	 
		ProductResponseList prl =new ProductResponseList();
		prl.setProductResponse(productResList);
	    return prl;
	}
	
	public ProductResponseList updateProductImgPath(int id,String path) {
		
		ProductResponseList response = new ProductResponseList();
		
		product_db = session.get(Product.class, id);
		
		if(product_db!=null) {
			product_db.setImg_path(path);
			
			try {
			session.clear();
			session.update(product_db);
			session.flush();
			}catch(Exception e) {
				response.setErrorCode(ErrorCode.DB_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
			
			try {
			List<Product> productList = new ArrayList<Product>();
			productList.add(product_db);
			return MapProductResponse(productList);
			}catch(Exception e) {
				response.setErrorCode(ErrorCode.PRODUCT_RESPONSE_MAPPING_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
		}
		
		else {
			response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.PRODUCT_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;	
		}
			
		
	}
	
	public boolean IsProductExists(String productName) {
		
		List<Product> productList =  session.createCriteria(Product.class).list();

		for(cnt=0;cnt<productList.size();cnt++) {
			
			System.out.println(productList.get(cnt).getProduct_name());
			if(productList.get(cnt).getProduct_name().equalsIgnoreCase(productName))
				return true;
		}
		
		return false;
		
	}

}
