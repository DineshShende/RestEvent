package com.projectx.rest.repository;


import static com.projectx.rest.fixture.CustomerQuickRegisterDataFixture.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles("Dev")
public class CustomerQuickRegisterRepositoryTest {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;
	
	@Before
	public void clearExistingRecords()
	{
		customerQuickRegisterRepository.clearCustomerQuickRegister();
	}
	
/*
	@Test
	public void saveCustomer() throws Exception
	{
		CustomerQuickRegisterEntity firstEntity=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertNotNull(firstEntity);
		
		CustomerQuickRegisterEntity secondEntity=customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertNull(secondEntity);
	}
	
*/	
	
	@Test
	public void countByEmailMobileNewEmailMobileCustomer() throws Exception {
		
		
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		
	}
	
	

	@Test
	public void countByEmailMobileNewEmailCustomer() throws Exception {
		
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		
	}

	
	@Test
	public void countByEmailMobileNewMobileCustomer() throws Exception {
		
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(new Integer(0), customerQuickRegisterRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.countByMobile(CUST_MOBILE));
		
		
	}
	
	@Test
	public void findAllWithEmailMobileCustomer() throws Exception {
		
		List<CustomerQuickRegisterEntity> customerList=customerQuickRegisterRepository.findAll();
		
		assertEquals(0,customerList.size());
		
		customerQuickRegisterRepository.save(standardEmailMobileCustomer());
		
		customerList=customerQuickRegisterRepository.findAll();
		
		assertEquals(1,customerList.size());
		
		CustomerQuickRegisterEntity customer=customerList.get(0);
		
		assertEquals(CUST_FIRSTNAME, customer.getFirstName());
		assertEquals(CUST_LASTNAME, customerList.get(0).getLastName());
		assertEquals(CUST_EMAIL, customerList.get(0).getEmail());
		assertEquals(CUST_MOBILE, customerList.get(0).getMobile());
		assertEquals(CUST_PIN, customerList.get(0).getPin());
		assertEquals(CUST_STATUS_EMAILMOBILE, customerList.get(0).getStatus());
		assertEquals(CUST_MOBILEPIN, customerList.get(0).getMobilePin());
		assertEquals(CUST_EMAILHASH, customerList.get(0).getEmailHash());
		
	}
	

	
	@Test
	public void findByCustomerIdWithEmailMobileCustomer() throws Exception {
		
	
		assertNull(customerQuickRegisterRepository.findByCustomerId(CUST_ID).getCustomerId());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterRepository.save(standardEmailMobileCustomer());

		assertEquals(savedCustomer,customerQuickRegisterRepository.findByCustomerId(savedCustomer.getCustomerId()));
			
		
	}
	
	
	@Test
	public void updateStatusAndMobileVerificationAttemptsByCustomerId() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(CUST_ID, STATUS_MOBILE_VERFIED,CUST_LAST_STATUS_CHANGE_TIME,CUST_MOBILE_VERIFICATION_ATTEMPTS));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getStatus());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(savedEntity.getCustomerId(), STATUS_MOBILE_VERFIED,CUST_LAST_STATUS_CHANGE_TIME,CUST_MOBILE_VERIFICATION_ATTEMPTS));
		
		assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getStatus());
	}
	
	

	@Test
	public void updateEmailHash() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateEmailHash(CUST_ID, CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardEmailCustomer());
		
		assertEquals(CUST_EMAILHASH,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getEmailHash());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME));
		
		assertEquals(CUST_EMAILHASH_UPDATED,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getEmailHash());
	}
	

		
	@Test
	public void updateMobilePin() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterRepository.updateMobilePin(CUST_ID, CUST_MOBILEPIN_UPDATED,CUST_MOBILE_PIN_SENT_TIME));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_MOBILEPIN,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getMobilePin());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updateMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN_UPDATED,CUST_MOBILE_PIN_SENT_TIME));
		
		assertEquals(CUST_MOBILEPIN_UPDATED,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getMobilePin());
	}
	
	@Test
	public void updatePassword() throws Exception
	{
		assertEquals(0, customerQuickRegisterRepository.updatePassword(CUST_ID, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED).intValue());
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(null,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getPassword());
		
		assertEquals(null,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getPasswordType());
		
		assertEquals(new Integer(1), customerQuickRegisterRepository.updatePassword(savedEntity.getCustomerId(),CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED));
		
		assertEquals(CUST_PASSWORD_CHANGED,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_CHANGED,customerQuickRegisterRepository.findByCustomerId(savedEntity.getCustomerId()).getPasswordType());
		
	}
	

	@Test
	public void updateEmailHashAndMobilePinSentTime() throws Exception
	{
		assertEquals(0, customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(CUST_ID, CUST_EMAIL_HASH_SENT_TIME, CUST_MOBILE_PIN_SENT_TIME).intValue());
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.updateEmailHashAndMobilePinSentTime(savedEntity.getCustomerId(), CUST_EMAIL_HASH_SENT_TIME, CUST_MOBILE_PIN_SENT_TIME).intValue());
	}
	
	
	@Test
	public void clearData() throws Exception
	{
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterRepository.findAll().size());
		
		customerQuickRegisterRepository.clearCustomerQuickRegister();
		
		assertEquals(0, customerQuickRegisterRepository.findAll().size());
		
	}
	
	
	/*
		
	@Test
	public void verifyEmailHash() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.verifyEmailHash(CUST_ID, CUST_EMAILHASH_UPDATED));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.verifyEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH_UPDATED));
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.updateEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH_UPDATED));
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.verifyEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH_UPDATED));
		
	}
	
	
	
	
	@Test
	public void verifyMobilePin() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.verifyMobilePin(CUST_ID, CUST_MOBILEPIN_UPDATED));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.verifyMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN_UPDATED));
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.updateMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN_UPDATED));
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.verifyMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN_UPDATED));
	}
	
*/

	
	/*
	@Test
	public void getStatusByCustomerIdWithEmailCustomer() throws Exception
	{
		assertNull(CUST_STATUS_EMAIL,customerQuickRegisterMemRepository.getStatusByCustomerId(CUST_ID));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardEmailCustomer());
				
		assertEquals(CUST_STATUS_EMAIL,customerQuickRegisterMemRepository.getStatusByCustomerId(savedEntity.getCustomerId()));
		
		//customerQuickRegisterMemRepository.updateStatusAfterEmailVerfication(CUST_EMAIL, STATUS_EMAIL_VERFIED);
		
		//assertEquals(STATUS_EMAIL_VERFIED,customerQuickRegisterMemRepository.fetchStatusByEmail(CUST_EMAIL));
	}
*/

	
}
