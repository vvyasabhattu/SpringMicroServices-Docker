package org.evoke.product.model;

import java.util.List;


public class ProductResponse extends BaseResponse {
	

	private List<Product> productLst;

	/**
	 * @return the productLst
	 */
	public List<Product> getProductLst() {
		return productLst;
	}

	/**
	 * @param productLst the productLst to set
	 */
	public void setProductLst(List<Product> productLst) {
		this.productLst = productLst;
	}

	
}
