package com.BPS.admin.service;

import java.util.List;

import com.BPS.admin.entities.AdminDetails;

public interface AdminDetailsService {
	
	List<AdminDetails> findAll();
	public AdminDetails addAdmin(AdminDetails admin);

}
