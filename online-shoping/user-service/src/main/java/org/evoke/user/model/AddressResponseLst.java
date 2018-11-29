package org.evoke.user.model;

import java.util.List;

public class AddressResponseLst extends BaseResponse{

	private static final long serialVersionUID = 1L;
	
	private List<CustomAddressResponse> addressResponseLst;

	/**
	 * @return the addressResponseLst
	 */
	public List<CustomAddressResponse> getAddressResponseLst() {
		return addressResponseLst;
	}

	/**
	 * @param addressResponseLst the addressResponseLst to set
	 */
	public void setAddressResponseLst(List<CustomAddressResponse> addressResponseLst) {
		this.addressResponseLst = addressResponseLst;
	}


}

