package org.evoke.product.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.evoke.product.dao.ProductDao;
import org.evoke.product.error.ErrorCode;
import org.evoke.product.error.ErrorDescription;
import org.evoke.product.error.ErrorType;
import org.evoke.product.model.LoginRequest;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.model.User;
import org.evoke.product.model.UserResponse;
import org.evoke.product.service.ProductServiceImpl;
import org.evoke.product.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/product")
@RequestScope
public class ProductController {

	@Autowired
	ProductServiceImpl ps;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	private UserService userService;
	
	@Autowired
    private ServletContext servletContext;
	
	ProductResponseList response = null ;
	UserResponse userResponse = null;
	String sep = File.separator;

	@GetMapping("/check")
	public void getCheck() throws RestClientException, IOException {

		try {
			String str = userService.getCheck();
			System.out.println(str);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@GetMapping("/loginUser")
	public UserResponse getLoginUser() throws RestClientException, IOException {

		UserResponse response = null;
		try {

			User user = new User();
			user.setEmail("string@email.com");
			user.setPassword("password");
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setUser(user);
			response = userService.loginUser(loginRequest);

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response;
	}

	@PostMapping
	public ProductResponseList add(@RequestBody ProductRequest pRequest) {
		
		try {
			
			if (IsProductExists(pRequest.getProduct().getProduct_name())) {
				response = new ProductResponseList();
				response.setErrorCode(ErrorCode.PRODUCT_ALREADY_EXISTS);
				response.setErrorDesc(ErrorDescription.PRODUCT_ALREADY_EXISTS);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

				return response;
			}
			
			
			User user =pRequest.getProduct().getUser();			
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setUser(user);
			userResponse = userService.getUser(loginRequest);
			
			if (null != userResponse && null != userResponse.getUserLst() && userResponse.getUserLst().size()>0) {
				
				
				//if(userResponse.getUserLst().get(0).getRoleLst().)
				pRequest.getProduct().setUser(userResponse.getUserLst().get(0));
				response = ps.addProduct(pRequest);
			}
			
			else {
				response = new ProductResponseList();
				response.setErrorCode(ErrorCode.USER_NOT_FOUND);
				response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
				response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
				return response;
			}
			
			
		} catch (Exception e) {
			response = new ProductResponseList();
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

		return response;
	}
	
	
	@PostMapping("/addProductImg/{product_id}")
	public ProductResponseList addProductImg(@RequestBody MultipartFile img,@PathVariable("product_id") int product_id) {

		String UPLOADED_FOLDER =  servletContext.getContextPath();
		
		//  String UPLOADED_FOLDER = servletContext.getRealPath(sep)+"images"+sep;
		  
		/*  File theDir = new File(UPLOADED_FOLDER);
		  File file = null;

		// if the directory does not exist, create it
		   if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;
		        theDir.mkdir();
		   }*/
		   
		  try {
	        	if(img!=null) {
		            // Get the file and save it somewhere
		        	//file = new File(UPLOADED_FOLDER+ product_id + "_"+ img.getOriginalFilename());
		        	
		        	//if(!file.exists()) {
		            byte[] bytes = img.getBytes();
		            Path path = Paths.get(UPLOADED_FOLDER + product_id + "_"+ img.getOriginalFilename());
		            Files.write(path, bytes);
		        	//}
		        	
	        	}
	        	
	        	return ps.updateProductImgPath(product_id,product_id + "_"+ img.getOriginalFilename());
	        	
	        } catch (IOException e) {
	        	response = new ProductResponseList();
				response.setErrorCode(100);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;	        }
		
	}

	@PutMapping
	public ProductResponseList update(@RequestBody ProductRequest pRequest) {

		UserResponse userResponse = null;
		
		try {
			
			if (IsProductExists(pRequest.getProduct().getProduct_name())) {
				response = new ProductResponseList();
				response.setErrorCode(ErrorCode.PRODUCT_ALREADY_EXISTS);
				response.setErrorDesc(ErrorDescription.PRODUCT_ALREADY_EXISTS);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
			
			User user =pRequest.getProduct().getUser();			
			LoginRequest loginRequest = new LoginRequest();
			loginRequest.setUser(user);
			userResponse = userService.getUser(loginRequest);
			
			if (null != userResponse && null != userResponse.getUserLst() && userResponse.getUserLst().size()>0) {

				pRequest.getProduct().setUser(userResponse.getUserLst().get(0));;
				response = ps.updateProduct(pRequest);
			}
			
			else {
				response = new ProductResponseList();
				response.setErrorCode(ErrorCode.USER_NOT_FOUND);
				response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
				response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
				return response;
			}
			
		} catch (Exception e) {
			response = new ProductResponseList();
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

		return response;
	}
	
	@DeleteMapping
	public @ResponseBody ProductResponseList delete(@RequestBody ProductRequest pRequest){
		
		try {
				response = ps.deleteProduct(pRequest);
			
		} catch (Exception e) {
			response = new ProductResponseList();
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

		return response;
		
	}
	

	@GetMapping("/all")
	public @ResponseBody ProductResponseList getProducts() {
		try {
			return ps.getProducts();
			}
			catch(Exception e) {
				response = new ProductResponseList();
				response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
	}

	@GetMapping("{product_id}")
	public @ResponseBody ProductResponseList getProductById(@PathVariable("product_id") int product_id) {
		try {
		   return ps.getProductById(product_id);
		}
		catch(Exception e) {
			response = new ProductResponseList();
			response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}
	}

/*	@GetMapping("byUser/{user_id}")
	public @ResponseBody List<Product> getProductsByUserId(@PathVariable("user_id") int user_id) {

		List<Product> pList = (List<Product>) ps.getProductsByUserId(user_id);

		return pList;
	}*/
	
	@GetMapping("byCategory/{category_id}")
	public @ResponseBody ProductResponseList getProductsByCategoryId(@PathVariable("category_id") int category_id) {
		    try {
			return ps.getProductsByCategoryId(category_id);
			}
			catch(Exception e) {
				response = new ProductResponseList();
				response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
	}
	
	
	public boolean IsProductExists(String productName) {
		
		return ps.IsProductExists(productName);
		
	}
	
	

}
