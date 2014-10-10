package com.projectx.rest.controller;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_EMAIL;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_FIRSTNAME;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_LASTNAME;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_MOBILEPIN;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_MOBILEPIN_UPDATED;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_PIN;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.CUST_STATUS_EMAILMOBILE;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.REGISTER_NOT_REGISTERED;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.STATUS_EMAIL_VERFIED;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.STATUS_MOBILE_VERFIED;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonEmailCustomer;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonEmailMobileCustomer;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonGetByCustomerIdDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonMobileCustomer;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonUpdateEmailHashDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonUpdateMobilePinDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonVerifyEmailHashDTO;
import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.standardJsonVerifyMobilePinDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.rest.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration


@ActiveProfiles("Test")
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
		
		Long arg0 = null;
		
		this.mockMvc.perform(
	            post("/customer/quickregister/checkifexist")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	            .andExpect(content().string(REGISTER_NOT_REGISTERED));
	           // .andReturn().getResponse().getContentAsString().toString().;
		
		System.out.println(arg0);
	 	
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
	//Cannot run in 'Dev' mode  but can run in 'Test' mode
	/*
	@Test 
	public void getByCustomerIdWithEmailMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	                    
						
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonGetByCustomerIdDTO())
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
	public void verifyEmailHashForEmailCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
				post("/customer/quickregister/verifyEmailHash")
                .content(standardJsonVerifyEmailHashDTO())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(content().string("true"))
	            .andExpect(status().isOk());
	    
	   this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonGetByCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(STATUS_EMAIL_VERFIED));
	   

	}
	
	
	
	@Test
	public void verifyMobilePinForMobileCustomer() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
			
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyMobilePin")
	                    .content(standardJsonVerifyMobilePinDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	    
		this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonGetByCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(STATUS_MOBILE_VERFIED));
		

	}
	
	
	@Test
	//@Rollback(value=false)
	public void updateMobilePin() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
	            post("/customer/quickregister/updateMobilePin")
	                    .content(standardJsonUpdateMobilePinDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonGetByCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$.mobilePin").value(CUST_MOBILEPIN_UPDATED));
	   
		
	}
	
	@Test
	//@Rollback(value=false)
	public void updateEmailHash() throws Exception
	{
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	
		this.mockMvc.perform(
	            post("/customer/quickregister/updateEmailHash")
	                    .content(standardJsonUpdateEmailHashDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("1"));
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonGetByCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());
			//	.andExpect(jsonPath("$.emailHash").value(CUST_EMAILHASH_UPDATED));
	   
		
	}
	*/	
		
}
