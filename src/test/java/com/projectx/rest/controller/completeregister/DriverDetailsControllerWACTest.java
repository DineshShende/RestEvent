package com.projectx.rest.controller.completeregister;




import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.DriverDetailsDataFixtures.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.services.completeregister.DriverDetailsService;
import com.projectx.rest.services.quickregister.MobileVerificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class DriverDetailsControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	DriverDetailsService driverDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService; 
	
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
		driverDetailsService.clearTestData();
		mobileVerificationService.clearTestData();
	}
	
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	public void save() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/driver")
	                    .content(standardDriverJson(standardDriverDetails()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());

	}

	
	@Test
	public void update() throws Exception
	{
		DriverDetails driverDetails=driverDetailsService.addDriver(standardDriverDetails());
		
		this.mockMvc.perform(
	            post("/vendor/driver/update")
	                    .content(standardDriverJson(standardDriverDetailsNewMobileAndFirstName(driverDetails.getDriverId())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardDriverDetailsNewMobileAndFirstName(driverDetails.getDriverId()).getFirstName()))
		 		.andExpect(jsonPath("$.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
		
	}
	
	@Test
	public void getByDriverId() throws Exception
	{
		
		DriverDetails driverDetails=driverDetailsService.addDriver(standardDriverDetails());
		
		this.mockMvc.perform(
	            get("/vendor/driver/getByDriverId/"+driverDetails.getDriverId())
	                    )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	}
	
	@Test
	public void deleteByDriverId() throws Exception
	{
		DriverDetails driverDetails=driverDetailsService.addDriver(standardDriverDetails());
		
		assertEquals(1, driverDetailsService.count().intValue());
		
		
		this.mockMvc.perform(
	            get("/vendor/driver/deleteByDriverId/"+driverDetails.getDriverId())
	                    )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		assertEquals(0, driverDetailsService.count().intValue());
	    
	}
	
	@Test
	public void getDriversByVendor() throws Exception
	{
		DriverDetails driverDetails=driverDetailsService.addDriver(standardDriverDetails());
		
		DriverDetails driverDetailsOther=driverDetailsService.addDriver(standardDriverDetailsOther());
		
		this.mockMvc.perform(
	            get("/vendor/driver/getDriversByVendor/"+standardDriverDetails().getVendorId())
	                    )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.[0].firstName").value(standardDriverDetails().getFirstName()))
		 		.andExpect(jsonPath("$.[0].middleName").value(standardDriverDetails().getMiddleName()))
		 		.andExpect(jsonPath("$.[0].lastName").value(standardDriverDetails().getLastName()))
		 		.andExpect(jsonPath("$.[0].bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.[0].homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.[0].homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.[0].homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.[0].homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.[0].homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.[0].homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.[0].mobile").value(standardDriverDetails().getMobile()))
	            .andExpect(jsonPath("$.[0].isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.[0].homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.[0].isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.[0].isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.[0].licenceNumber").value(standardDriverDetails().getLicenceNumber()))
	            .andExpect(jsonPath("$.[0].language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists())
	            
	            .andExpect(jsonPath("$.[1].firstName").value(standardDriverDetailsOther().getFirstName()))
		 		.andExpect(jsonPath("$.[1].middleName").value(standardDriverDetailsOther().getMiddleName()))
		 		.andExpect(jsonPath("$.[1].lastName").value(standardDriverDetailsOther().getLastName()))
		 		.andExpect(jsonPath("$.[1].bloodGroup").value(standardDriverDetails().getBloodGroup()))
		 		.andExpect(jsonPath("$.[1].homeAddress.addressLine").value(standardDriverDetails().getHomeAddress().getAddressLine()))
	            .andExpect(jsonPath("$.[1].homeAddress.customerType").value(standardDriverDetails().getHomeAddress().getCustomerType()))
	            .andExpect(jsonPath("$.[1].homeAddress.city").value(standardDriverDetails().getHomeAddress().getCity()))
	            .andExpect(jsonPath("$.[1].homeAddress.district").value(standardDriverDetails().getHomeAddress().getDistrict()))
	            .andExpect(jsonPath("$.[1].homeAddress.state").value(standardDriverDetails().getHomeAddress().getState()))
	            .andExpect(jsonPath("$.[1].homeAddress.pincode").value(standardDriverDetails().getHomeAddress().getPincode()))
	            .andExpect(jsonPath("$.[1].mobile").value(standardDriverDetailsOther().getMobile()))
	            .andExpect(jsonPath("$.[1].isMobileVerified").value(standardDriverDetails().getIsMobileVerified()))
	            .andExpect(jsonPath("$.[1].homeContactNumber").value(standardDriverDetails().getHomeContactNumber()))
	            .andExpect(jsonPath("$.[1].isFreightRequestPermissionGiven").value(standardDriverDetails().getIsFreightRequestPermissionGiven()))
	            .andExpect(jsonPath("$.[1].isDealFinalizationPermissionGiven").value(standardDriverDetails().getIsDealFinalizationPermissionGiven()))
	            .andExpect(jsonPath("$.[1].licenceNumber").value(standardDriverDetailsOther().getLicenceNumber()))
	            .andExpect(jsonPath("$.[1].language").value(standardDriverDetails().getLanguage()))
	            .andExpect(jsonPath("$.[1].updatedBy").value(standardDriverDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[1].insertTime").exists())
	            .andExpect(jsonPath("$.[1].updateTime").exists())
	            ;
		
		
	}
	
	
}
