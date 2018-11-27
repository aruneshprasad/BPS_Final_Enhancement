package com.BPS.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BPS.customer.entities.CustomerDetails;

public interface CustomerDetailsDao extends JpaRepository<CustomerDetails, String> {
	
	public List<CustomerDetails> findByOrderByCustomerIdDesc();
	

}