package com.BPS.bill.service;

import java.util.List;
import java.util.Optional;

import com.BPS.bill.entities.BillDetails;

public interface BillDetailsService {
	
	List<BillDetails> findAll();
	public BillDetails addBill(BillDetails bill);
	public Optional<BillDetails> findById(String billId);
	public Optional<List<BillDetails>> findBillByCustomerId(String customerId);
	public void deleteById(String billId);
	
	public List<BillDetails> findByOrderByBillIdDesc();

}
