package com.BPS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BPS.admin.entities.AdminDetails;
import com.BPS.admin.service.AdminDetailsService;


@RestController
@CrossOrigin
@ComponentScans(value={@ComponentScan("com.BPS.admin.dao"), @ComponentScan("com.BPS.admin.service")})
@RequestMapping(value="/adminservice")
public class AdminController {
	
	@Autowired
	private AdminDetailsService as;
	
	List<AdminDetails> admins = null;
	List<AdminDetails> admin = null;
	
	@GetMapping(value = "/admins")
	public ResponseEntity<?> findAll() {
		admins = as.findAll();
		if (admins.isEmpty()) {
			return new ResponseEntity<String>("No Records available in DB", HttpStatus.OK);
		}

		return new ResponseEntity<List<AdminDetails>>(admins, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/")
	public ResponseEntity<?> updateAdmin(@RequestBody AdminDetails admin) {
		
		
		admin = as.addAdmin(admin);
		
		if(admin==null){
			return new ResponseEntity<AdminDetails>(new AdminDetails(), HttpStatus.OK);
		}

		return new ResponseEntity<AdminDetails>(admin, HttpStatus.OK);
	}

}
