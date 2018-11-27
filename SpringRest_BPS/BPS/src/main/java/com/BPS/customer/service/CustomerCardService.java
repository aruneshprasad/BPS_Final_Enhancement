package com.BPS.customer.service;

import java.util.List;

import com.BPS.customer.entities.Card;

public interface CustomerCardService {
	
	public List<Card> findByCardNo(String cardNo);
	public Card addCard(Card card);

}
