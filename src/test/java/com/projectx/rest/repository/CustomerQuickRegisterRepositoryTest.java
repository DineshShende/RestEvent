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
	CustomerQuickRegisterRepository customerQuickRegisterMemRepository;
	
	@Before
	public void clearExistingRecords()
	{
		customerQuickRegisterMemRepository.clearCustomerQuickRegister();
	}
	
	
	@Test
	public void countByEmailMobileNewEmailMobileCustomer() throws Exception {
		
		
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		
	}
	
	

	@Test
	public void countByEmailMobileNewEmailCustomer() throws Exception {
		
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardEmailCustomer());
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		
	}

	
	@Test
	public void countByEmailMobileNewMobileCustomer() throws Exception {
		
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.countByEmail(CUST_EMAIL));
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.countByMobile(CUST_MOBILE));
		
		
	}
	
	@Test
	public void findAllWithEmailMobileCustomer() throws Exception {
		
		List<CustomerQuickRegisterEntity> customerList=customerQuickRegisterMemRepository.findAll();
		
		assertEquals(0,customerList.size());
		
		customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());
		
		customerList=customerQuickRegisterMemRepository.findAll();
		
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
		
	
		assertNull(customerQuickRegisterMemRepository.findByCustomerId(CUST_ID).getCustomerId());
		
		CustomerQuickRegisterEntity savedCustomer=customerQuickRegisterMemRepository.save(standardEmailMobileCustomer());

		assertEquals(savedCustomer,customerQuickRegisterMemRepository.findByCustomerId(savedCustomer.getCustomerId()));
			
		
	}
	
	
	@Test
	public void updateStatusAndMobileVerificationAttemptsByCustomerId() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(CUST_ID, STATUS_MOBILE_VERFIED,CUST_LAST_STATUS_CHANGE_TIME,CUST_MOBILE_VERIFICATION_ATTEMPTS));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_STATUS_MOBILE,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getStatus());
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.updateStatusAndMobileVerificationAttemptsByCustomerId(savedEntity.getCustomerId(), STATUS_MOBILE_VERFIED,CUST_LAST_STATUS_CHANGE_TIME,CUST_MOBILE_VERIFICATION_ATTEMPTS));
		
		assertEquals(STATUS_MOBILE_VERFIED,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getStatus());
	}
	
	

	@Test
	public void updateEmailHash() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.updateEmailHash(CUST_ID, CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardEmailCustomer());
		
		assertEquals(CUST_EMAILHASH,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getEmailHash());
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.updateEmailHash(savedEntity.getCustomerId(), CUST_EMAILHASH_UPDATED,CUST_EMAIL_HASH_SENT_TIME));
		
		assertEquals(CUST_EMAILHASH_UPDATED,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getEmailHash());
	}
	

		
	@Test
	public void updateMobilePin() throws Exception
	{
		assertEquals(new Integer(0), customerQuickRegisterMemRepository.updateMobilePin(CUST_ID, CUST_MOBILEPIN_UPDATED,CUST_MOBILE_PIN_SENT_TIME));
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(CUST_MOBILEPIN,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getMobilePin());
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.updateMobilePin(savedEntity.getCustomerId(), CUST_MOBILEPIN_UPDATED,CUST_MOBILE_PIN_SENT_TIME));
		
		assertEquals(CUST_MOBILEPIN_UPDATED,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getMobilePin());
	}
	
	@Test
	public void updatePassword() throws Exception
	{
		assertEquals(0, customerQuickRegisterMemRepository.updatePassword(CUST_ID, CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED).intValue());
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(null,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getPassword());
		
		assertEquals(null,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getPasswordType());
		
		assertEquals(new Integer(1), customerQuickRegisterMemRepository.updatePassword(savedEntity.getCustomerId(),CUST_PASSWORD_CHANGED, CUST_PASSWORD_TYPE_CHANGED));
		
		assertEquals(CUST_PASSWORD_CHANGED,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getPassword());
		
		assertEquals(CUST_PASSWORD_TYPE_CHANGED,customerQuickRegisterMemRepository.findByCustomerId(savedEntity.getCustomerId()).getPasswordType());
		
	}
	

	@Test
	public void updateEmailHashAndMobilePinSentTime() throws Exception
	{
		assertEquals(0, customerQuickRegisterMemRepository.updateEmailHashAndMobilePinSentTime(CUST_ID, CUST_EMAIL_HASH_SENT_TIME, CUST_MOBILE_PIN_SENT_TIME).intValue());
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterMemRepository.updateEmailHashAndMobilePinSentTime(savedEntity.getCustomerId(), CUST_EMAIL_HASH_SENT_TIME, CUST_MOBILE_PIN_SENT_TIME).intValue());
	}
	
	
	@Test
	public void clearData() throws Exception
	{
		assertEquals(0, customerQuickRegisterMemRepository.findAll().size());
		
		CustomerQuickRegisterEntity savedEntity=customerQuickRegisterMemRepository.save(standardMobileCustomer());
		
		assertEquals(1, customerQuickRegisterMemRepository.findAll().size());
		
		customerQuickRegisterMemRepository.clearCustomerQuickRegister();
		
		assertEquals(0, customerQuickRegisterMemRepository.findAll().size());
		
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
