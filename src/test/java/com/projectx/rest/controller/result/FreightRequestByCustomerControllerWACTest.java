package com.projectx.rest.controller.result;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;
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
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.services.completeregister.VehicleDetailsService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
public class FreightRequestByCustomerControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService; 
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		
		
	
	}
	
	@Before
	public void cleanUp()
	{
		freightRequestByCustomerService.clearTestData();
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
		this.mockMvc.perform(
	            post("/request/freightRequestByCustomer")
	                    .content(standardJsonFreightRequestByCustomer(standardFreightRequestByCustomerFullTruckLoad()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()))
	            .andExpect(jsonPath("$.isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	            .andExpect(jsonPath("$.isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.length").doesNotExist())
	            .andExpect(jsonPath("$.width").doesNotExist())
	            .andExpect(jsonPath("$.height").doesNotExist())
	            .andExpect(jsonPath("$.vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.pickupDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	    
	
	}
	
	
	
	@Test
	public void getById() throws Exception
	{
		FreightRequestByCustomer customer=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad());
		
		this.mockMvc.perform(
	            get("/request/freightRequestByCustomer/getById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()))
	            .andExpect(jsonPath("$.isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	            .andExpect(jsonPath("$.isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.length").doesNotExist())
	            .andExpect(jsonPath("$.width").doesNotExist())
	            .andExpect(jsonPath("$.height").doesNotExist())
	            .andExpect(jsonPath("$.vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.pickupDate").exists())
	            .andExpect(jsonPath("$.insertTime").exists())
	            .andExpect(jsonPath("$.updateTime").exists());
	    
	            
	    
		
	}
	
	@Test
	public void deleteById() throws Exception
	{
		FreightRequestByCustomer customer=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad());
		
		this.mockMvc.perform(
	            get("/request/freightRequestByCustomer/deleteById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
	    
	}
	
	@Test
	public void count() throws Exception
	{
		
		this.mockMvc.perform(
	            get("/request/freightRequestByCustomer/count"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("0"));
	    
	}
	
	@Test
	public void clearTestData() throws Exception
	{
		this.mockMvc.perform(
	            get("/request/freightRequestByCustomer/clearTestData"))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            .andExpect(content().string("true"));
		
	}
	
	@Test
	public void findByCustomerId() throws Exception
	{
		FreightRequestByCustomer customer=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad());
		
		
		
		this.mockMvc.perform(
	            get("/request/freightRequestByCustomer/getByCustomerId/"+customer.getCustomerId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
	            
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.[0].noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()))
	            .andExpect(jsonPath("$.[0].isFullTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsFullTruckLoad()))
	            .andExpect(jsonPath("$.[0].isLessThanTruckLoad").value(standardFreightRequestByCustomerFullTruckLoad().getIsLessThanTruckLoad()))
	            .andExpect(jsonPath("$.[0].bodyType").value(standardFreightRequestByCustomerFullTruckLoad().getBodyType()))
	            .andExpect(jsonPath("$.[0].length").doesNotExist())
	            .andExpect(jsonPath("$.[0].width").doesNotExist())
	            .andExpect(jsonPath("$.[0].height").doesNotExist())
	            .andExpect(jsonPath("$.[0].vehicleBrand").value(standardFreightRequestByCustomerFullTruckLoad().getVehicleBrand()))
	            .andExpect(jsonPath("$.[0].model").value(standardFreightRequestByCustomerFullTruckLoad().getModel()))
	            .andExpect(jsonPath("$.[0].commodity").value(standardFreightRequestByCustomerFullTruckLoad().getCommodity()))
	            .andExpect(jsonPath("$.[0].pickupTime").value(standardFreightRequestByCustomerFullTruckLoad().getPickupTime()))
	            .andExpect(jsonPath("$.[0].updatedBy").value(standardFreightRequestByCustomerFullTruckLoad().getUpdatedBy()))
	            .andExpect(jsonPath("$.[0].pickupDate").exists())
	            .andExpect(jsonPath("$.[0].insertTime").exists())
	            .andExpect(jsonPath("$.[0].updateTime").exists());
	            
	    
	}
	
	@Test
	public void getMatchingCustomerReqForVendorReq() throws Exception
	{
		
		
		freightRequestByCustomerService.clearTestData();
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad110());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoadOpenTataReq());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerLessThanTruckLoad15());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenTata());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel());
		
		savedEntity=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel());
		
		//FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor testRequest=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());

		
		this.mockMvc.perform(
	            post("/request/freightRequestByCustomer/getMatchingCustomerReqForVendorReq")
	                    .content(stanardJsonFreightRequestByVendor(testRequest))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk());/*
	            .andExpect(jsonPath("$.[0].source").value(standardFreightRequestByCustomerFullTruckLoad().getSource()))
	            .andExpect(jsonPath("$.[0].destination").value(standardFreightRequestByCustomerFullTruckLoad().getDestination()))
	            .andExpect(jsonPath("$.[0].noOfVehicles").value(standardFreightRequestByCustomerFullTruckLoad().getNoOfVehicles()));
				*/
	    
		
		
	}
	
}
