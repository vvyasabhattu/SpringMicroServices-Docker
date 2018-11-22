package org.evoke.user.persistence.dao;

import org.evoke.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	
	@Query(value = "SELECT password FROM user u WHERE u.email= ?1" , 
			nativeQuery = true)
	String getUserPassword(String email);
	
	@Query(value = "SELECT u.* FROM user u WHERE u.email = ?1",
			nativeQuery = true)
	User getUser(String email);

}
