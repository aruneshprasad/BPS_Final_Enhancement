package com.BPS.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.BPS.customer.entities.Card;

public interface CustomerCardDao extends JpaRepository<Card, String>{
	
	@Query(value="select * from card c where c.card_no = :cNoParam", nativeQuery=true)
	public List<Card> findByCardNo(@Param("cNoParam") String cardNo);

}
