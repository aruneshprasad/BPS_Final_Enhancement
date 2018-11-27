package com.BPS.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BPS.admin.entities.AdminDetails;

@Repository
public interface AdminDetailsDao extends JpaRepository<AdminDetails, String>{
	
	

}
