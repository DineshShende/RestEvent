package com.projectx.rest.controller.completeregister;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeEmailTypeUpdatedByDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileTypeRequestedByDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class CustomerDetailsWACTest {
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Before
	@After
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
				get("/customer/quickregister/cleartestdata"));
		
		this.mockMvc.perform(
				get("/customer/clearTestData"));				
	
		authenticationService.clearTestData();
		
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		this.mockMvc.perform(
				post("/customer/createFromQuickRegister")
				.content(standardJsonEntityIdDTO(standardEntityIdDTO(quickRegisterEntity.getCustomerId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardCustomerFromQuickEntity().getFirstName()))
    .andExpect(jsonPath("$.lastName").value(standardCustomerFromQuickEntity().getLastName()))
    .andExpect(jsonPath("$.homeAddressId").exists())
    .andExpect(jsonPath("$.mobile").value(standardCustomerFromQuickEntity().getMobile()))
    .andExpect(jsonPath("$.email").value(standardCustomerFromQuickEntity().getEmail()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerFromQuickEntity().getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerFromQuickEntity().getIsEmailVerified()))
    .andExpect(jsonPath("$.language").doesNotExist())
    .andExpect(jsonPath("$.businessDomain").doesNotExist())
    .andExpect(jsonPath("$.nameOfFirm").doesNotExist())
    .andExpect(jsonPath("$.firmAddressId").doesNotExist())
    .andExpect(jsonPath("$.secondaryMobile").doesNotExist())
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(false))
    .andExpect(jsonPath("$.secondaryEmail").doesNotExist())
    .andExpect(jsonPath("$.dateOfBirth").doesNotExist())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardCustomerFromQuickEntity().getUpdatedBy()));
	
	
	}
	
	
	@Test
	public void saveCompleteEntity() throws Exception
	{
		this.mockMvc.perform(
				post("/customer")
				.content(standardJsonCustomerDetails(standardCustomerDetails(standardCustomerFromQuickEntity())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getFirstName()))
    .andExpect(jsonPath("$.lastName").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getLastName()))
    .andExpect(jsonPath("$.homeAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.homeAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.homeAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.homeAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.homeAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.mobile").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getMobile()))
    .andExpect(jsonPath("$.email").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getEmail()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getIsEmailVerified()))
    .andExpect(jsonPath("$.language").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getLanguage()))
    .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getBusinessDomain()))
    .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getNameOfFirm()))
    .andExpect(jsonPath("$.firmAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.firmAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.firmAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.firmAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.firmAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.secondaryMobile").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getIsSecondaryMobileVerified()))
    .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryEmail()))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardCustomerFromQuickEntity().getUpdatedBy()));
	
	
	}
	
	@Test
	public void saveCompleteEntityWithInvalidAddress() throws Exception
	{
		this.mockMvc.perform(
				post("/customer")
				.content(standardJsonCustomerDetails(standardCustomerDetailsInvalidPincode(standardCustomerFromQuickEntity())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isNotAcceptable());
	}
	
	@Test
	public void merge() throws Exception
	{
		this.mockMvc.perform(
				post("/customer/createFromQuickRegister")
				.content(standardJsonEmailMobileCustomer())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				post("/customer")
				.content(standardJsonCustomerDetails(standardCustomerDetails(standardCustomerFromQuickEntity())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getFirstName()))
    .andExpect(jsonPath("$.lastName").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getLastName()))
    .andExpect(jsonPath("$.homeAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.homeAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.homeAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.homeAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.homeAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.mobile").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getMobile()))
    .andExpect(jsonPath("$.email").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getEmail()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getIsEmailVerified()))
    .andExpect(jsonPath("$.language").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getLanguage()))
    .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getBusinessDomain()))
    .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getNameOfFirm()))
    .andExpect(jsonPath("$.firmAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.firmAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.firmAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.firmAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.firmAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.secondaryMobile").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getIsSecondaryMobileVerified()))
    .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails(standardCustomerFromQuickEntity()).getSecondaryEmail()))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardCustomerFromQuickEntity().getUpdatedBy()));
	
	
	}
	


}
