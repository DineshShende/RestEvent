package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

public class AuthenticationControllerStandAloneTest {

	@InjectMocks
	AuthenticationController authenticationController;
	
	@Mock
	AuthenticationService authenticationService;
	
	@Mock
	QuickRegisterService quickRegisterService;
	
	private MockMvc mockMvc;
	
	private Integer EMAIL_REQ=1;
	
	private Integer MOBILE_REQ=2;
	
		
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

	    this.mockMvc = standaloneSetup(authenticationController)
	    		.build();
	    
	}

	
	@Test
	public void environmentTest() {
		
	}
	
		
	
		/*
	@Test
	public void resetPassword() throws Exception
	{
		when(authenticationService.resetPassword(standardCustomerIdTypeUpdatedByDTO(),EMAIL_REQ)).thenReturn(true);
		
			this.mockMvc.perform(
	            post("/customer/quickregister/resetPassword")
	                    .content(standardJsonCustomerIdTypeEmailOrMobileOptionUpdatedBy(standardCustomerIdTypeEmailOrMobileOptionUpdatedBy()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
		
		
	}


	
	@Test
	public void resendPassword() throws Exception
	{
		when(authenticationService.resendPassword(standardCustomerIdTypeUpdatedByDTO(),EMAIL_REQ)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendPassword")
	                    .content(standardJsonCustomerIdTypeEmailOrMobileOptionUpdatedBy(standardCustomerIdTypeEmailOrMobileOptionUpdatedBy()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		
	}
	
	*/


}
