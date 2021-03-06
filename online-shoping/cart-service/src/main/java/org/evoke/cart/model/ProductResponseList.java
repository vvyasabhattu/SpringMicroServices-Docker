package org.evoke.cart.model;

import java.util.List;

public class ProductResponseList extends BaseResponse {
	
	private List<ProductResponse> productResponse;

	/**
	 * @return the productResponse
	 */
	public List<ProductResponse> getProductResponse() {
		return productResponse;
	}

	/**
	 * @param productResponse the productResponse to set
	 */
	public void setProductResponse(List<ProductResponse> productResponse) {
		this.productResponse = productResponse;
	}
	
	

}
