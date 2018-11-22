package org.evoke.product.model;

/**
 * @author mdenning
 */
public enum RoleEnum {
    
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    SELLER("Seller");
   

    RoleEnum(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return this.toString();
    }
}
