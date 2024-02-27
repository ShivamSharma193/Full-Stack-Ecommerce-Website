package com.fashion_point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion_point.pojos.User;

//JpaRepository<User, Long> takes entity(pojo) and its primary key type 
//With this declaration, you get a set of standard CRUD (Create, Read, Update, Delete) methods for 
//free, thanks to Spring Data JPA. These methods include operations like save 
//(for creating or updating records), findById (for retrieving a record by its ID), findAll 
//(for retrieving all records), deleteById (for deleting a record by its ID), and more.
public interface UserRepository extends JpaRepository<User, Long> {
	//camelcasing is vvvvvvvvIMP 
	//jpa writes appropriate sql query if it find Email as variable for that pojo
	public User findByEmail(String email);

}
