package com.projectx.rest.repository.completeregister;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.Address;
import com.projectx.rest.domain.completeregister.CustomerDetails;

import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.completeregister.AddressDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class CustomerDetailsRepositoryTest {

	@Autowired
	CustomerDetailsRepository customerDetailsCustomRepository;
	
	@Before
	public void clearTestData()
	{
		customerDetailsCustomRepository.deleteAll();
		
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	
	@Test
	public void saveCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()), savedEntity);
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}
	
	
	@Test
	public void saveAndGetCustomerDetails()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(standardCustomerDetails(standardCustomerFromQuickEntity()), customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
	}

	
	@Test
	public void copiedDataFromQuickRegisterEntityAndSaveAndUpdateAfterWards()
	{
		assertEquals(0,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerFromQuickEntity());
		
		assertEquals(standardCustomerFromQuickEntity(), 
				customerDetailsCustomRepository.findOne(savedEntity.getCustomerId()));
		
		assertEquals(1,customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails newEntityToMerge=savedEntity;
		
		newEntityToMerge.setDateOfBirth(CUST_DATE);
		newEntityToMerge.setHomeAddressId(standardCustomerDetails(standardCustomerFromQuickEntity()).getHomeAddressId());
		newEntityToMerge.setFirmAddressId(standardCustomerDetails(standardCustomerFromQuickEntity()).getFirmAddressId());
		newEntityToMerge.setLanguage(CUST_LANG);
		newEntityToMerge.setBusinessDomain(CUST_BUSINESS_DOMAIN);
		newEntityToMerge.setNameOfFirm(CUST_NAME_OF_FIRM);
		newEntityToMerge.setFirmAddressId(standardAddress());
		newEntityToMerge.setSecondaryMobile(CUST_SEC_MOBILE);
		newEntityToMerge.setSecondaryEmail(CUST_SEC_EMAIL);
		
		
		CustomerDetails mergeResult=customerDetailsCustomRepository.save(newEntityToMerge);
		
		assertEquals(newEntityToMerge, mergeResult);
	}
	
	
	/*
	@Test
	public void updateHomeAddress()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		Address updatedHomeAddress=customerDetailsCustomRepository.updateHomeAddress(standardUpdateAddressDTO()).getHomeAddressId();
		
		assertEquals(standardUpdateAddressDTO().getAddress().getAddressLine(),updatedHomeAddress.getAddressLine() );
		assertEquals(standardUpdateAddressDTO().getAddress().getCity(),updatedHomeAddress.getCity());
		assertEquals(standardUpdateAddressDTO().getAddress().getDistrict(),updatedHomeAddress.getDistrict() );
		
		
	}
	
	
	
	
	@Test
	public void updateFirmAddress()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		Address updatedFirmAddress=customerDetailsCustomRepository.updateFirmAddress(standardUpdateAddressDTO()).getFirmAddressId();
		
		assertEquals(standardUpdateAddressDTO().getAddress().getAddressLine(),updatedFirmAddress.getAddressLine() );
		assertEquals(standardUpdateAddressDTO().getAddress().getCity(),updatedFirmAddress.getCity());
		assertEquals(standardUpdateAddressDTO().getAddress().getDistrict(),updatedFirmAddress.getDistrict() );
	
		
		
	}
	*/
	
	
	@Test
	public void updateMobileVerifiedStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		
		
		assertEquals(1, customerDetailsCustomRepository
				.updateMobileVerificationStatus(new UpdateMobileVerificationStatusDTO(savedEntity.getCustomerId(),savedEntity.getMobile(), true)).intValue());
		
	}
	
	
	
	@Test
	public void updateSecondaryMobileVerificationStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository
				.updateSecondaryMobileVerificationStatus(new UpdateMobileVerificationStatusDTO(savedEntity.getCustomerId(),savedEntity.getSecondaryMobile(), true)).intValue());
		
	}
	
	@Test
	public void updateEmailVerificationStatus()
	{
		assertEquals(0, customerDetailsCustomRepository.count().intValue());
		
		CustomerDetails savedEntity=customerDetailsCustomRepository.save(standardCustomerDetails(standardCustomerFromQuickEntity()));
		
		assertEquals(1, customerDetailsCustomRepository.count().intValue());
		
		assertEquals(1, customerDetailsCustomRepository
				.updateEmailVerificationStatus(new UpdateEmailVerificationStatusDTO(savedEntity.getCustomerId(),savedEntity.getEmail(), true)).intValue());
		
	}
	

}
