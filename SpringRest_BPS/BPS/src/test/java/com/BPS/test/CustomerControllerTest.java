package com.BPS.test;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.BPS.controller.CustomerController;
import com.BPS.customer.entities.Amount;
import com.BPS.customer.entities.Card;
import com.BPS.customer.entities.Country;
import com.BPS.customer.entities.CustomerDetails;
import com.BPS.customer.service.CustomerAmountService;
import com.BPS.customer.service.CustomerCardService;
import com.BPS.customer.service.CustomerCountryService;
import com.BPS.customer.service.CustomerDetailsService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class, secure = false)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerDetailsService cds;

	@MockBean
	private CustomerCountryService cs;

	@MockBean
	private CustomerAmountService as;

	@MockBean
	private CustomerCardService cards;

	Date d = new Date(2019,3,23);
	LocalDate ld = LocalDate.of(2019, 2, 22);
	Calendar cal = Calendar.getInstance();
	

	
	String dateStr;
	{
		cal.setTime(d);
		cal.set(Calendar.MINUTE, 24);
		cal.set(cal.HOUR_OF_DAY, 5);
		cal.set(Calendar.SECOND, 44);
		cal.set(Calendar.MILLISECOND, 524);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+0000'");
		dateStr=sdf.format(cal.getTime());
		System.out.println("sjahs"+dateStr);
//		dateStr=null;
		cal.add(cal.HOUR_OF_DAY, 5);
		cal.add(cal.MINUTE, 30);
	}
	
	String customerId = "C0001";

	Card card1 = new Card("9999-9999-9999-9999", "Debit card", cal.getTime());
	
	Amount amount1 = new Amount("Insurance", 123123.345);
	
	Country country1 = new Country("C099", "TestCountry", "TestState");
	


	CustomerDetails customer1 = new CustomerDetails("C00099", "TestCustomer", "TestVendor", "TestAddress",
			"TestContactNumber", "TestEmail", "TestDocType", "TestDocNo", cal.getTime(), 3213213.22, card1, amount1,
			country1);
//	Optional o = Optional.of(customer1);
	/*
	 * @Test public void getCustomer() throws Exception{ // CustomerDetails cd =
	 * new CustomerDetails(); // Optional<CustomerDetails> ocd =
	 * Optional.of(cd); // Mockito.when( //
	 * cds.findById(customerId)).thenReturn(ocd);
	 * 
	 * 
	 * RequestBuilder requestB
ilder = MockMvcRequestBuilders
	 * .get("/customerservice/getcustomer/C0001")
	 * .accept(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * String expected = "{customerId: 'C0001'," +"customerName: 'Srishtti S',"
	 * +"vendorName: 'WBSEDCL'," +"address: 'Bungalow, Madurai',"
	 * +"contactNumber: '8974563210'," +"email: 'srish@gmail.com',"
	 * +"idDocType: 'VoterId'," +"docNo: 'GMV555'," +"regDate: '2018-11-21',"
	 * +"balance: 897452," +"card: {" +"cardNo: '7894-5612-3000-0000',"
	 * +"cardType: 'AMEX'," +"cardValidity: '2019-02-23T00:00:00.000+0000'"
	 * +"}," +"amount: {" +"vendorType: 'Electricity'," +"amount: 1000" +"},"
	 * +"country: {" +"countryId: 'C05'," +"country: 'UK'," +"state: 'Scotland'"
	 * +"}" +"}"; System.out.println(result.getResponse().getContentAsString());
	 * JSONAssert.assertEquals(expected,
	 * result.getResponse().getContentAsString(), false); }
	 */

	
	  @Test 
	  public void findCustomerByIdTest() throws Exception{
	  Mockito.when(cds.findById(Mockito.anyString())).thenReturn(Optional.of(customer1));
	  
	  RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customerservice/getcustomer/C00099")
			  .accept(MediaType.APPLICATION_JSON);
	  
	  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	  
	  System.out.println(result.getResponse());
	  
	  //String expected = "{    customerId: 'C00099',    customerName: 'TestCustomer',    vendorName: 'TestVendor',    address: 'TestAddress',    contactNumber: 'TestContactNumber',    email: 'TestEmail',    idDocType:'TestDocType',    docNo: 'TestDocNo',    regDate:"+d+",    balance: 3213213.22,    card: {        cardNo: '9999-9999-9999-9999',        cardType: 'Debit card',        cardValidity: "+d+"    },    amount: {        vendorType: 'Insurance',        amount: 123123.345   },    country: {        countryId: 'C099',        country: 'TestCountry',        state: 'TestState'    }}";
	  //String expected = null;
	  String expected = "{customerId: 'C00099'," +"customerName: 'TestCustomer',"
				 +"vendorName: 'TestVendor'," +"address: 'TestAddress',"
				 +"contactNumber: 'TestContactNumber'," +"email: 'TestEmail',"
				 +"idDocType: 'TestDocType'," +"docNo: 'TestDocNo'," +"regDate: '"+dateStr+"',"
				 +"balance: 3213213.22," +"card: {" +"cardNo: '9999-9999-9999-9999',"
				 +"cardType: 'Debit card'," +"cardValidity: '"+dateStr+"'"
				 +"}," +"amount: {" +"vendorType: 'Insurance'," +"amount: 123123.345" +"},"
				 +"country: {" +"countryId: 'C099'," +"country: 'TestCountry'," +"state: 'TestState'"
				 +"}" +"}";
	  JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	  }
	 

}
