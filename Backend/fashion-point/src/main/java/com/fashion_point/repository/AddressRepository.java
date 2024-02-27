package com.fashion_point.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	@Query("SELECT a from Address a WHERE a.user.id=:userId")
	public Address userAddress(@Param("userId")String userId) throws UserException;

}
