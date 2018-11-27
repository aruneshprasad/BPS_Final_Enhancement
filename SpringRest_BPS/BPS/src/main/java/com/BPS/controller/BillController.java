package com.BPS.controller;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

import com.BPS.bill.entities.BillDetails;
import com.BPS.bill.entities.BillSaveHolder;
import com.BPS.bill.entities.BillUpdateHolder;
import com.BPS.bill.entities.UploadFile;
import com.BPS.bill.service.BillDetailsService;
import com.BPS.vendor.entities.VendorDetails;
import com.BPS.vendor.service.VendorDetailsService;

@RestController
@CrossOrigin
@ComponentScans(value={@ComponentScan("com.BPS.bill.dao"), @ComponentScan("com.BPS.bill.service"), @ComponentScan("com.BPS.vendor.dao"), @ComponentScan("com.BPS.vendor.service")})
@RequestMapping(value="/billservice")
public class BillController {
	
	@Autowired
	private BillDetailsService bs;
	@Autowired
	private VendorDetailsService vds;
	
	List<BillDetails> bills = null;
	Optional<List<BillDetails>> bill = null;
	
	@GetMapping(value = "/bills")
	public ResponseEntity<?> findAll() {
		bills = bs.findAll();
		if (bills.isEmpty()) {
			return new ResponseEntity<String>("No Records available in DB", HttpStatus.OK);
		}
		return new ResponseEntity<List<BillDetails>>(bills, HttpStatus.OK);
	}
	
	/*@GetMapping("/getbill/{billId}")
	public ResponseEntity<?> findBillById(@PathVariable("billId") String billId){
		
		bill = bs.findById(billId);
		
		if(!bill.isPresent()) {
			return new ResponseEntity<String>("Bill with given id "+billId+" not found.", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Optional<BillDetails>> (bill, HttpStatus.OK);
	}*/
	
	@GetMapping("/getbilldetails/{customerId}")
	public ResponseEntity<?> findBillByCustomerId(@PathVariable("customerId") String customerId){
		
		bill = bs.findBillByCustomerId(customerId);
		
		/*if(!bill.isPresent()) {
			return new ResponseEntity<Optional<List<BillDetails>>>(new BillDetails(), HttpStatus.OK);
		}*/
		
		return new ResponseEntity<Optional<List<BillDetails>>> (bill, HttpStatus.OK);
	}
	
	@PostMapping(value = "/generate/")
	public ResponseEntity<?> generateBill(@RequestBody BillSaveHolder bsh) {
		
		BillDetails bill = new BillDetails();
		
		bill.setCustId(bsh.getCustomerId());
		bill.setPaymentDate(bsh.getPaymentDate());
		bill.setAmountToPay(bsh.getAmountToPay());
		bill.setDueAmount(bsh.getPendingAmount()-bsh.getAmountToPay());
		
		List<VendorDetails> vendor = vds.findVendorIdByNameType(bsh.getVendorName(), bsh.getVendorType());
		bill.setVendorId(vendor.get(0).getVendorId());
		
		StringBuilder prefix= new StringBuilder("B");
		List<BillDetails> bills = bs.findByOrderByBillIdDesc();
		if(!bills.isEmpty()){
			Integer id = new Integer(bills.get(0).getBillId().substring(1));
			id++;
			for(int i=id.toString().length();i<=3;i++)
				prefix.append("0");
			prefix.append(id);
		}
		else{
			prefix.append("0001");
		}

		bill.setBillId(prefix.toString());;
		
		bill = bs.addBill(bill);
		
		if(bill==null) {
			return new ResponseEntity<String>("Bill not saved", HttpStatus.OK);

		}
		return new ResponseEntity<BillDetails>(bill, HttpStatus.OK);
	}
	
	@PostMapping(value="/upload/")
	public ResponseEntity<?> uploadFile(@RequestBody UploadFile upf){
		File file=new File("C:/Users/724467/Downloads/"+upf.getFile().getName());
		JSONParser parser = new JSONParser();
        try {
			Object obj = parser.parse(new FileReader(file));
			 JSONArray json = (JSONArray) obj;
			 for(int i=0;i<json.size();i++){
				 JSONObject jo=(JSONObject)json.get(i);
				System.out.println(jo);
				 BillDetails bill = new BillDetails();
				 bill.setCustId((String) jo.get("custId"));
					bill.setPaymentDate(new Date());
					bill.setAmountToPay((Long) jo.get("amountToPay"));
					bill.setDueAmount((Long) jo.get("dueAmount"));
					
					bill.setVendorId((String) jo.get("vendorId"));
					
					StringBuilder prefix= new StringBuilder("B");
					List<BillDetails> bills = bs.findByOrderByBillIdDesc();
					if(!bills.isEmpty()){
						Integer id = new Integer(bills.get(0).getBillId().substring(1));
						id++;
						for(int j=id.toString().length();j<=3;j++)
							prefix.append("0");
						prefix.append(id);
					}
					else{
						prefix.append("0001");
					}

					bill.setBillId(prefix.toString());;
					bill = bs.addBill(bill);
			 }
	} 
		 catch (Exception e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<BillDetails>(new BillDetails(), HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/update/")
	public ResponseEntity<?> updateBill(@RequestBody BillUpdateHolder buh) {
		
		BillDetails bill = new BillDetails();
		
		bill.setBillId(buh.getBillId());
		bill.setCustId(buh.getCustomerId());
		bill.setPaymentDate(buh.getPaymentDate());
		bill.setAmountToPay(buh.getAmountToPay());
		bill.setDueAmount(buh.getPendingAmount()-buh.getAmountToPay());
		
		List<VendorDetails> vendor = vds.findVendorIdByNameType(buh.getVendorName(), buh.getVendorType());
		bill.setVendorId(vendor.get(0).getVendorId());
		
		bill = bs.addBill(bill);
		
		if(bill==null) {
			return new ResponseEntity<String>("Bill not saved", HttpStatus.OK);

		}
		return new ResponseEntity<BillDetails>(bill, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{billId}")
	public ResponseEntity<?> deleteBill(@PathVariable("billId") String billId) {
		bs.deleteById(billId);
		
		return new ResponseEntity<BillDetails>(new BillDetails(), HttpStatus.OK);
	}

}
