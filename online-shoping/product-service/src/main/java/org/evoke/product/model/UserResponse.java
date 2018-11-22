package org.evoke.product.model;

import java.util.List;

public class UserResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<User> getUserLst() {
		return userLst;
	}
	public void setUserLst(List<User> userLst) {
		this.userLst = userLst;
	}
	private List<User> userLst;

	

	
		
	
}
