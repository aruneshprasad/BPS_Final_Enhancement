package com.BPS.vendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.vendor.dao.VendorAmountDao;
import com.BPS.vendor.entities.Amount;

@Service
public class VendorAmountServiceImpl implements VendorAmountService{
	
	@Autowired
	private VendorAmountDao ad;

	@Override
	@Transactional
	public List<Amount> findByVendorType(String vendorType) {
		return ad.findByVendorType(vendorType);
	}

}
