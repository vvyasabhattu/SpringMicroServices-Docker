package org.evoke.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.evoke.product.model.BaseResponse;
import org.evoke.product.model.Category_product;
import org.evoke.product.model.Product;
import org.evoke.product.model.ProductResponse;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.model.User;
import org.evoke.product.model.User_product;
import org.evoke.product.util.DateUtil;
import org.evoke.product.util.ProductMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
	
	
	ProductResponse productResponse = null;
	Product product = null;
	ProductResponseList prl = null;


	public ProductResponseList addProduct(Product product) {

		product.setCreatedUser(product.getUser().getFirstName());
		product.setUpdatedUser(product.getUser().getFirstName());
		product.setCreatedDate(DateUtil.getDDMMYYDate());
		product.setUpdatedDate(DateUtil.getDDMMYYDate());
		
		int id = (int) session.save(product);
		//session.flush();
		//session.evict(product);
		
		product.setProduct_id(id);
		List<Product> productList = new ArrayList<Product>() ;
		productList.add(product);
		
		return MapProductResponse(productList);
		
	}
	
	public ProductResponseList updateProduct(Product product) {

		product.setUpdatedUser(product.getUser().getFirstName());
		product.setUpdatedDate(DateUtil.getDDMMYYDate());
		
	//	product 
		
		session.evict(product);
		session.saveOrUpdate(product);
		session.flush();
		//session.evict(product);
		
		List<Product> productList = new ArrayList<Product>() ;
		productList.add(product);
		
		return MapProductResponse(productList);
	    
	}


	public ProductResponseList getProducts() {

		ProductResponse response =  new ProductResponse();
		
		Criteria c = session.createCriteria(Product.class);// ht.find("from Product");
		List<Product> productList = (List<Product>) c.list();
				

		return MapProductResponse(productList);
		
	}

	public ProductResponseList getProductById(int id) {
		
		product = session.get(Product.class, id);
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		
		return MapProductResponse(productList);

	}

	public List<Product> getProductsByUserId(int id) {
		
		User ud = session.get(User.class, id);
		return null;// ud.getProducts();

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

}
