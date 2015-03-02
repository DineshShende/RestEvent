package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_ID;
import static com.projectx.rest.fixture.quickregister.EmailVerificationDetailsFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailCustomerDTO;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardJsonEmailMobileCustomer;
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
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.exception.repository.quickregister.QuickRegisterEntityNotFoundException;
import com.projectx.rest.handlers.quickregister.QuickRegisterHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class MobileVerificationControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterHandler customerQuickRegisterHandler;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void environmentTest() {
		
		
		
	}
	
	@Test
	public void sendEmailHash() throws Exception {
		
		QuickRegisterEntity quickRegisterEntity=null;
		
		try{
			quickRegisterEntity=customerQuickRegisterHandler.getByEntityId(CUST_ID);
		}catch(QuickRegisterEntityNotFoundException e)
		{
			assertNull(quickRegisterEntity);
		}
		
	
		QuickRegisterEntity handledEntity=customerQuickRegisterHandler
				.handleNewCustomerQuickRegister(standardEmailCustomerDTO()).getCustomer();

		this.mockMvc.perform(
	            post("/customer/quickregister/resendEmailHash")
	                    .content(standardJsonUpdateEmailHashDTOMVC(handledEntity.getCustomerId()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print());/*
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("status").value(true))
	    */
				
	}
	
	

}
