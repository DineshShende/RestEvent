package com.projectx.rest.controller;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.rest.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration


//@ActiveProfiles("Test")
public class CustomerQuickRegisterControllerITest {
	
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	
	@After
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(get("/customer/quickregister/cleartestdata"));
	}
	
	
	@Test
	public void checkIfExistStatusWithEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister/checkifexist")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string(REGISTER_NOT_REGISTERED));
	 	
	}
	
	@Test
	public void AddCustomerQuickRegisterWithEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(CUST_FIRSTNAME))
	            .andExpect(jsonPath("$.lastName").value(CUST_LASTNAME))
	           // .andExpect(jsonPath("$.mobile").value(CUST_MOBILE.longValue()))
	            .andExpect(jsonPath("$.email").value(CUST_EMAIL))
	            .andExpect(jsonPath("$.pin").value(CUST_PIN))
				.andExpect(jsonPath("$.status").value(CUST_STATUS_EMAILMOBILE))
				.andExpect(jsonPath("$.mobilePin").value(CUST_MOBILEPIN));
			  //.andExpect(jsonPath("$.emailHash").value(CUST_EMAILHASH));
		    		
	}
	
	@Test 
	public void getByEmailWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByEmail")
	                    .content(standardJsonGetByEmailDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(CUST_FIRSTNAME))
	            .andExpect(jsonPath("$.lastName").value(CUST_LASTNAME))
	           // .andExpect(jsonPath("$.mobile").value(CUST_MOBILE.longValue()))
	            .andExpect(jsonPath("$.email").value(CUST_EMAIL))
	            .andExpect(jsonPath("$.pin").value(CUST_PIN))
				.andExpect(jsonPath("$.status").value(CUST_STATUS_EMAILMOBILE))
				.andExpect(jsonPath("$.mobilePin").value(CUST_MOBILEPIN));
			  //.andExpect(jsonPath("$.emailHash").value(CUST_EMAILHASH));

	}
	
	@Test 
	public void getByMobileWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByMobile")
	                    .content(standardJsonGetByMobileDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(CUST_FIRSTNAME))
	            .andExpect(jsonPath("$.lastName").value(CUST_LASTNAME))
	           // .andExpect(jsonPath("$.mobile").value(CUST_MOBILE.longValue()))
	            .andExpect(jsonPath("$.email").value(CUST_EMAIL))
	            .andExpect(jsonPath("$.pin").value(CUST_PIN))
				.andExpect(jsonPath("$.status").value(CUST_STATUS_EMAILMOBILE))
				.andExpect(jsonPath("$.mobilePin").value(CUST_MOBILEPIN));
			  //.andExpect(jsonPath("$.emailHash").value(CUST_EMAILHASH));
		
		
	}
	
	
	@Test
	public void verifyEmailForEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailCustomerForEmailVerification())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
	            get("/customer/quickregister/verifyemail/dineshshe/hashValue/1010101010")
	            .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(content().string("true"))
	            .andExpect(status().isOk());
	    
	   this.mockMvc.perform(
	            post("/customer/quickregister/getByEmail")
	                    .content(standardJsonGetByEmailDTOForEmailVerification())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(STATUS_EMAIL_VERFIED));


	}
	
	
	
	@Test
	public void verifyMobileForMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
			
		this.mockMvc.perform(
	            post("/customer/quickregister/verifymobile")
	                    .content(standardJsonVerifyMobileDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	    
		this.mockMvc.perform(
	            post("/customer/quickregister/getByMobile")
	                    .content(standardJsonGetByMobileDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(STATUS_MOBILE_VERFIED));
		

	}
	
	

	
	
	
}
