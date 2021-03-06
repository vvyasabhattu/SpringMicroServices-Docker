package org.evoke.cart.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author mdenning
 */


public class Role extends AbstractTimestampEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@Enumerated(EnumType.STRING)
    private String role;
    
    @JsonIgnore
    @ManyToOne
	@JoinColumn(name="user_id")
	User user;

    public Role() {}

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role(String role) {
        this.role = role;
    }

   
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}
