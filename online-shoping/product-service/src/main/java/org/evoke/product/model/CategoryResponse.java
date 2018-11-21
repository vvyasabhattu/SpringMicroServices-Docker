package org.evoke.product.model;

import java.util.List;

public class CategoryResponse extends BaseResponse {
	
	private List<Category> category;

	/**
	 * @return the category
	 */
	public List<Category> getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(List<Category> category) {
		this.category = category;
	}


}
