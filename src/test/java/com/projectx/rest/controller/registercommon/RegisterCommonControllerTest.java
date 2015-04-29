package com.projectx.rest.controller.registercommon;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
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

import com.projectx.rest.config.Application;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class RegisterCommonControllerTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	TransactionalUpdatesService transactionalUpdatesService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		quickRegisterService.clearDataForTesting();
		authenticationService.clearTestData();
		
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void forgetPassword() throws Exception
	{
		transactionalUpdatesService.saveNewQuickRegisterEntity(standardEmailCustomer());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetPasswordRedirect")
	                    .content(standardJsonResetPasswordRedirectDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
		
	            
	            .andExpect(jsonPath("$.result.customerType").value(standardEmailMobileCustomer().getCustomerType()))
		        .andExpect(jsonPath("$.result.mobile").doesNotExist())
	            .andExpect(jsonPath("$.result.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.result.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
	            .andExpect(jsonPath("$.result.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
	            .andExpect(jsonPath("$.errorMessage").value(""));
	    
	
	}

}
