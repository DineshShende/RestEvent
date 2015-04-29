package com.projectx.rest.controller.completeregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.standardAddress;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static org.junit.Assert.*;
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
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.TransactionalUpdatesRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.fixture.completeregister.VendorDetailsDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class VendorDetailsControllerWACTest {
	
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Autowired
	TransactionalUpdatesRepository transactionalUpdatesRepository;
	
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
				get("/vendor/clearTestData"));				
			
		authenticationService.clearTestData();
		mobileVerificationService.clearTestData();
		emailVerificationService.clearTestData();
	}

	
	@Test
	public void createFromQuickRegister() throws Exception {
		
		QuickRegisterEntity quickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileVendor()).getCustomerQuickRegisterEntity();
		
		
		
		this.mockMvc.perform(
				post("/vendor/createFromQuickRegister")
				.content(standardJsonEntityIdDTO(standardEntityIdDTO(quickRegisterEntity.getCustomerId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName").value(standardVendorCreatedFromQuickRegister().getFirstName()))
	    .andExpect(jsonPath("$.lastName").value(standardVendorCreatedFromQuickRegister().getLastName()))
	    .andExpect(jsonPath("$.firmAddressId").doesNotExist())
	    .andExpect(jsonPath("$.mobile").value(standardVendorCreatedFromQuickRegister().getMobile()))
	    .andExpect(jsonPath("$.email").value(standardVendorCreatedFromQuickRegister().getEmail()))
	    .andExpect(jsonPath("$.isMobileVerified").value(standardVendorCreatedFromQuickRegister().getIsMobileVerified()))
	    .andExpect(jsonPath("$.isEmailVerified").value(standardVendorCreatedFromQuickRegister().getIsEmailVerified()))
	    .andExpect(jsonPath("$.language").doesNotExist())
	    .andExpect(jsonPath("$.businessDomain").doesNotExist())
	    .andExpect(jsonPath("$.dateOfBirth").doesNotExist())
	    .andExpect(jsonPath("$.insertTime").exists())
		.andExpect(jsonPath("$.updateTime").exists())
		.andExpect(jsonPath("$.updatedBy").value(standardVendorCreatedFromQuickRegister().getUpdatedBy()));
	
	}
	

	@Test
	public void update() throws Exception {
		
		this.mockMvc.perform(
				post("/vendor/update")
				.content(standardJsonVendor(standardVendor()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName").value(standardVendor().getFirstName()))
	    .andExpect(jsonPath("$.lastName").value(standardVendor().getLastName()))
	    .andExpect(jsonPath("$.firmAddress.customerType").value(standardAddress().getCustomerType()))
	    .andExpect(jsonPath("$.firmAddress.addressLine").value(standardAddress().getAddressLine()))
	    .andExpect(jsonPath("$.firmAddress.city").value(standardAddress().getCity()))
	    .andExpect(jsonPath("$.firmAddress.district").value(standardAddress().getDistrict()))
	    .andExpect(jsonPath("$.firmAddress.state").value(standardAddress().getState()))
	    .andExpect(jsonPath("$.firmAddress.pincode").value(standardAddress().getPincode()))
	    .andExpect(jsonPath("$.mobile").value(standardVendor().getMobile()))
	    .andExpect(jsonPath("$.email").value(standardVendor().getEmail()))
	    .andExpect(jsonPath("$.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	    .andExpect(jsonPath("$.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	    .andExpect(jsonPath("$.laguage").value(standardVendor().getLaguage()))
	    
	    .andExpect(jsonPath("$.dateOfBirth").exists())
	    .andExpect(jsonPath("$.insertTime").exists())
		.andExpect(jsonPath("$.updateTime").exists())
		.andExpect(jsonPath("$.updatedBy").value(standardVendorCreatedFromQuickRegister().getUpdatedBy()));
	
		
		
	}
	
	@Test
	public void getVendorDetailsById() throws Exception {
		
		QuickRegisterEntity quickRegisterEntity=transactionalUpdatesRepository
				.saveNewQuickRegisterEntity(standardEmailMobileVendorQuick()).getCustomerQuickRegisterEntity();
	
		
		this.mockMvc.perform(
				post("/vendor/createFromQuickRegister")
				.content(standardJsonQuickRegisterVendor(quickRegisterEntity))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				post("/vendor/update")
				.content(standardJsonVendor(standardVendor(quickRegisterEntity.getCustomerId())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		this.mockMvc.perform(
				get("/vendor/getVendorDetailsById/"+quickRegisterEntity.getCustomerId())
																			)
		.andDo(print())
		.andExpect(status().isFound())
		.andExpect(jsonPath("$.firstName").value(standardVendor().getFirstName()))
	    .andExpect(jsonPath("$.lastName").value(standardVendor().getLastName()))
	    .andExpect(jsonPath("$.firmAddress.customerType").value(standardAddress().getCustomerType()))
	    .andExpect(jsonPath("$.firmAddress.addressLine").value(standardAddress().getAddressLine()))
	    .andExpect(jsonPath("$.firmAddress.city").value(standardAddress().getCity()))
	    .andExpect(jsonPath("$.firmAddress.district").value(standardAddress().getDistrict()))
	    .andExpect(jsonPath("$.firmAddress.state").value(standardAddress().getState()))
	    .andExpect(jsonPath("$.firmAddress.pincode").value(standardAddress().getPincode()))
	    .andExpect(jsonPath("$.mobile").value(standardVendor().getMobile()))
	    .andExpect(jsonPath("$.email").value(standardVendor().getEmail()))
	    .andExpect(jsonPath("$.isMobileVerified").value(standardVendor().getIsMobileVerified()))
	    .andExpect(jsonPath("$.isEmailVerified").value(standardVendor().getIsEmailVerified()))
	    .andExpect(jsonPath("$.laguage").value(standardVendor().getLaguage()))
	    
	    .andExpect(jsonPath("$.dateOfBirth").exists())
	    .andExpect(jsonPath("$.insertTime").exists())
		.andExpect(jsonPath("$.updateTime").exists())
		.andExpect(jsonPath("$.updatedBy").value(standardVendorCreatedFromQuickRegister().getUpdatedBy()));
	
	}
	
	
}
