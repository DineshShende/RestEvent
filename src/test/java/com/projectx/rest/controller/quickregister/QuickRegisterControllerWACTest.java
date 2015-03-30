package com.projectx.rest.controller.quickregister;


import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.rest.config.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class QuickRegisterControllerWACTest {
	
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@After
	@Before
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(get("/customer/quickregister/cleartestdata"));
		this.mockMvc.perform(get("/customer/quickregister/cleartestdata"));
		this.mockMvc.perform(get("/customer/quickregister/cleartestdata"));
		this.mockMvc.perform(get("/customer/quickregister/cleartestdata"));
	}
	

	@Test
	public void AddCustomerQuickRegisterWithEmailMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("status").value(REGISTER_REGISTERED_SUCESSFULLY))
	            .andExpect(jsonPath("$.customer.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.customer.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.customer.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.customer.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.customer.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
	            .andExpect(jsonPath("$.customer.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
	            .andExpect(jsonPath("$.customer.insertTime").exists())
				.andExpect(jsonPath("$.customer.updateTime").exists())
				.andExpect(jsonPath("$.customer.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
		
		
	}

	
	@Test
	public void checkIfExistStatusWithEmailMobileCustomer() throws Exception {
	
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON));
	            	
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonEmailMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isAlreadyReported())
	            
	             .andExpect(jsonPath("status").value("EMAIL_MOBILE_ALREADY_REGISTERED_EMAIL_MOBILE_UNVERIFIED"))
	            .andExpect(jsonPath("$.customer.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.customer.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.customer.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.customer.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.customer.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
	            .andExpect(jsonPath("$.customer.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
	            .andExpect(jsonPath("$.customer.insertTime").exists())
				.andExpect(jsonPath("$.customer.updateTime").exists())
				.andExpect(jsonPath("$.customer.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
			 	
	}
	
	
	

	@Test
	public void AddCustomerQuickRegisterWithMobileCustomer() throws Exception {
		
		this.mockMvc.perform(
	            post("/customer/quickregister")
	                    .content(standardJsonMobileCustomer())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("status").value(REGISTER_REGISTERED_SUCESSFULLY))
	            .andExpect(jsonPath("$.customer.firstName").value(standardMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.customer.lastName").value(standardMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.customer.mobile").value(standardMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.customer.email").value(standardMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.customer.isMobileVerified").value(standardMobileCustomer().getIsMobileVerified()))
	            .andExpect(jsonPath("$.customer.isEmailVerified").doesNotExist())
	            .andExpect(jsonPath("$.customer.insertTime").exists())
				.andExpect(jsonPath("$.customer.updateTime").exists())
				.andExpect(jsonPath("$.customer.updatedBy").value(standardMobileCustomer().getUpdatedBy()));				
	}
	
	
	
			
}
