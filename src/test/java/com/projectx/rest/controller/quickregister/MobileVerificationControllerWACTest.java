package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.rest.fixture.quickregister.MobileVericationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import javax.swing.text.AbstractDocument.Content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.data.domain.quickregister.SendResendResetMobilePinDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobilePinDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.handlers.quickregister.QuickRegisterHandler;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class MobileVerificationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService customerQuickRegisterHandler;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Value("${SEND_REQUEST}")
	private Integer SEND_REQUEST;
	
	@Value("${RESEND_REQUEST}")
	private Integer RESEND_REQUEST;
	
	@Value("${RESET_REQUEST}")
	private Integer RESET_REQUEST;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		customerQuickRegisterHandler.clearDataForTesting();
		authenticationService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void verifyMobile() throws Exception {
		
				
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();

		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyMobilePin")
	                    .content(standardJsonVerifyMobilePinDTO(new VerifyMobilePinDTO(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
	    						ENTITY_TYPE_PRIMARY,mobileVerificationDetails.getMobilePin(),
	    						CUST_UPDATED_BY, handledEntity.getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	    
				
	}
	
	@Test
	public void sendEmailHash() throws Exception {
		
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();

		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/sendOrResendOrResetMobilePin")
	                    .content(standardJsonSendResendResetMobilePinDTO(new SendResendResetMobilePinDTO(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
	    						ENTITY_TYPE_PRIMARY,SEND_REQUEST,
	    						CUST_UPDATED_BY, handledEntity.getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	    
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		assertEquals(0, mobileVerificationDetails.getResendCount().intValue());
				
	}
	
	@Test
	public void reSendEmailHash() throws Exception {
		
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();

		
		
		this.mockMvc.perform(
	            post("/customer/quickregister/sendOrResendOrResetMobilePin")
	                    .content(standardJsonSendResendResetMobilePinDTO(new SendResendResetMobilePinDTO(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
	    						ENTITY_TYPE_PRIMARY,RESEND_REQUEST,
	    						CUST_UPDATED_BY, handledEntity.getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	    
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		assertEquals(1, mobileVerificationDetails.getResendCount().intValue());
				
	}
	
	@Test
	public void resetEmailHash() throws Exception {
		
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();

		MobileVerificationDetails mobileVerificationDetailsOld=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/sendOrResendOrResetMobilePin")
	                    .content(standardJsonSendResendResetMobilePinDTO(new SendResendResetMobilePinDTO(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
	    						ENTITY_TYPE_PRIMARY,RESET_REQUEST,
	    						CUST_UPDATED_BY, handledEntity.getCustomerId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	    
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getByEntityIdTypeAndMobileType(handledEntity.getCustomerId(), handledEntity.getCustomerType(),
						ENTITY_TYPE_PRIMARY);
		
		assertNotEquals(mobileVerificationDetailsOld.getMobilePin(), mobileVerificationDetails.getMobilePin());
				
	}

}
