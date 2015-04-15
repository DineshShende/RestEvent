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
	
		
	
	@Test
	public void updatePassword() throws Exception
	{
		when(authenticationService.updatePassword(standardUpdatePasswordAndPasswordTypeDTO())).thenReturn(true);
	
		when(authenticationService.getByEntityIdType(standardUpdatePasswordAndPasswordTypeDTO().getCustomerId(),
				standardUpdatePasswordAndPasswordTypeDTO().getCustomerType())).thenReturn(standardCustomerEmailAuthenticationDetails());
		
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
		when(authenticationService.resetPassword(standardCustomerIdTypeUpdatedByDTO(),EMAIL_REQ)).thenReturn(true);
		
			this.mockMvc.perform(
	            post("/customer/quickregister/resetPassword")
	                    .content(standardJsonCustomerIdTypeEmailOrMobileOptionUpdatedBy(standardCustomerIdTypeEmailOrMobileOptionUpdatedBy()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))

	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		
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
