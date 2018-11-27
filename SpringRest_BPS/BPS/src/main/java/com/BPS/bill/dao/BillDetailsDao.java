package com.BPS.bill.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BPS.bill.entities.BillDetails;

public interface BillDetailsDao extends JpaRepository<BillDetails, String>{
	
	@Query(value="select * from billdetails b where b.cust_id = :cidParam", nativeQuery=true)
	Optional<List<BillDetails>> findBillByCustomerId(@Param("cidParam") String customerId);
	
	public List<BillDetails> findByOrderByBillIdDesc();

}
