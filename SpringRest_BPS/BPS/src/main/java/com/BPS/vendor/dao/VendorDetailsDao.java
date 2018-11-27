package com.BPS.vendor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BPS.vendor.entities.VendorDetails;
import com.BPS.vendor.entities.VendorPrimaryKeys;

public interface VendorDetailsDao extends JpaRepository<VendorDetails, VendorPrimaryKeys>{
	
	public List<VendorDetails> findByOrderByVendorIdDesc();
	
	@Query(value="select * from vendordetails v where v.amount_vendor_type = :vTypeParam", nativeQuery=true)
	List<VendorDetails> findVendorByType(@Param("vTypeParam") String vendorType);
	
	@Query(value="select * from vendordetails v where v.vendor_id = :vIdParam", nativeQuery=true)
	List<VendorDetails> findVendorById(@Param("vIdParam") String vendorId);
	
	@Query(value="select * from vendordetails v where v.vendor_name = :vNameParam and v.amount_vendor_type = :vTypeParam", nativeQuery=true)
	List<VendorDetails> findVendorIdByNameType(@Param("vNameParam") String vendorName, @Param("vTypeParam") String vendorType);

}
