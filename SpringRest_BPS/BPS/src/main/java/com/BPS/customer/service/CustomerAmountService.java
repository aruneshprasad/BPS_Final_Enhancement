package com.BPS.customer.service;

import java.util.List;

import com.BPS.customer.entities.Amount;

public interface CustomerAmountService {
	
	public List<Amount> findByVendorType(String vendorType);

}
