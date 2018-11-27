package com.BPS.customer.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="vendor_type"))
public class Amount {

    @Id
    @Column(unique = true, name="vendor_type")
    private String vendorType;

    @Basic
    @Column(precision = 2)
    private Double amount;
  
	public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    //parameterized constructor
    public Amount(String vendorType, Double amount) {
		super();
		this.vendorType = vendorType;
		this.amount = amount;
	}

  //non-parameterized constructor
    public Amount() {
		super();
	}
    
	public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
