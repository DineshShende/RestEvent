package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardJsonLoginVerification;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;

import org.junit.After;
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

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.mvc.domain.quickregister.LoginVerificationDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class EmailVerificationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService quickRegisterService; 
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Value("${SEND_REQUEST}")
	private Integer SEND_REQUEST;
	
	@Value("${RESEND_REQUEST}")
	private Integer RESEND_REQUEST;
	
	@Value("${RESET_REQUEST}")
	private Integer RESET_REQUEST;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	private Integer ZERO_COUNT=0;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		


	
	}
	
	@Before
	@After
	public void clearTestData()
	{
		quickRegisterService.clearDataForTesting();
		authenticationService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
		
	}

	
	@Test
	public void getEmailVerificationDetails() throws Exception
	{

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer())
				.getCustomerQuickRegisterEntity();
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getEmailVerificationDetails")
	                    .content(standardJsonCustomerIdTypeEmailTypeDTO(new CustomerIdTypeEmailTypeDTO(quickRegisterEntity.getCustomerId(),
	                    		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY)))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isFound())
	            .andExpect(jsonPath("$.key.customerId").exists())
	            .andExpect(jsonPath("$.key.customerType").exists())
	            .andExpect(jsonPath("$.key.emailType").exists())
	            .andExpect(jsonPath("$.email").value(quickRegisterEntity.getEmail()))
	            .andExpect(jsonPath("$.emailHash").exists())
	            .andExpect(jsonPath("$.resendCount").value(ZERO_COUNT))
	            .andExpect(jsonPath("$.updatedBy").exists())
	            .andExpect(jsonPath("$.insertedBy").exists())
	            .andExpect(jsonPath("$.updatedById").exists())
	            .andExpect(jsonPath("$.insertedById").exists())
	            .andExpect(jsonPath("$.emailHashSentTime").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
		
	}

	@Test
	public void verifyEmailHash() throws Exception
	{

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer())
				.getCustomerQuickRegisterEntity();
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getByEntityIdTypeAndEmailType(quickRegisterEntity.getCustomerId(),
        		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyEmailHash")
	                    .content(standardJsonVerifyEmailHashDTO(quickRegisterEntity.getCustomerId(),
	                    		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY,emailVerificationDetails.getEmailHash(),
	                    		CUST_UPDATED_BY,quickRegisterEntity.getCustomerId()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	}
	
	@Test
	public void sendEmailHash() throws Exception
	{

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer())
				.getCustomerQuickRegisterEntity();
		
			
		this.mockMvc.perform(
	            post("/customer/quickregister/sendOrResendOrResetEmailHash")
	                    .content(standardJsonSendResendResetEmailHashDTO(quickRegisterEntity.getCustomerId(),
	                    		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY,SEND_REQUEST,
	                    		CUST_UPDATED_BY,quickRegisterEntity.getCustomerId()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getByEntityIdTypeAndEmailType(quickRegisterEntity.getCustomerId(),
        		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY);
		
		assertEquals(0, emailVerificationDetails.getResendCount().intValue());
		
		
	}
	
	@Test
	public void reSendEmailHash() throws Exception
	{

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer())
				.getCustomerQuickRegisterEntity();
		
			
		this.mockMvc.perform(
	            post("/customer/quickregister/sendOrResendOrResetEmailHash")
	                    .content(standardJsonSendResendResetEmailHashDTO(quickRegisterEntity.getCustomerId(),
	                    		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY,RESEND_REQUEST,
	                    		CUST_UPDATED_BY,quickRegisterEntity.getCustomerId()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getByEntityIdTypeAndEmailType(quickRegisterEntity.getCustomerId(),
        		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY);
		
		assertEquals(1, emailVerificationDetails.getResendCount().intValue());
		
		
	}
	
	@Test
	public void resetEmailHash() throws Exception
	{

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer())
				.getCustomerQuickRegisterEntity();
		
		EmailVerificationDetails emailVerificationDetailsOld=emailVerificationService.getByEntityIdTypeAndEmailType(quickRegisterEntity.getCustomerId(),
        		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY);
			
		this.mockMvc.perform(
	            post("/customer/quickregister/sendOrResendOrResetEmailHash")
	                    .content(standardJsonSendResendResetEmailHashDTO(quickRegisterEntity.getCustomerId(),
	                    		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY,RESET_REQUEST,
	                    		CUST_UPDATED_BY,quickRegisterEntity.getCustomerId()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.result").value(true))
	            .andExpect(jsonPath("$.errorMessage").value(""));
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService.getByEntityIdTypeAndEmailType(quickRegisterEntity.getCustomerId(),
        		quickRegisterEntity.getCustomerType(),ENTITY_TYPE_PRIMARY);
		
		assertNotEquals(emailVerificationDetailsOld.getEmailHash(), emailVerificationDetails.getEmailHash());
		
		
	}

}
