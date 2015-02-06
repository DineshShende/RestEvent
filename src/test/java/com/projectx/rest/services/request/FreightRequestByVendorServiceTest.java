package com.projectx.rest.services.request;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.mvc.domain.request.FreightRequestByVendorDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.VehicleDetailsDTO;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.services.completeregister.VehicleDetailsService;

import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.*;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class FreightRequestByVendorServiceTest {

	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	@Before
	public void setUp()
	{
		freightRequestByVendorService.clearTestData();
		vehicleDetailsService.clearTestData();
		
	}
	
	
	@Test
	public void environmentTest()
	{
		
	}
/*
	@Test
	public void toFreightRequestByVendor()
	{
		VehicleDetailsDTO vehicleDetailsDTO=vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		FreightRequestByVendor freightRequestByVendor=
				freightRequestByVendorService.toFreightRequestByVendor(standardFreightRequestByVendorDTO());
		
		assertEquals(vehicleDetailsDTO, freightRequestByVendor.getVehicleDetailsId());
		
		
	}
	
	@Test
	public void toFreightRequestByVendorDTO()
	{
		FreightRequestByVendorDTO freightRequestByVendorDTO=
				freightRequestByVendorService.toFreightRequestByVendorDTO(standardFreightRequestByVendor());
		
		assertEquals(standardFreightRequestByVendorDTO(), freightRequestByVendorDTO);
	}
*/
}
