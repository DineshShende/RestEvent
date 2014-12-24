package com.projectx.rest.controller.completeregister;

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

import com.projectx.data.domain.quickregister.CustomerIdTypeEmailDTO;
import com.projectx.data.domain.quickregister.CustomerIdTypeMobileDTO;
import com.projectx.mvc.domain.completeregister.VerifyEmailDTO;
import com.projectx.mvc.domain.completeregister.VerifyMobileDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("Dev")

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
	
		
		
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	
	@Test
	public void createCustomerDetailsFromQuickRegisterEntity() throws Exception
	{
		this.mockMvc.perform(
				post("/customer/createFromQuickRegister")
				.content(standardJsonQuickRegister())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getFirstName()))
    .andExpect(jsonPath("$.lastName").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getLastName()))
    .andExpect(jsonPath("$.homeAddressId").doesNotExist())
    .andExpect(jsonPath("$.mobile").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getMobile()))
    .andExpect(jsonPath("$.email").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getEmail()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getIsEmailVerified()))
    .andExpect(jsonPath("$.language").doesNotExist())
    .andExpect(jsonPath("$.businessDomain").doesNotExist())
    .andExpect(jsonPath("$.nameOfFirm").doesNotExist())
    .andExpect(jsonPath("$.firmAddressId").doesNotExist())
    .andExpect(jsonPath("$.secondaryMobile").doesNotExist())
    .andExpect(jsonPath("$.isSecondaryMobileVerified").doesNotExist())
    .andExpect(jsonPath("$.secondaryEmail").doesNotExist())
    .andExpect(jsonPath("$.dateOfBirth").doesNotExist())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getUpdatedBy()));
	
	
	}
	
	
	@Test
	public void saveCompleteEntity() throws Exception
	{
		this.mockMvc.perform(
				post("/customer")
				.content(standardJsonCustomerDetails(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))
    .andExpect(jsonPath("$.lastName").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))
    .andExpect(jsonPath("$.homeAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.homeAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.homeAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.homeAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.homeAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.mobile").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))
    .andExpect(jsonPath("$.email").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))
    .andExpect(jsonPath("$.language").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage()))
    .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain()))
    .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm()))
    .andExpect(jsonPath("$.firmAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.firmAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.firmAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.firmAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.firmAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.secondaryMobile").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))
    .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail()))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getUpdatedBy()));
	
	
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
				.content(standardJsonCustomerDetails(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity())))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
	.andDo(print())
	.andExpect(status().isOk())
	.andExpect(jsonPath("$.firstName").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getFirstName()))
    .andExpect(jsonPath("$.lastName").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLastName()))
    .andExpect(jsonPath("$.homeAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.homeAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.homeAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.homeAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.homeAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.homeAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.mobile").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getMobile()))
    .andExpect(jsonPath("$.email").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getEmail()))
    .andExpect(jsonPath("$.isMobileVerified").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsMobileVerified()))
    .andExpect(jsonPath("$.isEmailVerified").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsEmailVerified()))
    .andExpect(jsonPath("$.language").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getLanguage()))
    .andExpect(jsonPath("$.businessDomain").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getBusinessDomain()))
    .andExpect(jsonPath("$.nameOfFirm").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getNameOfFirm()))
    .andExpect(jsonPath("$.firmAddressId.customerType").value(standardAddress().getCustomerType()))
    .andExpect(jsonPath("$.firmAddressId.addressLine").value(standardAddress().getAddressLine()))
    .andExpect(jsonPath("$.firmAddressId.city").value(standardAddress().getCity()))
    .andExpect(jsonPath("$.firmAddressId.district").value(standardAddress().getDistrict()))
    .andExpect(jsonPath("$.firmAddressId.state").value(standardAddress().getState()))
    .andExpect(jsonPath("$.firmAddressId.pincode").value(standardAddress().getPincode()))
    .andExpect(jsonPath("$.secondaryMobile").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryMobile()))
    .andExpect(jsonPath("$.isSecondaryMobileVerified").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getIsSecondaryMobileVerified()))
    .andExpect(jsonPath("$.secondaryEmail").value(standardCustomerDetails(standardCustomerDetailsCopiedFromQuickRegisterEntity()).getSecondaryEmail()))
    .andExpect(jsonPath("$.dateOfBirth").exists())
    .andExpect(jsonPath("$.insertTime").exists())
	.andExpect(jsonPath("$.updateTime").exists())
	.andExpect(jsonPath("$.updatedBy").value(standardCustomerDetailsCopiedFromQuickRegisterEntity().getUpdatedBy()));
	
	
	}
	
	@Test
	public void verifySecondaryMobileDetails() throws Exception
	{
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails newEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails customerDetails=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(newEntity));
		
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(customerDetails.getCustomerId(), 1, customerDetails.getSecondaryMobile());
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(customerDetails.getCustomerId(), 1, customerDetails.getSecondaryMobile(),
				mobileVerificationDetails.getMobileType(), mobileVerificationDetails.getMobilePin());
		
		this.mockMvc.perform(
				post("/customer/verifyMobileDetails")
				.content(standardJsonVerifyMobileDetails(verifyMobileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
		
	
	}
	
	
	@Test
	public void verifyMobileDetails() throws Exception
	{

	
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails newEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerDetails customerDetails=customerDetailsService.mergeCustomerDetails(standardCustomerDetails(newEntity));
	
		MobileVerificationDetails mobileVerificationDetails=
				mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(customerDetails.getCustomerId(), 1, customerDetails.getMobile());
		
		VerifyMobileDTO verifyMobileDTO=new VerifyMobileDTO(customerDetails.getCustomerId(), 1, customerDetails.getMobile(),
				mobileVerificationDetails.getMobileType(), mobileVerificationDetails.getMobilePin());
		
		this.mockMvc.perform(
				post("/customer/verifyMobileDetails")
				.content(standardJsonVerifyMobileDetails(verifyMobileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
		
	
	}
	
	@Test
	public void verifyEmailDetails() throws Exception
	{

	
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails newEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService
				.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(newEntity.getCustomerId(), 1, newEntity.getEmail());
		
		VerifyEmailDTO verifyEmailDTO=new VerifyEmailDTO(newEntity.getCustomerId(), 1, newEntity.getEmail(),
				emailVerificationDetails.getEmailType(),emailVerificationDetails.getEmailHash());
		
		this.mockMvc.perform(
				post("/customer/verifyEmailDetails")
				.content(standardJsonVerifyEmailDetails(verifyEmailDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
		
	
	}

	@Test
	public void sendMobileVerificationDetails() throws Exception
	{
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails newEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerIdTypeMobileDTO customerIdTypeMobileDTO=new CustomerIdTypeMobileDTO(newEntity.getCustomerId(), 1, newEntity.getMobile());
		
		this.mockMvc.perform(
				post("/customer/sendMobileVerificationDetails")
				.content(standardJsonCustomerIdTypeMobileDTO(customerIdTypeMobileDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
		
		
	}
	
	@Test
	public void sendEmailVerificationDetails() throws Exception
	{
		
		QuickRegisterEntity quickRegisterEntity=quickRegisterService
				.saveNewCustomerQuickRegisterEntity(standardEmailMobileCustomer()).getCustomerQuickRegisterEntity();
		
		
		CustomerDetails newEntity=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		CustomerIdTypeEmailDTO customerIdTypeEmailDTO=new CustomerIdTypeEmailDTO(newEntity.getCustomerId(), 1, newEntity.getEmail());
		
		this.mockMvc.perform(
				post("/customer/sendEmailVerificationDetails")
				.content(standardJsonCustomerIdTypeEmailDTO(customerIdTypeEmailDTO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
		
		
	}

}
