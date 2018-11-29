package org.evoke.user.model;

import java.util.List;

public class AddressResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Address> addressLst;


	public List<Address> getAddressLst() {
		return addressLst;
	}


	public void setAddressLst(List<Address> addressLst) {
		this.addressLst = addressLst;
	}
	
	
} 
