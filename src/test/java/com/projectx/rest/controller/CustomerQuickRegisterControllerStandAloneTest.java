package com.projectx.rest.controller;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.repositoryImpl.CustomerQuickRegisterRepositoryImpl;
import com.projectx.rest.services.CustomerQuickRegisterService;


public class CustomerQuickRegisterControllerStandAloneTest {

	@InjectMocks
	CustomerQuickRegisterController customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterService customerQuickRegisterService; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(customerQuickRegisterController)
	    		.build();
	    
	}

	
	@Test
	public void getByCustomerIdWithEmailMobileCustomer() throws Exception {
		
		when(customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(standardEmailMobileCustomer().getCustomerId()))
																				.thenReturn(standardEmailMobileCustomer());
		
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
		when(customerQuickRegisterService.verifyEmailHash(CUST_ID, CUST_EMAILHASH)).thenReturn(true);
		
		
		this.mockMvc.perform(
				post("/customer/quickregister/verifyEmailHash")
                .content(standardJsonVerifyEmailHashDTO())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(content().string("true"))
	            .andExpect(status().isOk());
		
	}
	

	@Test
	public void verifyMobilePinForMobileCustomer() throws Exception
	{
		when(customerQuickRegisterService.verifyMobilePin(CUST_ID, CUST_MOBILEPIN)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyMobilePin")
	                    .content(standardJsonVerifyMobilePinDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	}
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		when(customerQuickRegisterService.reSendMobilePin(CUST_ID)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendMobilePin")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		when(customerQuickRegisterService.reSendEmailHash(CUST_ID)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendEmailHash")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	
}

