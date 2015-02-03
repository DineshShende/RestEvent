package com.projectx.rest.controller.result;

import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.standardFreightRequestByCustomerFullTruckLoad;
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
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.services.request.FreightRequestByVendorService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

@ActiveProfiles(value="Prod")
public class FreightRequestByVendorControllerWACTest {

	@Autowired
	private WebApplicationContext  wac;
	
	MockMvc mockMvc;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Before
	public void setUp() throws Exception
	{
		this.mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
		freightRequestByVendorService.clearTestData();
	
	}
	
	@Test
	public void environmentTest()
	{
		
	}
	
	@Test
	public void save() throws Exception
	{
		this.mockMvc.perform(
	            post("/request/freightRequestByVendor")
	                    .content(stanardJsonFreightRequestByVendor(standardFreightRequestByVendor()))
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .accept(MediaType.APPLICATION_JSON))
	            .andDo(print())
	            .andExpect(status().isOk())
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
		FreightRequestByVendor customer=freightRequestByVendorService.newRequest(standardFreightRequestByVendor());
		
		this.mockMvc.perform(
	            get("/request/freightRequestByVendor/getById/"+customer.getRequestId()))
	            
	            .andDo(print())
	            .andExpect(status().isOk())
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
}
