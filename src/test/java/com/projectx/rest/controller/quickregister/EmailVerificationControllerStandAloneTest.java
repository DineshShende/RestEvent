package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.standardCustomerIdEmailDTO;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_EMAILHASH;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_ID;

import static org.junit.Assert.*;
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

import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;

public class EmailVerificationControllerStandAloneTest {

	@InjectMocks
	EmailVerificationController emailVerificationController;
	
	@Mock
	EmailVerificationService emailVerificationService; 
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(emailVerificationController)
	    		.build();
	    
	}

	
	@Test
	public void environmentTest() {
		
	}
	

	@Test
	public void verifyEmailHashForEmailCustomer() throws Exception
	{
		when(emailVerificationService.verifyEmailHash(CUST_ID,CUST_TYPE_CUSTOMER,CUST_EMAIL ,CUST_EMAILHASH)).thenReturn(true);
		
		
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
	public void reSetEmailHash() throws Exception
	{
		when(emailVerificationService.reSetEmailHash(CUST_ID,CUST_TYPE_CUSTOMER,CUST_EMAIL)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetEmailHash")
	                    .content(standardJsonUpdateEmailHashDTOMVC())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		when(emailVerificationService.reSendEmailHash(CUST_ID,CUST_TYPE_CUSTOMER,CUST_EMAIL)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendEmailHash")
	                    .content(standardJsonUpdateEmailHashDTOMVC())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}

	@Test
	public void addCustomerEmailVerification() throws Exception
	{
		when(emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(standardCustomerIdEmailDTO().getCustomerId(),
				standardCustomerIdEmailDTO().getCustomerType(),standardCustomerIdEmailDTO().getEmail())).thenReturn(standardCustomerEmailVerificationDetails());
		
		this.mockMvc.perform(
				post("/customer/quickregister/getEmailVerificationDetails")
				.content(standardJsonUpdateEmailHashDTOMVC())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		//.andExpect(jsonPath("$.customerId").value(standardCustomerMobileVerificationDetails().getCustomerId()))
	    .andExpect(jsonPath("$.emailType").value(standardCustomerEmailVerificationDetails().getEmailType()))
	    .andExpect(jsonPath("$.key.email").value(standardCustomerEmailVerificationDetails().getKey().getEmail()))
	    .andExpect(jsonPath("$.emailHash").value(standardCustomerEmailVerificationDetails().getEmailHash()))
	    .andExpect(jsonPath("$.emailHashSentTime").exists())
	    .andExpect(jsonPath("$.resendCount").value(standardCustomerEmailVerificationDetails().getResendCount()));
	    
	}


}
