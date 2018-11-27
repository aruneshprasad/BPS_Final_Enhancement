package com.BPS.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.customer.dao.CustomerAmountDao;
import com.BPS.customer.entities.Amount;

@Service
public class CustomerAmountServiceImpl implements CustomerAmountService{
	
	@Autowired
	private CustomerAmountDao ad;

	@Override
	@Transactional
	public List<Amount> findByVendorType(String vendorType) {
		return ad.findByVendorType(vendorType);
	}

}
