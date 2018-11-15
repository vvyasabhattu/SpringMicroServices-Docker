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
import org.evoke.product.model.User;
import org.evoke.product.model.User_product;
import org.evoke.product.util.DateUtil;
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
	
	
	ProductResponse productResponse = null;
	Product product = null;


	public ProductResponse addProduct(Product product) {

		product.setCreatedUser(product.getUser().getFirstName());
		product.setUpdatedUser(product.getUser().getFirstName());
		product.setCreatedDate(DateUtil.getDDMMYYDate());
		product.setUpdatedDate(DateUtil.getDDMMYYDate());
		
		session.saveOrUpdate(product);
		//session.flush();
		//session.evict(product);
		
		 productResponse = new ProductResponse();
		List<Product> products = new ArrayList<Product>();
		products.add(product);
		productResponse.setProductLst(products);
		return productResponse;
	}

	public ProductResponse getProducts() {

		ProductResponse response =  new ProductResponse();
		
		Criteria c = session.createCriteria(Product.class);// ht.find("from Product");
		List<Product> productList = (List<Product>) c.list();
				

		 productResponse = new ProductResponse();
			productResponse.setProductLst(productList);
			return productResponse;
		
	}

	public ProductResponse getProductById(int id) {
		
		product = session.get(Product.class, id);
		
		 productResponse = new ProductResponse();
			List<Product> products = new ArrayList<Product>();
			products.add(product);
			productResponse.setProductLst(products);
			return productResponse;

	}

	public List<Product> getProductsByUserId(int id) {
		
		User ud = session.get(User.class, id);
		return null;// ud.getProducts();

	}

}
