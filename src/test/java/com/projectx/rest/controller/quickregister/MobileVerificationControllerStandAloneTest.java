package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.standardCustomerIdMobileDTO;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_MOBILEPIN;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_TYPE_CUSTOMER;

import static org.mockito.Mockito.when;
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
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.services.quickregister.MobileVerificationService;

public class MobileVerificationControllerStandAloneTest {

	@InjectMocks
	MobileVerificationController mobileVerificationController;
	
	@Mock
	MobileVerificationService mobileVerificationService; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(mobileVerificationController)
	    		.build();
	    
	}

	
	@Test
	public void environmentTest() {
		
	}
	

	
	@Test
	public void addCustomerMobileVerification() throws Exception
	{
		when(mobileVerificationService.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getCustomerType(),standardCustomerIdMobileDTO().getMobile())).thenReturn(standardCustomerMobileVerificationDetails());
		
	
		this.mockMvc.perform(
				post("/customer/quickregister/getMobileVerificationDetails")
				.content(standardJsonUpdateMobilePinDTOMVC())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		//.andExpect(jsonPath("$.customerId").value(standardCustomerMobileVerificationDetails().getCustomerId()))
	    .andExpect(jsonPath("$.mobileType").value(standardCustomerMobileVerificationDetails().getMobileType()))
	    .andExpect(jsonPath("$.key.mobile").value(standardCustomerMobileVerificationDetails().getKey().getMobile()))
	    .andExpect(jsonPath("$.mobilePin").value(standardCustomerMobileVerificationDetails().getMobilePin()))
	    .andExpect(jsonPath("$.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
	    .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()));
	    
	

		
	}
	
	
	
	@Test
	public void verifyMobilePinForMobileCustomer() throws Exception
	{
		when(mobileVerificationService.verifyMobilePin(CUST_ID,CUST_TYPE_CUSTOMER,CUST_MOBILE, CUST_MOBILEPIN)).thenReturn(true);
		
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
	public void reSetMobilePin() throws Exception
	{
		when(mobileVerificationService.reSetMobilePin(CUST_ID,CUST_TYPE_CUSTOMER,CUST_MOBILE)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetMobilePin")
	                    .content(standardJsonUpdateMobilePinDTOMVC())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		when(mobileVerificationService.reSendMobilePin(CUST_ID,CUST_TYPE_CUSTOMER,CUST_MOBILE)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendMobilePin")
	                    .content(standardJsonUpdateMobilePinDTOMVC())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	


}
