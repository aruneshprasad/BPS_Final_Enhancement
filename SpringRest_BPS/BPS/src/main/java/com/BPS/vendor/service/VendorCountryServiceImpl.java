package com.BPS.vendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.vendor.dao.VendorCountryDao;
import com.BPS.vendor.entities.Country;

@Service
public class VendorCountryServiceImpl implements VendorCountryService{
	
	@Autowired
	private VendorCountryDao cd;

	@Override
	@Transactional
	public List<Country> findCountryId(String country, String state) {
		return cd.findCountryId(country, state);
	}

	@Override
	public List<Country> findByCountryName(String country) {
		return cd.findByCountryName(country);
	}

}
