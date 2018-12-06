package org.evoke.cart.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "user")
public class User extends AbstractTimestampEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@NotBlank(message = "Please enter first name!")
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "Please enter last name!")
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "Please enter email address!")
	@Email
	@Column(unique = true)
	private String email;

	@NotBlank(message = "Please enter contact number!")
	@Column(name = "contact_number")
	@Size(min = 10, max = 10)
	// @Pattern(regexp="(^$|[0-9]{10})")
	private String contactNumber;

	// @NotBlank(message = "Please enter password!")
	@Column(updatable = false)
	private String password;

	@Size(min = 1, max = 8)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Role> roleLst;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addressLst;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Product> productLst;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public List<Product> getProductLst() {
		return productLst;
	}

	public void setProductLst(List<Product> productLst) {
		this.productLst = productLst;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", password=" + password + ", roleLst=" + roleLst
				+ ", addressLst=" + addressLst + ", productLst=" + productLst + "]";
	}

}
