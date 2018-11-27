package com.BPS.bill.entities;

import java.util.Date;

public class BillSaveHolder{
	
	private String customerId;
	private String vendorType;
	private String vendorName;
	private Long pendingAmount; 
	private Date paymentDate;
	private Long amountToPay;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Long getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(Long pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Long getAmountToPay() {
		return amountToPay;
	}
	public void setAmountToPay(Long amountToPay) {
		this.amountToPay = amountToPay;
	}
	
	
	

}
