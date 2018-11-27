package bps.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="billdetails", uniqueConstraints=@UniqueConstraint(columnNames="bill_id"))
public class BillDetails {
	
	@Id
	@Column(unique = true, name="bill_id")
	private String billId;
	
	@Column(name="vendor_id")
	private String vendorId;
	
	@Column(name="cust_id")
	private String custId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="payment_date")
	private Date paymentDate;
	
	@Column(name="due_amount")
	private Long dueAmount;
	
	@Column(name="amount_to_pay")
	private Long amountToPay;

	public Long getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(Long amountToPay) {
		this.amountToPay = amountToPay;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Long dueAmount) {
		this.dueAmount = dueAmount;
	}
	
	
}
