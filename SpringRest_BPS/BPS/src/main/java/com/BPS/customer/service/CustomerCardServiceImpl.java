package com.BPS.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BPS.customer.dao.CustomerCardDao;
import com.BPS.customer.entities.Card;

@Service
public class CustomerCardServiceImpl implements CustomerCardService{
	
	@Autowired
	private CustomerCardDao dao;

	@Override
	@Transactional
	public List<Card> findByCardNo(String cardNo) {
		return dao.findByCardNo(cardNo);
	}

	@Override
	public Card addCard(Card card) {
		return dao.save(card);
	}

}
