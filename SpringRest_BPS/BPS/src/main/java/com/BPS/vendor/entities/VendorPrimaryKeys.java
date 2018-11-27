package com.BPS.vendor.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VendorPrimaryKeys implements Serializable{
	
    private String vendorId;
    private String vendorName;
    
    
    
	public VendorPrimaryKeys() {
	}

	public VendorPrimaryKeys(String vendorId, String vendorName) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
	}

	public VendorPrimaryKeys(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
    
    

}
