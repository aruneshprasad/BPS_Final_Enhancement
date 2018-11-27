package com.BPS.customer.service;

import java.util.List;
import java.util.Optional;

import com.BPS.customer.entities.CustomerDetails;

public interface CustomerDetailsService {
	
	public List<CustomerDetails> findAll();
	public CustomerDetails addCustomer(CustomerDetails customer);
	public Optional<CustomerDetails> findById(String CustomerId);
	public List<CustomerDetails> findByOrderByCustomerIdDesc();
	public void deleteById(String customerId);

}
