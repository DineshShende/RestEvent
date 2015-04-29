package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

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

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.controller.quickregister.QuickRegisterController;
import com.projectx.rest.repositoryImpl.quickregister.QuickRegisterRepositoryImpl;
import com.projectx.rest.services.quickregister.QuickRegisterService;


public class QuickRegisterControllerStandAloneTest {

	@InjectMocks
	QuickRegisterController customerQuickRegisterController;
	
	@Mock
	QuickRegisterService customerQuickRegisterService; 
	
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
		
		when(customerQuickRegisterService.getByEntityId(standardEmailMobileCustomer().getCustomerId()))
																				.thenReturn(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.result.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.result.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.result.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
	            .andExpect(jsonPath("$.result.insertTime").exists())
				.andExpect(jsonPath("$.result.updateTime").exists())
				.andExpect(jsonPath("$.result.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()))
				.andExpect(jsonPath("$.errorMessage").value(""));
		
			verify(customerQuickRegisterService,times(1))
						.getByEntityId(standardEmailMobileCustomer().getCustomerId());
			
			verifyNoMoreInteractions(customerQuickRegisterService);
	}
	
		
	
		
	
	
	
}

