package com.projectx.rest.services.handshake;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileCustomer;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.standardEmailMobileVendor;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.standardFreightRequestByCustomerFullTruckLoad;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.standardFreightRequestByVendor;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.handshake.DealDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VehicleDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.services.request.FreightRequestByCustomerService;
import com.projectx.rest.services.request.FreightRequestByVendorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class DealServiceTest {

	@Autowired
	DealService dealService;
	
	@Autowired
	FreightRequestByVendorService freightRequestByVendorService;
	
	@Autowired
	FreightRequestByCustomerService freightRequestByCustomerService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	VehicleDetailsService vehicleDetailsService;
	
	
	@Before
	public void setUp()
	{
		dealService.clearTestData();
		freightRequestByVendorService.clearTestData();
		freightRequestByCustomerService.clearTestData();
		customerDetailsService.clearTestData();
		vendorDetailsService.clearTestData();
		quickRegisterService.clearDataForTesting();
		vehicleDetailsService.clearTestData();
		
	}
	
	@Test
	public void environmentTest() {
		
		

		
	}
	
	@Test
	public void triggerDeal()
	{
		QuickRegisterEntity savedQuickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		CustomerDetails customer=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileVendor());
		
		VendorDetails vendor=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		
		FreightRequestByCustomer customerRequest=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad(customer.getCustomerId()));
		
		FreightRequestByVendor vendorRequest=freightRequestByVendorService.newRequest(standardFreightRequestByVendor(vendor.getVendorId()));
		
		assertEquals(0, dealService.count().intValue());
		
		DealDetails deal=null;
		
		try{
			
			deal=dealService.getByCustomerRequestId(customerRequest.getRequestId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		try{
			
			deal=dealService.getByVendorRequestId(vendorRequest.getRequestId());
			
			assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		deal=dealService.triggerDeal(customerRequest.getRequestId(), vendorRequest.getRequestId(), "ONLINE", 100, "CUST_ONLINE");

		assertEquals(1, dealService.count().intValue());
		
		assertEquals(deal, dealService.getByCustomerRequestId(customerRequest.getRequestId()));
		
		assertEquals(deal, dealService.getByVendorRequestId(vendorRequest.getRequestId()));
		
	}
	
	@Test
	public void exchangeContacts()
	{
		QuickRegisterEntity savedQuickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileCustomer());
		
		CustomerDetails customer=customerDetailsService.createCustomerDetailsFromQuickRegisterEntity(savedQuickRegisterEntity);

		QuickRegisterEntity quickRegisterEntity=quickRegisterService.saveCustomerQuickRegisterEntity(standardEmailMobileVendor());
		
		VendorDetails vendor=vendorDetailsService.createCustomerDetailsFromQuickRegisterEntity(quickRegisterEntity);
		
		vehicleDetailsService.addVehicle(standardVehicleDetails());
		
		
		FreightRequestByCustomer customerRequest=freightRequestByCustomerService.newRequest(standardFreightRequestByCustomerFullTruckLoad(customer.getCustomerId()));
		
		FreightRequestByVendor vendorRequest=freightRequestByVendorService.newRequest(standardFreightRequestByVendor(vendor.getVendorId()));
		
		DealDetails deal=dealService.triggerDeal(customerRequest.getRequestId(), vendorRequest.getRequestId(), "ONLINE", 100, "CUST_ONLINE");
		
		assertTrue(dealService.exchangeContact(deal.getDealId(), customerRequest.getRequestId(), vendorRequest.getRequestId()));
		
	}

}
