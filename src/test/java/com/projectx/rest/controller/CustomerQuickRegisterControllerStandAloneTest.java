package com.projectx.rest.controller;

import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.CustomerAuthenticationDetailsDataFixtures.*;
import static org.junit.Assert.*;
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

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.repositoryImpl.CustomerQuickRegisterRepositoryImpl;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.web.domain.LoginVerificationDTO;


public class CustomerQuickRegisterControllerStandAloneTest {

	@InjectMocks
	CustomerQuickRegisterController customerQuickRegisterController;
	
	@Mock
	CustomerQuickRegisterService customerQuickRegisterService; 
	
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
		
		when(customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(standardEmailMobileCustomer().getCustomerId()))
																				.thenReturn(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/getByCustomerId")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
	            .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
	            .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
	            .andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
	            .andExpect(jsonPath("$.insertTime").exists())
				.andExpect(jsonPath("$.updateTime").exists())
				.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
	}
	
	
	@Test
	public void verifyEmailHashForEmailCustomer() throws Exception
	{
		when(customerQuickRegisterService.verifyEmailHash(CUST_ID,CUST_EMAIL ,CUST_EMAILHASH)).thenReturn(true);
		
		
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
	public void verifyMobilePinForMobileCustomer() throws Exception
	{
		when(customerQuickRegisterService.verifyMobilePin(CUST_ID,CUST_MOBILE, CUST_MOBILEPIN)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyMobilePin")
	                    .content(standardJsonVerifyMobilePinDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	}
	
	
	@Test
	public void reSendMobilePin() throws Exception
	{
		when(customerQuickRegisterService.reSetMobilePin(CUST_ID,CUST_MOBILE)).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetMobilePin")
	                    .content(standardJsonUpdateMobilePinDTOMVC())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void reSendEmailHash() throws Exception
	{
		when(customerQuickRegisterService.reSetEmailHash(CUST_ID,CUST_EMAIL)).thenReturn(true);
		
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
	public void verifyLoginDetails() throws Exception
	{
		when(customerQuickRegisterService.verifyLoginDetails(standardLoginVerificationWithEmail())).thenReturn(standardCustomerEmailMobileAuthenticationDetails());
		
		System.out.println(standardLoginVerificationWithEmail());
		
		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDetails")
	                    .content(standardJsonLoginVerification(standardLoginVerificationWithEmail()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
				.andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()));
	}
	
	@Test
	public void updatePassword() throws Exception
	{
		when(customerQuickRegisterService.updatePassword(standardUpdatePasswordAndPasswordTypeDTO())).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/updatePassword")
	                    .content(standardJsonUpdatePasswordAndPasswordType(standardUpdatePasswordDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));

	}
	
	
	@Test
	public void resetPassword() throws Exception
	{
		when(customerQuickRegisterService.resetPassword(standardCustomerIdDTO())).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetPassword")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		
	}
	
	
	@Test
	public void resetPasswordRedirect() throws Exception
	{
		when(customerQuickRegisterService.resetPasswordByEmailOrMobileRedirect(Long.toString(CUST_MOBILE))).thenReturn(standardEmailMobileCustomer());
		
		this.mockMvc.perform(
					post("/customer/quickregister/resetPasswordRedirect")
					.content(standardJsonResetPasswordRedirect(Long.toString(CUST_MOBILE)))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName").value(standardEmailMobileCustomer().getFirstName()))
        .andExpect(jsonPath("$.lastName").value(standardEmailMobileCustomer().getLastName()))
        .andExpect(jsonPath("$.mobile").value(standardEmailMobileCustomer().getMobile()))
        .andExpect(jsonPath("$.email").value(standardEmailMobileCustomer().getEmail()))
        .andExpect(jsonPath("$.isMobileVerified").value(standardEmailMobileCustomer().getIsMobileVerified()))
        .andExpect(jsonPath("$.isEmailVerified").value(standardEmailMobileCustomer().getIsEmailVerified()))
        .andExpect(jsonPath("$.insertTime").exists())
		.andExpect(jsonPath("$.updateTime").exists())
		.andExpect(jsonPath("$.updatedBy").value(standardEmailMobileCustomer().getUpdatedBy()));
		
	}
	
}

