package org.evoke.product.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.evoke.product.error.ErrorCode;
import org.evoke.product.error.ErrorDescription;
import org.evoke.product.error.ErrorType;
import org.evoke.product.model.LoginRequest;
import org.evoke.product.model.ProductRequest;
import org.evoke.product.model.ProductResponseList;
import org.evoke.product.model.Role;
import org.evoke.product.model.User;
import org.evoke.product.model.UserResponse;
import org.evoke.product.service.ProductServiceImpl;
import org.evoke.product.util.ProductMapper;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductServiceImpl ps;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	private UserService userService;
	
	@Autowired
    private ServletContext servletContext;
	
	String sep = File.separator;
	boolean roleCheck = false;


	@PostMapping("/add")
	public ProductResponseList add(@RequestBody ProductRequest pRequest) {
		
		ProductResponseList response = new ProductResponseList();
		boolean roleCheck = false;
		
		try {
			
			if (IsProductExists(pRequest.getProduct().getProduct_name())) {
				response.setErrorCode(ErrorCode.PRODUCT_ALREADY_EXISTS);
				response.setErrorDesc(ErrorDescription.PRODUCT_ALREADY_EXISTS);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
			
			return checkUserRole(pRequest, "insert");
			
			
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

	}
	
	
	@PostMapping("/uploadImg/{product_id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public ProductResponseList addProductImg(@FormDataParam("file") MultipartFile file,@PathVariable("product_id") int product_id,HttpServletRequest req) {
		
		ProductResponseList response = new ProductResponseList();
		  String UPLOADED_FOLDER = null;
		
		 UPLOADED_FOLDER = req.getServletContext().getRealPath("/")+"images"+sep;
		 System.out.println(UPLOADED_FOLDER);
		  File theDir = new File(UPLOADED_FOLDER);
		  File destFile = null;
		  File srcFile = null;
		  FileOutputStream out = null;

		// if the directory does not exist, create it
		   if (!theDir.exists()) {
		        theDir.mkdir();
		   }
		   
		  try {
	        	if(file!=null) {
		            // Get the file and save it .
		        	destFile = new File(UPLOADED_FOLDER+ product_id +"_"+ file.getOriginalFilename());
		        	srcFile = new File(file.getOriginalFilename());
		        	file.transferTo(srcFile);
		        	
		        	FileUtils.copyFile(srcFile, destFile);
	        	}
		  } catch (IOException e) {
				response.setErrorCode(ErrorCode.IMG_SAVE_ERROR);
				response.setErrorDesc(ErrorDescription.IMG_SAVE_ERROR);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				logger.error(e.getMessage());
				return response;	        
			}
	        
		  return ps.updateProductImgPath(product_id,"images"+"/"+product_id + "_"+file.getOriginalFilename());
	}
	

	@PutMapping("update")
	public ProductResponseList update(@RequestBody ProductRequest pRequest) {

		ProductResponseList response = new ProductResponseList();
		
		try {
			
			if (IsProductExists(pRequest.getProduct().getProduct_name())) {
				response.setErrorCode(ErrorCode.PRODUCT_ALREADY_EXISTS);
				response.setErrorDesc(ErrorDescription.PRODUCT_ALREADY_EXISTS);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
			
			return checkUserRole(pRequest, "update");
			
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

		
	}
	
	@PutMapping("/delete")
	public @ResponseBody ProductResponseList delete(@RequestBody ProductRequest pRequest){
		
		ProductResponseList response = new ProductResponseList();
		
		try {
				return checkUserRole(pRequest, "delete");
			
		} catch (Exception e) {
			response.setErrorCode(ErrorCode.PRODUCT_NOT_VALID);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

	}
	

	@GetMapping("/all")
	public @ResponseBody ProductResponseList getProducts() {
		
		ProductResponseList response = new ProductResponseList();

		try {
			return ps.getProducts();
			}
			catch(Exception e) {
				response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
				response.setErrorDesc(e.getMessage());
				System.out.println(e.getStackTrace());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
	}

	@GetMapping("{product_id}")
	public @ResponseBody ProductResponseList getProductById(@PathVariable("product_id") int product_id) {
		
		ProductResponseList response = new ProductResponseList();
		
		try {
		   return ps.getProductById(product_id);
		}
		catch(Exception e) {
			response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}
	}

	@GetMapping("byUser/{user_id}")
	public @ResponseBody ProductResponseList getProductsByUserId(@PathVariable("user_id") int user_id) {
		    
		ProductResponseList response = new ProductResponseList();

		try {
			return ps.getProductsByUserId(user_id);
			}
			catch(Exception e) {
				response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
	}
	
	@GetMapping("byCategory/{category_id}")
	public @ResponseBody ProductResponseList getProductsByCategoryId(@PathVariable("category_id") int category_id) {
		    
		ProductResponseList response = new ProductResponseList();

		try {
			return ps.getProductsByCategoryId(category_id);
			}
			catch(Exception e) {
				response.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR);
				response.setErrorDesc(e.getMessage());
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				return response;
			}
	}
	
	
	public boolean IsProductExists(String productName) {
		
		return ps.IsProductExists(productName);
		
	}
	
	
	public ProductResponseList checkUserRole(ProductRequest pRequest,String operation) {
		
		UserResponse userResponse= null;
		ProductResponseList response = new ProductResponseList();
		boolean roleCheck = false;
		User user = null;
		
		if(pRequest.getProduct().getUser_id()!=0) {
			user=new User();
			user.setId(pRequest.getProduct().getUser_id());	
		}else {
			response.setErrorCode(ErrorCode.USER_ID_NOT_FOUND_IN_REQUEST);
			response.setErrorDesc(ErrorDescription.USER_ID_NOT_FOUND_IN_REQUEST);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUser(user);
		userResponse = userService.getUser(loginRequest);
		
		if (null != userResponse && null != userResponse.getUserLst() && userResponse.getUserLst().size()>0) {

			List<Role> roleLst = userResponse.getUserLst().get(0).getRoleLst();
			
			for(int i=0;i<roleLst.size();i++) {
				System.out.println("role: "+roleLst.get(i).getRole());
				if(roleLst.get(i).getRole().equalsIgnoreCase("Seller"))
					roleCheck = true;
			}
			
			if(roleCheck==true) {
				
				user = userResponse.getUserLst().get(0);
				pRequest.getProduct().setUser_id(userResponse.getUserLst().get(0).getId());
				
				if(operation.equals("insert"))
				 response = ps.addProduct(pRequest,user);
				else if (operation.equals("update"))
					response=ps.updateProduct(pRequest,user);
				else if(operation.equals("delete"))
					response = ps.deleteProduct(pRequest);
					
				return response;
			}
			else {
				response.setErrorCode(ErrorCode.INVALID_USER);
				response.setErrorDesc(ErrorDescription.INVALID_USER);
				response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
				return response;
			}
			
		}
		
		else {
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_PRACTICE_ERROR);
			return response;
		}
		
	}	
	

}
