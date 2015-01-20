package com.projectx.rest.controller.quickregister;

import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardCustomerEmailMobileAuthenticationDetails;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardCustomerIdDTO;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardEmailLoginVerification;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardJsonLoginVerification;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardJsonUpdatePasswordAndPasswordType;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardLoginVerificationWithEmail;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardUpdatePasswordEmailPasswordAndPasswordTypeDTO;
import static com.projectx.rest.fixture.quickregister.AuthenticationDetailsDataFixtures.standardUpdatePasswordDTO;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.CUST_MOBILE;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardJsonCustomerIdDTO;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;

public class AuthenticationControllerStandAloneTest {

	@InjectMocks
	AuthenticationController authenticationController;
	
	@Mock
	AuthenticationService authenticationService; 
	
	private MockMvc mockMvc;
	
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
	public void verifyLoginDetails() throws Exception
	{
		when(authenticationService.verifyLoginDetails(standardLoginVerificationWithEmail())).thenReturn(standardCustomerEmailMobileAuthenticationDetails());
		
		//System.out.println(standardLoginVerificationWithEmail());
		
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
				.andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
				.andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailMobileAuthenticationDetails().getEmailPassword()));
	}
	
	
	
	@Test
	public void verifyLoginDefaultEmailPassword() throws Exception
	{
		
		when(authenticationService.verifyDefaultEmailLoginDetails(standardEmailLoginVerification())).thenReturn(standardCustomerEmailMobileAuthenticationDetails());

		this.mockMvc.perform(
	            post("/customer/quickregister/verifyLoginDefaultEmailPassword")
	                    .content(standJsonEmailPasswordLoginVerification())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.mobile").value(standardCustomerEmailMobileAuthenticationDetails().getMobile()))
	            .andExpect(jsonPath("$.email").value(standardCustomerEmailMobileAuthenticationDetails().getEmail()))
	            .andExpect(jsonPath("$.password").value(standardCustomerEmailMobileAuthenticationDetails().getPassword()))
				.andExpect(jsonPath("$.passwordType").value(standardCustomerEmailMobileAuthenticationDetails().getPasswordType()))
				.andExpect(jsonPath("$.emailPassword").value(standardCustomerEmailMobileAuthenticationDetails().getEmailPassword()));

		
	}
	
	
	@Test
	public void updatePassword() throws Exception
	{
		when(authenticationService.updatePassword(standardUpdatePasswordAndPasswordTypeDTO())).thenReturn(true);
		
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
		when(authenticationService.resetPassword(standardCustomerIdDTO())).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resetPassword")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		
	}
*/

	
	@Test
	public void resendPassword() throws Exception
	{
		when(authenticationService.resendPassword(standardCustomerIdDTO())).thenReturn(true);
		
		this.mockMvc.perform(
	            post("/customer/quickregister/resendPassword")
	                    .content(standardJsonCustomerIdDTO())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		
	}
	
	/*
	@Test
	public void resetPasswordRedirect() throws Exception
	{
		when(authenticationService.resetPasswordByEmailOrMobileRedirect(Long.toString(CUST_MOBILE))).thenReturn(standardEmailMobileCustomer());
		
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
	
	
*/


}
