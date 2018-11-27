package com.BPS.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BPS.admin.dao.AdminDetailsDao;
import com.BPS.admin.entities.AdminDetails;

@Service
public class AdminDetailsServiceImpl implements AdminDetailsService{
	
	@Autowired
	private AdminDetailsDao dao;

	@Override
	public List<AdminDetails> findAll() {
		return dao.findAll();
	}

	@Override
	public AdminDetails addAdmin(AdminDetails admin) {
		return dao.save(admin);
	}

}
