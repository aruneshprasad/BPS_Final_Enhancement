package com.BPS.vendor.service;

import java.util.List;

import com.BPS.vendor.entities.VendorDetails;

public interface VendorDetailsService {
	
	public List<VendorDetails> findAll();
	public VendorDetails addVendor(VendorDetails vendor);
	public List<VendorDetails> findVendorByType(String vendorType);
	public List<VendorDetails> findVendorById(String vendorId);
	public List<VendorDetails> findVendorIdByNameType(String vendorName, String vendorType);

	
	public List<VendorDetails> findByOrderByVendorIdDesc();
}
