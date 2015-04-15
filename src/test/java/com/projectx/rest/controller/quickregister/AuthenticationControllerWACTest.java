package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationWithDefaultEmailPasswordDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.handlers.quickregister.QuickRegisterHandler;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class AuthenticationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterHandler customerQuickRegisterHandler;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		customerQuickRegisterHandler.clearDataForTesting();
	}
	
	@Test
	public void environmentTest() {
		
		
		
	}
	
	@Test
	public void verifyLoginDetails() throws Exception
	{
		
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO()).getCustomer();

		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		
		mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomerId(), handledEntity.getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetails.getMobilePin(), handledEntity.getUpdatedBy());
		
		AuthenticationDetails authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomerId(),
				handledEntity.getCustomerType());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(new LoginVerificationDTO(handledEntity.getEmail(),
	                    		authenticationDetails.getPassword())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.key.customerId").exists())
	            .andExpect(jsonPath("$.key.customerType").exists())
	            .andExpect(jsonPath("$.completeName").exists())
	            .andExpect(jsonPath("$.passwordType").value("Default"))
	            .andExpect(jsonPath("$.isCompleteRegisterCompleted").value(false));
				
	}

	
	@Test
	public void verifyLoginDefaultEmailPassword() throws Exception
	{
		
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailMobileCustomerDTO()).getCustomer();

		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(), ENTITY_TYPE_PRIMARY);
		
		
		mobileVerificationService.verifyMobilePinUpdateStatusAndSendPassword(handledEntity.getCustomerId(), handledEntity.getCustomerType(), ENTITY_TYPE_PRIMARY,
				mobileVerificationDetails.getMobilePin(), handledEntity.getUpdatedBy());
		
		AuthenticationDetails authenticationDetails=authenticationService.getByEntityIdType(handledEntity.getCustomerId(),
				handledEntity.getCustomerType());
		

		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDefaultEmailPassword")
	                    .content(standJsonEmailPasswordLoginVerification(new LoginVerificationWithDefaultEmailPasswordDTO
	                    		(handledEntity.getCustomerId(),
	                    				handledEntity.getCustomerType(), authenticationDetails.getEmailPassword())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.key.customerId").exists())
	            .andExpect(jsonPath("$.key.customerType").exists())
	            .andExpect(jsonPath("$.completeName").exists())
	            .andExpect(jsonPath("$.passwordType").value("Default"))
	            .andExpect(jsonPath("$.isCompleteRegisterCompleted").value(false));
				

		
	}


}
