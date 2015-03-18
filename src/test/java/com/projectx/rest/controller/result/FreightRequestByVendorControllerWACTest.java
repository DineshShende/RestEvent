package com.projectx.rest.controller.result;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;
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

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.services.completeregister.VehicleDetailsService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class FreightRequestByVendorControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
	
	}

	@Before
	@After
	public void cleanUp()
	{
		freightRequestByVendorService.clearTestData();
		vehicleDetailsService.clearTestData();
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	public void save() throws Exception
	{
	
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		this.mockMvc.perform(
	            post("/request/freightRequestByVendor")
	                    .content(stanardJsonFreightRequestByVendorDTO(standardFreightRequestByVendorDTO()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.vehicleRegistrationNumber").value(standardFreightRequestByVendor().getVehicleRegistrationNumber()))
	            .andExpect(jsonPath("$.source").value(standardFreightRequestByVendor().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardFreightRequestByVendor().getDestination()))
	            .andExpect(jsonPath("$.driverId").exists())
	            .andExpect(jsonPath("$.availableTime").value(standardFreightRequestByVendor().getAvailableTime()))
	            .andExpect(jsonPath("$.pickupRangeInKm").value(standardFreightRequestByVendor().getPickupRangeInKm()))
	            .andExpect(jsonPath("$.updatedBy").value(standardFreightRequestByVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.availableDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists())
	            ;
				
		
		 		
	
	}
	

	@Test
	public void getById() throws Exception
	{
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor customer=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());
		
		this.mockMvc.perform(
	            get("/request/freightRequestByVendor/getById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isFound())
	            .andExpect(jsonPath("$.vehicleRegistrationNumber").value(standardFreightRequestByVendor().getVehicleRegistrationNumber()))
	            .andExpect(jsonPath("$.source").value(standardFreightRequestByVendor().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardFreightRequestByVendor().getDestination()))
	            .andExpect(jsonPath("$.driverId").exists())
	            .andExpect(jsonPath("$.availableTime").value(standardFreightRequestByVendor().getAvailableTime()))
	            .andExpect(jsonPath("$.pickupRangeInKm").value(standardFreightRequestByVendor().getPickupRangeInKm()))
	            .andExpect(jsonPath("$.updatedBy").value(standardFreightRequestByVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.availableDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	           
	            
	    
		
	}
	
	@Test
	public void deleteById() throws Exception
	{
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor customer=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());
		
		this.mockMvc.perform(
	            get("/request/freightRequestByVendor/deleteById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	    
	}
	
	@Test
	public void count() throws Exception
	{
		
		this.mockMvc.perform(
	            get("/request/freightRequestByVendor/count"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
	    
	}
	
	@Test
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
	            get("/request/freightRequestByVendor/clearTestData"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void findByVendor() throws Exception
	{
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor customer=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());
		
		this.mockMvc.perform(
	            get("/request/freightRequestByVendor/findByVendorId/"+customer.getVendorId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.[0].vehicleRegistrationNumber").value(standardFreightRequestByVendor().getVehicleRegistrationNumber()))
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByVendor().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByVendor().getDestination()))
	            .andExpect(jsonPath("$.[0].driverId").exists())
	            .andExpect(jsonPath("$.[0].availableTime").value(standardFreightRequestByVendor().getAvailableTime()))
	            .andExpect(jsonPath("$.[0].pickupRangeInKm").value(standardFreightRequestByVendor().getPickupRangeInKm()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardFreightRequestByVendor().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].availableDate").exists())
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists());
	           
	    
	}
	
	@Test
	public void getMatchingVendorReqForCustomerReq() throws Exception
	{
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor savedEntity=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());
		
		vehicleDetailsService.addVehicle(standardVehicleDetailsOpen307());
		
		freightRequestByVendorService.newRequest(standardFreightRequestByVendorOpen307());
		
		vehicleDetailsService.addVehicle(standardVehicleDetailsClosed());
		
		freightRequestByVendorService.newRequest(standardFreightRequestByVendorClosed());
		
		vehicleDetailsService.addVehicle(standardVehicleDetailsFlexible());
		
		freightRequestByVendorService.newRequest(standardFreightRequestByVendorFlexible());
		
		FreightRequestByCustomer freightRequestByCustomer=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad());

		
		this.mockMvc.perform(
	            post("/request/freightRequestByVendor/getMatchingVendorReqForCustomerReq")
	                    .content(standardJsonFreightRequestByCustomer(freightRequestByCustomer))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());/*
	            .andExpect(jsonPath("$.[0].vehicleRegistrationNumber").value(standardFreightRequestByVendor().getVehicleRegistrationNumber()))
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByVendor().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByVendor().getDestination()));
	*/
		
	}
}
