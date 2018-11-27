package com.BPS.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.customer.dao.CustomerDetailsDao;
import com.BPS.customer.entities.CustomerDetails;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService{

	@Autowired
	private CustomerDetailsDao dao;
	
	@Override
	@Transactional
	public List<CustomerDetails> findAll() {
		return  dao.findAll();
	}
	
	@Override
	@Transactional
	public CustomerDetails addCustomer(CustomerDetails customer) {
		return dao.save(customer);
	}
	
	@Override
	@Transactional
	public Optional<CustomerDetails> findById(String customerId) {
		return dao.findById(customerId);
	}

	@Override
	@Transactional
	public List<CustomerDetails> findByOrderByCustomerIdDesc() {
		return dao.findByOrderByCustomerIdDesc();
	}

	@Override
	public void deleteById(String customerId) {
		dao.deleteById(customerId);
		
	}

}
