package org.evoke.product.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractTimestampEntity {

   
    @Column(name = "create_date", nullable = false)
    private String createdDate;

   
    @Column(name = "update_date", nullable = false)
    private String updatedDate;
    
    @Column(name = "created_user", nullable = false)
    private String createdUser;
    
    @Column(name = "updated_user", nullable = false)
    private String updatedUser;
    
    public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}
    
    
}