package com.BPS.bill.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.bill.dao.BillDetailsDao;
import com.BPS.bill.entities.BillDetails;

@Service
public class BillDetailsServiceImpl implements BillDetailsService{
	
	@Autowired
	private BillDetailsDao dao;

	@Override
	@Transactional
	public List<BillDetails> findAll() {
		return dao.findAll();
	}

	@Override
	@Transactional
	public BillDetails addBill(BillDetails bill) {
		return dao.save(bill);
	}

	@Override
	@Transactional
	public List<BillDetails> findByOrderByBillIdDesc() {
		return dao.findByOrderByBillIdDesc();
	}

	@Override
	@Transactional
	public Optional<BillDetails> findById(String billId) {
		return dao.findById(billId);
	}

	@Override
	@Transactional
	public Optional<List<BillDetails>> findBillByCustomerId(String customerId) {
		return dao.findBillByCustomerId(customerId);
	}

	@Override
	public void deleteById(String billId) {
		dao.deleteById(billId);
		
	}

}
