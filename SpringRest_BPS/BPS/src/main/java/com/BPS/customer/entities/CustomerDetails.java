package com.BPS.customer.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="customerdetails", uniqueConstraints=@UniqueConstraint(columnNames="customer_id"))
public class CustomerDetails {
	
	@Id
	@Column(unique = true,name="customer_id")
	private String customerId;
	
	@Basic
	@Column(name="customer_name")
	private String customerName;
	
	@Basic
	@Column(name="vendor_name")
	private String vendorName;
	
	@Basic
	private String address;
	
	@Basic
	@Column(name="contact_number")
	private String contactNumber;
	
	@Basic
	private String email;
	
	@Basic
	@Column(name="id_document_type")
	private String idDocType;
	
	@Basic
	@Column(name="doc_no")
	private String docNo;

	@Basic
	@Column(name="reg_date")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	
	@Basic
	private Double balance;

	@ManyToOne
	private Card card;
	
	@ManyToOne
	private Amount amount;
	
	@ManyToOne
	private Country country;
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCustomerId() {
		return customerId;
	}
	
	//parameterized constructor
	public CustomerDetails(String customerId, String customerName, String vendorName, String address,
			String contactNumber, String email, String idDocType, String docNo, Date regDate, Double balance, Card card,
			Amount amount, Country country) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.vendorName = vendorName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.email = email;
		this.idDocType = idDocType;
		this.docNo = docNo;
		this.regDate = regDate;
		this.balance = balance;
		this.card = card;
		this.amount = amount;
		this.country = country;
	}
	
	//non-parameterized constructor
	public CustomerDetails() {
		super();
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdDocType() {
		return idDocType;
	}

	public void setIdDocType(String idDocType) {
		this.idDocType = idDocType;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
