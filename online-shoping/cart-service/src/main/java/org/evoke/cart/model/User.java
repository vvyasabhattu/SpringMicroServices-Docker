package org.evoke.cart.model;

import java.io.Serializable;
import java.util.List;


public class User extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;

	private String email;

	private String contactNumber;

	private String password;

	private List<Role> roleLst;

	private List<Address> addressLst;

	
	private int product_id;

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddressLst() {
		return addressLst;
	}

	public List<Role> getRoleLst() {
		return roleLst;
	}

	public void setRoleLst(List<Role> roleLst) {
		this.roleLst = roleLst;
	}

	public void setAddressLst(List<Address> addressLst) {
		this.addressLst = addressLst;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", password=" + password + ", roleLst=" + roleLst
				+ ", addressLst=" + addressLst + ", product_id=" + product_id + "]";
	}
	
	
}
