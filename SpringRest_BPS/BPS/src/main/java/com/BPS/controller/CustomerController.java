 package com.BPS.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BPS.customer.entities.Amount;
import com.BPS.customer.entities.Card;
import com.BPS.customer.entities.Country;
import com.BPS.customer.entities.CustomerDetails;
import com.BPS.customer.entities.CustomerSaveHolder;
import com.BPS.customer.entities.CustomerUpdateHolder;
import com.BPS.customer.service.CustomerAmountService;
import com.BPS.customer.service.CustomerCardService;
import com.BPS.customer.service.CustomerCountryService;
import com.BPS.customer.service.CustomerDetailsService;

@RestController
@CrossOrigin
@ComponentScans(value = { @ComponentScan("com.BPS.customer.dao"), @ComponentScan("com.BPS.customer.service") })
@RequestMapping(value = "/customerservice")
public class CustomerController {

	@Autowired
	private CustomerDetailsService cds;
	@Autowired
	private CustomerCountryService cs;
	@Autowired
	private CustomerAmountService as;
	@Autowired
	private CustomerCardService cards;
	
	List<CustomerDetails> customers = null;
	Optional<CustomerDetails> customer = null;
	List<Country> countries = null;
	List<Amount> amounts = null;
	
	@GetMapping(value = "/customers")
	public ResponseEntity<?> findAllCustomers() {
		customers = cds.findAll();
		
		if (customers.isEmpty()) {
			return new ResponseEntity<String>("No Records available in DB.", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<CustomerDetails>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<?> findCustomerById(@PathVariable("customerId") String customerId){
		customer = cds.findById(customerId);
		
		if(!customer.isPresent()) {
			return new ResponseEntity<CustomerDetails>(new CustomerDetails(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Optional<CustomerDetails>> (customer, HttpStatus.OK);
	}
	
	@PostMapping(value = "/registercustomer/")
	public ResponseEntity<?> saveCustomer(@RequestBody CustomerSaveHolder csh) {
		
		CustomerDetails customer = new CustomerDetails();
		
		customer.setCustomerName(csh.getCustomerName());
		customer.setAddress(csh.getAddress());
		customer.setContactNumber(csh.getContactNumber());
		customer.setEmail(csh.getEmail());
		customer.setIdDocType(csh.getIdDocType());
		customer.setDocNo(csh.getDocNo());
		customer.setRegDate(csh.getRegDate());
		customer.setVendorName(csh.getVendorName());
		customer.setBalance(csh.getBalance());
//		File file=new File("C:/Users/724467/Downloads/"+csh.getFile().getName());
//		System.out.println();
//		System.out.println(csh.getFile().getPath());
//		System.out.println(csh.getFile().getAbsolutePath());
//		try {
//			System.out.println(csh.getFile().getCanonicalPath()+" "+csh.getFile().getName());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		JSONParser parser = new JSONParser();
//        try {
//			Object obj = parser.parse(new FileReader(file));
//			 JSONArray json = (JSONArray) obj;
//			 for(int i=0;i<json.size();i++){
//				 JSONObject jo=(JSONObject)json.get(i);
//				 System.out.println(jo.get("id"));
//			 }
//	} 
//		 catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	
		Amount amount = new Amount();
		List<Amount> vendorAmount = as.findByVendorType(csh.getVendorType());
		amount.setVendorType(vendorAmount.get(0).getVendorType());
		amount.setAmount(vendorAmount.get(0).getAmount());
		customer.setAmount(amount);
		
		Country country = new Country();
		List<Country> countryDetails = cs.findCountryId(csh.getCountry(), csh.getState());
		country.setCountryId(countryDetails.get(0).getCountryId());
		country.setCountry(countryDetails.get(0).getCountry());
		country.setState(countryDetails.get(0).getState());
		customer.setCountry(country);
		
		Card card = new Card();
		//List<Card> cardDetails = cards.findByCardNo(csh.getCardNo());
		card.setCardNo(csh.getCardNo());
		card.setCardType(csh.getCardType());
		card.setCardValidity(csh.getCardValidity());
		cards.addCard(card);
		customer.setCard(card);
		
		StringBuilder prefix= new StringBuilder("C");
		List<CustomerDetails> customers = cds.findByOrderByCustomerIdDesc();
		if(!customers.isEmpty()){
			Integer id = new Integer(customers.get(0).getCustomerId().substring(1));
			id++;
			for(int i=id.toString().length();i<=3;i++)
				prefix.append("0");
			prefix.append(id);
		}
		else{
			prefix.append("0001");
		}

		customer.setCustomerId(prefix.toString());
		
		customer = cds.addCustomer(customer);
		
		if(customer==null){
			return new ResponseEntity<String>("Details could not be saved.", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<CustomerDetails>(customer, HttpStatus.OK);
	}
	
	@PutMapping(value = "/updatecustomer/")
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerUpdateHolder cuh) {
		
		CustomerDetails customer = new CustomerDetails();
		
		customer.setCustomerId(cuh.getCustomerId());
		customer.setCustomerName(cuh.getCustomerName());
		customer.setAddress(cuh.getAddress());
		customer.setContactNumber(cuh.getContactNumber());
		customer.setEmail(cuh.getEmail());
		customer.setIdDocType(cuh.getIdDocType());
		customer.setDocNo(cuh.getDocNo());
		customer.setRegDate(cuh.getRegDate());
		customer.setVendorName(cuh.getVendorName());
		customer.setBalance(cuh.getBalance());
	
		Amount amount = new Amount();
		List<Amount> vendorAmount = as.findByVendorType(cuh.getVendorType());
		amount.setVendorType(vendorAmount.get(0).getVendorType());
		amount.setAmount(vendorAmount.get(0).getAmount());
		customer.setAmount(amount);
		
		Country country = new Country();
		List<Country> countryDetails = cs.findCountryId(cuh.getCountry(), cuh.getState());
		country.setCountryId(countryDetails.get(0).getCountryId());
		country.setCountry(countryDetails.get(0).getCountry());
		country.setState(countryDetails.get(0).getState());
		customer.setCountry(country);
		
		Card card = new Card();
		//List<Card> cardDetails = cards.findByCardNo(cuh.getCardNo());
		card.setCardNo(cuh.getCardNo());
		card.setCardType(cuh.getCardType());
		card.setCardValidity(cuh.getCardValidity());
		cards.addCard(card);
		customer.setCard(card);
		
		customer = cds.addCustomer(customer);
		
		if(customer==null){
			return new ResponseEntity<String>("Details could not be saved.", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<CustomerDetails>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{customerId}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") String customerId) {
		cds.deleteById(customerId);
		
		return new ResponseEntity<CustomerDetails>(new CustomerDetails(), HttpStatus.OK);
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
