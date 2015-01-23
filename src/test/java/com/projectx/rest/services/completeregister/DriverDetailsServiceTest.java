package com.projectx.rest.services.completeregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.services.quickregister.MobileVerificationService;

import static com.projectx.rest.fixture.completeregister.DriverDetailsDataFixtures.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(value="Dev")
public class DriverDetailsServiceTest {

	@Autowired
	DriverDetailsService driverDetailsService;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	private Integer ENTITY_TYPE_DRIVER=3;
	
	private Integer ENTITY_TYPE_PRIMARY=1;
	
	@Before
	public void environmentTest()
	{
		driverDetailsService.clearTestData();
		mobileVerificationService.clearTestData();
	}

	@Test
	public void saveAndGetById()
	{
		assertEquals(0, driverDetailsService.count().intValue());
		
		DriverDetails savedEntity=driverDetailsService.addDriver(standardDriverDetails());
		
		assertEquals(savedEntity, driverDetailsService.getDriverById(savedEntity.getDriverId()));
		
		assertEquals(1, driverDetailsService.count().intValue());
	}

	
	@Test
	public void updateAndVerify()
	{
		assertEquals(0, driverDetailsService.count().intValue());
		
		DriverDetails savedEntity=driverDetailsService.addDriver(standardDriverDetails());
		
		DriverDetails updatedEntity=driverDetailsService.updateDriver(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()));
		
		assertNotEquals(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile(),
				updatedEntity.getMobile());
		
		mobileVerificationService.reSendMobilePin(savedEntity.getDriverId(),ENTITY_TYPE_DRIVER,ENTITY_TYPE_PRIMARY);
		
		MobileVerificationDetails mobileVerificationDetails
			=mobileVerificationService.getByEntityIdTypeAndMobileType(savedEntity.getDriverId(),ENTITY_TYPE_DRIVER,ENTITY_TYPE_PRIMARY);
		
		
		
		assertTrue(mobileVerificationService
			.verifyMobilePinUpdateStatusAndSendPassword(savedEntity.getDriverId(),ENTITY_TYPE_DRIVER,ENTITY_TYPE_PRIMARY, mobileVerificationDetails.getMobilePin()));
		
		assertEquals(standardDriverDetailsNewMobileAndFirstName(savedEntity.getDriverId()).getMobile(),
				driverDetailsService.getDriverById(savedEntity.getDriverId()).getMobile());

	}

}
