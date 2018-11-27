package com.BPS.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.customer.dao.CustomerCountryDao;
import com.BPS.customer.entities.Country;

@Service
public class CustomerCountryServiceImpl implements CustomerCountryService{
	
	@Autowired
	private CustomerCountryDao cd;

	@Override
	@Transactional
	public List<Country> findCountryId(String country, String state) {
		return cd.findCountryId(country, state);
	}

	@Override
	@Transactional
	public List<Country> findByCountryName(String country) {
		return cd.findByCountryName(country);
	}

}
