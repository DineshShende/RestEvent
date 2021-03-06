package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
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
	public void getMobileVerificationDetails() throws Exception
	{
		when(mobileVerificationService.getByEntityIdTypeAndMobileType(standardCustomerIdMobileDTO().getCustomerId(),
				standardCustomerIdMobileDTO().getCustomerType(),standardCustomerIdMobileDTO().getMobileType())).thenReturn(standardCustomerMobileVerificationDetails());
		
	
		this.mockMvc.perform(
				post("/customer/quickregister/getMobileVerificationDetails")
				.content(standardJsonUpdateMobilePinDTOMVC())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isFound())
		//.andExpect(jsonPath("$.customerId").value(standardCustomerMobileVerificationDetails().getCustomerId()))
	    .andExpect(jsonPath("$.key.mobileType").value(standardCustomerMobileVerificationDetails().getKey().getMobileType()))
	    .andExpect(jsonPath("$.mobile").value(standardCustomerMobileVerificationDetails().getMobile()))
	    .andExpect(jsonPath("$.mobilePin").value(standardCustomerMobileVerificationDetails().getMobilePin()))
	    .andExpect(jsonPath("$.mobileVerificationAttempts").value(standardCustomerMobileVerificationDetails().getMobileVerificationAttempts()))
	    .andExpect(jsonPath("$.resendCount").value(standardCustomerMobileVerificationDetails().getResendCount()));
	    
	

		
	}
	
	/*
	@Test
	public void reSetMobilePin() throws Exception
	{
		when(mobileVerificationService.reSetMobilePin(CUST_ID,ENTITY_TYPE_CUSTOMER,MOB_TYPE_PRIMARY,CUST_UPDATED_BY,CUST_ID)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetMobilePin")
	                    .content(standardJsonUpdateMobilePinUpdatedByDTOMVC(standardCustomerIdTypeMobileTypeUpdatedByDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
		
	}
	
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		when(mobileVerificationService.reSendMobilePin(CUST_ID,ENTITY_TYPE_CUSTOMER,MOB_TYPE_PRIMARY,CUST_UPDATED_BY,CUST_ID)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendMobilePin")
	                    .content(standardJsonUpdateMobilePinUpdatedByDTOMVC(standardCustomerIdTypeMobileTypeUpdatedByDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
		
	}
	*/


}
