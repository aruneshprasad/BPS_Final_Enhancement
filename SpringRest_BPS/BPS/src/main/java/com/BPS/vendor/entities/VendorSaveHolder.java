package com.BPS.vendor.entities;

import java.util.Date;

public class VendorSaveHolder {
	
    private String vendorName;
    private String companyRegNo;
    private String vendorType;
    private String address;
    private String country;
    private String state;
    private String email;
    private String contact;
    private String website;
    private Date certIssueDate;
    private Date certValidityDate;
    private int empCount;
    private int custCount;
    private int yearOfEstablishment;
    
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getCompanyRegNo() {
		return companyRegNo;
	}
	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Date getCertIssueDate() {
		return certIssueDate;
	}
	public void setCertIssueDate(Date certIssueDate) {
		this.certIssueDate = certIssueDate;
	}
	public Date getCertValidityDate() {
		return certValidityDate;
	}
	public void setCertValidityDate(Date certValidityDate) {
		this.certValidityDate = certValidityDate;
	}
	public int getEmpCount() {
		return empCount;
	}
	public void setEmpCount(int empCount) {
		this.empCount = empCount;
	}
	public int getCustCount() {
		return custCount;
	}
	public void setCustCount(int custCount) {
		this.custCount = custCount;
	}
	public int getYearOfEstablishment() {
		return yearOfEstablishment;
	}
	public void setYearOfEstablishment(int yearOfEstablishment) {
		this.yearOfEstablishment = yearOfEstablishment;
	}
    
}
