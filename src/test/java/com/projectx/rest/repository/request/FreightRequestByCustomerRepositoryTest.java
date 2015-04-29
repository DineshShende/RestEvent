package com.projectx.rest.repository.request;

import static org.junit.Assert.*;
import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.VehicleDetailsDataFixtures.standardVehicleDetails;
import static com.projectx.rest.fixture.request.FreightRequestByCustomerDataFixture.*;
import static com.projectx.rest.fixture.request.FreightRequestByVendorDataFixture.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.completeregister.VehicleDetailsRepository;
import com.projectx.rest.repository.completeregister.VendorDetailsRepository;


@RunWith(SpringJUnit4ClassRunner.class)   
@SpringApplicationConfiguration(classes = Application.class)   
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)

public class FreightRequestByCustomerRepositoryTest {
 
	@Autowired
	FreightRequestByCustomerRepository  freightRequestByCustomerRepository;
	
	@Autowired
	FreightRequestByVendorRepository freightRequestByVendorRepository;
	
	@Autowired
	VehicleDetailsRepository vehicleDetailsRepository;
	
	@Value("${FREIGHTALLOCATIONSTATUS_RESPONDED}")
	private String FREIGHTALLOCATIONSTATUS_RESPONDED;
	
	@Value("${FREIGHTALLOCATIONSTATUS_NEW}")
	private String FREIGHTALLOCATIONSTATUS_NEW;
			
	@Value("${FREIGHTALLOCATIONSTATUS_BOOKED}")
	private String FREIGHTALLOCATIONSTATUS_BOOKED;
			

	@Before
	@After
	public void clearData()
	{
		
		freightRequestByCustomerRepository.clearTestData();
		freightRequestByVendorRepository.clearTestData();
		vehicleDetailsRepository.clearTestData();
		
		
	}
	
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveAndGet()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
	}
	
	
	@Test
	public void update()
	{
				
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		savedEntity.setBodyType(CREQ_BODYTYPE_CLOSED);
		
		freightRequestByCustomerRepository.save(savedEntity);
		
		assertEquals(savedEntity, freightRequestByCustomerRepository.getById(savedEntity.getRequestId()));
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
		
	}
	
	@Test
	public void deleteById()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
		
		freightRequestByCustomerRepository.deleteById(savedEntity.getRequestId());
		
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
	}
	
	@Test
	public void count()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
	}

	
	@Test
	public void clearTestData()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		assertEquals(1, freightRequestByCustomerRepository.count().intValue());
		
		freightRequestByCustomerRepository.clearTestData();
		
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
	}
	
	@Test
	public void findByCustomerId()
	{
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad());
		
		List<FreightRequestByCustomer> requestList=freightRequestByCustomerRepository.findByCustomerId(savedEntity.getCustomerId());
		
		assertEquals(savedEntity, requestList.get(0));
		
		assertEquals(1, requestList.size());
	}
	
	@Test
	public void getMatchingCustomerRequest ()
	{
		freightRequestByCustomerRepository.clearTestData();
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad110());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadClosedAcerReq());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoadOpenTataReq());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoad15());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenAcer());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenTata());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrand());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoBrandAndNoModel());
		
		savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerLessThanTruckLoadOpenNoModel());
		
		//FreightRequestByVendor vendorRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		
		vehicleDetailsRepository.save(standardVehicleDetails());
		
		FreightRequestByVendor testRequest=freightRequestByVendorRepository.save(standardFreightRequestByVendor());
		
		
		
		List<FreightRequestByCustomer> list=freightRequestByCustomerRepository.getMatchingCustReqForVendorReq(testRequest,REQ_STATUS);
		
		//System.out.println(list.size());
		
		assertEquals(1, list.size());
	}

	@Test
	public void updateAllocationStatus()
	{
		
		assertEquals(0, freightRequestByCustomerRepository.count().intValue());
		
		FreightRequestByCustomer savedEntity=freightRequestByCustomerRepository.save(standardFreightRequestByCustomerFullTruckLoad110());
		
		assertEquals(1, freightRequestByCustomerRepository.updateReservationStatusWithReservedFor(savedEntity.getRequestId(), 
			FREIGHTALLOCATIONSTATUS_NEW, FREIGHTALLOCATIONSTATUS_RESPONDED, 2345L,1,savedEntity.getCustomerId()).intValue());
		
		assertEquals(1, freightRequestByCustomerRepository.updateReservationStatusWithReservedFor(savedEntity.getRequestId(), 
				FREIGHTALLOCATIONSTATUS_RESPONDED, FREIGHTALLOCATIONSTATUS_BOOKED, 2345L,1,savedEntity.getCustomerId()).intValue());
	}
}
