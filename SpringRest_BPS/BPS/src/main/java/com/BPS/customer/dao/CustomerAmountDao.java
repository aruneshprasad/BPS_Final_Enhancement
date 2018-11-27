package com.BPS.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BPS.customer.entities.Amount;

public interface CustomerAmountDao extends JpaRepository<Amount, String>{
	
	@Query(value="select * from amount a where a.vendor_type = :vtParam", nativeQuery=true)
	List<Amount> findByVendorType(@Param("vtParam") String vendorType);

}
