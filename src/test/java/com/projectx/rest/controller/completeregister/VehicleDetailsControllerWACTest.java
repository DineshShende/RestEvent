package com.projectx.rest.controller.completeregister;


import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.*;
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
import com.projectx.rest.domain.completeregister.VehicleDetails;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class VehicleDetailsControllerWACTest {

	
	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@Before
	public void setUp()
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
		vehicleDetailsService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}

	@Test
	public void addVehicle() throws Exception
	{
		this.mockMvc.perform(
	            post("/vendor/vehicle")
	                    .content((standardVehicleJson(standardVehicleDetails())))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		.andExpect(jsonPath("$.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());

	}
	
	@Test
	public void getByVehicleId() throws Exception
	{
		
		VehicleDetails vehicleDetails=vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vendor/vehicle/getByVehicleId/"+vehicleDetails.getVehicleId())
	                )
	            .andDo(print())
	            .andExpect(status().isFound())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		.andExpect(jsonPath("$.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());

		
	}
	
	@Test
	public void deleteByVehicleId() throws Exception
	{
		VehicleDetails vehicleDetails=vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		this.mockMvc.perform(
	            get("/vendor/vehicle/deleteByVehicleId/"+vehicleDetails.getVehicleId())
	                )
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
		assertEquals(0, vehicleDetailsService.count().intValue());

	}
	
	@Test
	public void getDriversByVendor() throws Exception
	{
		VehicleDetails vehicleDetails=vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		VehicleDetails vehicleDetailsOther=vehicleDetailsService.addVehicle(standardVehicleDetailsOther());
		
		this.mockMvc.perform(
	            get("/vendor/vehicle/getVehicleByVendor/"+vehicleDetails.getVendorId())
	                )
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	         //   .andExpect(jsonPath("$.key.customerId").value(standardDocumentDetailsWithDummyDocument().getKey().getCustomerId()))
		
		 		.andExpect(jsonPath("$.[0].ownerFirstName").value(standardVehicleDetails().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.[0].ownerMiddleName").value(standardVehicleDetails().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.[0].ownerLastName").value(standardVehicleDetails().getOwnerLastName()))
		 		.andExpect(jsonPath("$.[0].vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[0].vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.[0].vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.[0].vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[0].vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.[0].isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.[0].registrationNumber").value(standardVehicleDetails().getRegistrationNumber()))
	            .andExpect(jsonPath("$.[0].chassisNumber").value(standardVehicleDetails().getChassisNumber()))
	            .andExpect(jsonPath("$.[0].loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.[0].numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.[0].permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.[0].insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.[0].insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.[0].insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists())
	            
	             .andExpect(jsonPath("$.[1].ownerFirstName").value(standardVehicleDetailsOther().getOwnerFirstName()))
		 		.andExpect(jsonPath("$.[1].ownerMiddleName").value(standardVehicleDetailsOther().getOwnerMiddleName()))
		 		.andExpect(jsonPath("$.[1].ownerLastName").value(standardVehicleDetailsOther().getOwnerLastName()))
		 		.andExpect(jsonPath("$.[1].vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[1].vehicleBrandId.vehicleBrandName").value(standardVehicleBrandDetails().getVehicleBrandName()))
	            .andExpect(jsonPath("$.[1].vehicleBrandId.modelNumber").value(standardVehicleBrandDetails().getModelNumber()))
	            .andExpect(jsonPath("$.[1].vehicleBrandId.vehicleTypeId.vehicleTypeName").value(standVehicleTypeDetails().getVehicleTypeName()))
	            .andExpect(jsonPath("$.[1].vehicleBodyType").value(standardVehicleDetails().getVehicleBodyType()))
	            .andExpect(jsonPath("$.[1].isBodyTypeFlexible").value(standardVehicleDetails().getIsBodyTypeFlexible()))
	            .andExpect(jsonPath("$.[1].registrationNumber").value(standardVehicleDetailsOther().getRegistrationNumber()))
	            .andExpect(jsonPath("$.[1].chassisNumber").value(standardVehicleDetailsOther().getChassisNumber()))
	            .andExpect(jsonPath("$.[1].loadCapacityInTons").value(standardVehicleDetails().getLoadCapacityInTons()))
	            .andExpect(jsonPath("$.[1].numberOfWheels").value(standardVehicleDetails().getNumberOfWheels()))
	            .andExpect(jsonPath("$.[1].permitType").value(standardVehicleDetails().getPermitType()))
	            .andExpect(jsonPath("$.[1].insuranceStatus").value(standardVehicleDetails().getInsuranceStatus()))
	            .andExpect(jsonPath("$.[1].insuranceNumber").value(standardVehicleDetails().getInsuranceNumber()))
	            .andExpect(jsonPath("$.[1].insuranceCompany").value(standardVehicleDetails().getInsuranceCompany()))
	            .andExpect(jsonPath("$.[1].updatedBy").value(standardVehicleDetails().getUpdatedBy()))
	            .andExpect(jsonPath("$.[1].insertTime").exists())
	            .andExpect(jsonPath("$.[1].updateTime").exists());

		
		
	}
	
}
