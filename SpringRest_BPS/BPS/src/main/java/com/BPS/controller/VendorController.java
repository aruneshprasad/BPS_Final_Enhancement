package com.BPS.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BPS.vendor.entities.Amount;
import com.BPS.vendor.entities.Country;
import com.BPS.vendor.entities.VendorDetails;
import com.BPS.vendor.entities.VendorSaveHolder;
import com.BPS.vendor.entities.VendorUpdateHolder;
import com.BPS.vendor.service.VendorAmountService;
import com.BPS.vendor.service.VendorCountryService;
import com.BPS.vendor.service.VendorDetailsService;

@RestController
@CrossOrigin
@ComponentScans(value={@ComponentScan("com.BPS.vendor.dao"), @ComponentScan("com.BPS.vendor.service")})
@RequestMapping(value="/vendorservice")
public class VendorController {
	
	@Autowired
	private VendorDetailsService vds;
	@Autowired
	private VendorCountryService cs;
	@Autowired
	private VendorAmountService as;
	
	List<VendorDetails> vendors = null;
	List<Country> countries = null;
	List<Amount> amounts = null;
	List<VendorDetails> vendor = null;
	
	@GetMapping(value = "/vendors")
	public ResponseEntity<?> findAllVendors() {
		vendors = vds.findAll();
		
		if (vendors.isEmpty()) {
			return new ResponseEntity<String>("No Records available in DB.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<VendorDetails>>(vendors, HttpStatus.OK);
	}
	
	@GetMapping("/getvendor/{vendorId}")
	public ResponseEntity<?> findVendorById(@PathVariable("vendorId") String vendorId){
		
		vendor = vds.findVendorById(vendorId);
		
		if(vendor.isEmpty()) {
			return new ResponseEntity<VendorDetails>(new VendorDetails(), HttpStatus.OK);
		}
		
		return new ResponseEntity<VendorDetails> (vendor.get(0), HttpStatus.OK);
	}
	
	@GetMapping("/getvendors/{vendorType}")
	public ResponseEntity<?> findVendorByType(@PathVariable("vendorType") String vendorType){
		
		
		vendors = vds.findVendorByType(vendorType);
		
		if(vendors.isEmpty()) {
			return new ResponseEntity<String>("Vendor with given type "+ vendorType +" not found.", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<VendorDetails>> (vendors, HttpStatus.OK);
	}
	
	@PostMapping(value = "/registervendor/")
	public ResponseEntity<?> saveVendor(@RequestBody VendorSaveHolder vsh) {
		
		vendor = vds.findVendorIdByNameType(vsh.getVendorName(), vsh.getVendorType());
		
		if(!vendor.isEmpty()){
			return new ResponseEntity<VendorDetails>(new VendorDetails(), HttpStatus.OK);
		}
		
		VendorDetails vendor = new VendorDetails();
		
		vendor.setAddress(vsh.getAddress());
		vendor.setCertIssueDate(vsh.getCertIssueDate());
		vendor.setCertValidityDate(vsh.getCertValidityDate());
		vendor.setCompanyRegNo(vsh.getCompanyRegNo());
		vendor.setContact(vsh.getContact());
		vendor.setCustCount(vsh.getCustCount());
		vendor.setEmail(vsh.getEmail());
		vendor.setEmpCount(vsh.getEmpCount());
		vendor.setVendorName(vsh.getVendorName());
		vendor.setWebsite(vsh.getWebsite());
		vendor.setYearOfEstablishment(vsh.getYearOfEstablishment());
		
		Amount amount = new Amount();
		List<Amount> vendorAmount = as.findByVendorType(vsh.getVendorType());
		amount.setVendorType(vendorAmount.get(0).getVendorType());
		amount.setAmount(vendorAmount.get(0).getAmount());
		vendor.setAmount(amount);
		
		Country country = new Country();
		List<Country> countryDetails = cs.findCountryId(vsh.getCountry(), vsh.getState());
		country.setCountryId(countryDetails.get(0).getCountryId());
		country.setCountry(countryDetails.get(0).getCountry());
		country.setState(countryDetails.get(0).getState());
		vendor.setCountry(country);
		
		StringBuilder prefix= new StringBuilder("V");
		List<VendorDetails> vendors = vds.findByOrderByVendorIdDesc();
		if(!vendors.isEmpty()){
			Integer id = new Integer(vendors.get(0).getVendorId().substring(1));
			id++;
			for(int i=id.toString().length();i<=3;i++)
				prefix.append("0");
			prefix.append(id);
		}
		else{
			prefix.append("0001");
		}

		vendor.setVendorId(prefix.toString());
		
		vendor = vds.addVendor(vendor);
		
		/*if(vendor==null){
			return new ResponseEntity<String>("Details could not be saved.", HttpStatus.EXPECTATION_FAILED);
		}*/
		return new ResponseEntity<VendorDetails>(vendor, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updatevendor/")
	public ResponseEntity<?> updateVendor(@RequestBody VendorUpdateHolder vuh) {
		
		System.out.println();
		
		VendorDetails vendor = new VendorDetails();
		
		vendor.setVendorId(vuh.getVendorId());
		vendor.setAddress(vuh.getAddress());
		vendor.setCertIssueDate(vuh.getCertIssueDate());
		vendor.setCertValidityDate(vuh.getCertValidityDate());
		vendor.setCompanyRegNo(vuh.getCompanyRegNo());
		vendor.setContact(vuh.getContact());
		vendor.setCustCount(vuh.getCustCount());
		vendor.setEmail(vuh.getEmail());
		vendor.setEmpCount(vuh.getEmpCount());
		vendor.setVendorName(vuh.getVendorName());
		vendor.setWebsite(vuh.getWebsite());
		vendor.setYearOfEstablishment(vuh.getYearOfEstablishment());
		
		Amount amount = new Amount();
		List<Amount> vendorAmount = as.findByVendorType(vuh.getVendorType());
		amount.setVendorType(vuh.getVendorType());
		amount.setAmount(vendorAmount.get(0).getAmount());
		vendor.setAmount(amount);
		
		Country country = new Country();
		List<Country> countryDetails = cs.findCountryId(vuh.getCountry(), vuh.getState());
		country.setCountryId(countryDetails.get(0).getCountryId());
		country.setCountry(countryDetails.get(0).getCountry());
		country.setState(countryDetails.get(0).getState());
		vendor.setCountry(country);
		
		vendor = vds.addVendor(vendor);
		
		if(vendor==null){
			return new ResponseEntity<String>("Details could not be saved.", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<VendorDetails>(vendor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getcountry/{country}")
	public ResponseEntity<?> findCountryByCountryName(@PathVariable("country") String country) {
		countries = cs.findByCountryName(country);
		if (countries.isEmpty()) {
			return new ResponseEntity<Object>("Country with given name "+country +" not found.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getamount/{vendorType}")
	public ResponseEntity<?> findAmountByVendorType(@PathVariable("vendorType") String vendorType) {
		amounts = as.findByVendorType(vendorType);
		if (amounts.isEmpty()) {
			return new ResponseEntity<Object>("Amount for given vendor type"+ vendorType+"not available.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Amount>>(amounts, HttpStatus.OK);
	}

}
