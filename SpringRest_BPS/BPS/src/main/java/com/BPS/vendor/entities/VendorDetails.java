package com.BPS.vendor.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(VendorPrimaryKeys.class)
@Table(name = "vendordetails")
public class VendorDetails{

	@Id
    @Column(name="vendor_id")
    private String vendorId;

    @Id
    @Column(name="vendor_name")
    private String vendorName;

    @Basic
    @Column(name="company_reg_no")
    private String companyRegNo;

    @Basic
    private String address;

    @Basic
    private String email;

    @Basic
    private String contact;

    @Basic
    private String website;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name="cert_issue_date")
    private Date certIssueDate;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name="cert_validity_date")
    private Date certValidityDate;

    @Basic
    @Column(name="emp_count")
    private int empCount;

    @Basic
    @Column(name="cust_count")
    private int custCount;

    @Basic
    @Column(name="year_of_establishment")
    private int yearOfEstablishment;

    @Basic
    private String certificate;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Amount amount;

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

	public String getCompanyRegNo() {
		return companyRegNo;
	}

	public void setCompanyRegNo(String companyRegNo) {
		this.companyRegNo = companyRegNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

    

}