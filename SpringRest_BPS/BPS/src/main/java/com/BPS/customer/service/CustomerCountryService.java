package com.BPS.customer.service;

import java.util.List;

import com.BPS.customer.entities.Country;

public interface CustomerCountryService {
	
	public List<Country> findByCountryName(String country);
	public List<Country> findCountryId(String country, String state);

}
