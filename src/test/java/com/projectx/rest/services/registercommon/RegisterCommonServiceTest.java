package com.projectx.rest.services.registercommon;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.CustomerQuickRegisterEmailMobileVerificationEntity;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.registercommon.ForgetPasswordEntity;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.QuickRegisterService;

import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;
import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.completeregister.VendorDetailsDataFixture.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class RegisterCommonServiceTest {

	
	@Autowired
	RegisterCommonService registerCommonService; 
	
	@Autowired
	TransactionalUpdatesService transactionalUpdatesService;
	
	@Autowired
	QuickRegisterService quickRegisterService;
	
	@Autowired
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Value("${ACTOR_ENTITY_SELF_WEB}")
	private Long ACTOR_ENTITY_SELF_WEB;
	
	@Before
	public void setUp() {
		
		customerDetailsService.clearTestData();
		vendorDetailsService.clearTestData();
		quickRegisterService.clearDataForTesting();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void forgetPasswordInQuickRegisterPhaseWithEmail()
	{
		
		try{
		
			registerCommonService.forgetPassword(CUST_EMAIL,CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity quickRegisterEntity=
				transactionalUpdatesService.saveNewQuickRegisterEntity(standardEmailCustomer());
		
		QuickRegisterEntity entity=quickRegisterEntity.getCustomerQuickRegisterEntity();
		
		ForgetPasswordEntity forgetPasswordEntity=registerCommonService.forgetPassword(entity.getEmail(),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(entity.getCustomerId(), forgetPasswordEntity.getCustomerId());
		assertEquals(entity.getCustomerType(), forgetPasswordEntity.getCustomerType());
		assertEquals(entity.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(entity.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(entity.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(entity.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
	}
	
	@Test
	public void forgetPasswordInQuickRegisterPhaseWithMobile()
	{
		
		try{
		
			registerCommonService.forgetPassword(Long.toString(CUST_MOBILE),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity quickRegisterEntity=
				transactionalUpdatesService.saveNewQuickRegisterEntity(standardMobileCustomer());
		
		QuickRegisterEntity entity=quickRegisterEntity.getCustomerQuickRegisterEntity();
		
		ForgetPasswordEntity forgetPasswordEntity=registerCommonService.forgetPassword(Long.toString(entity.getMobile()),CUST_UPDATED_BY,
				ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(entity.getCustomerId(), forgetPasswordEntity.getCustomerId());
		assertEquals(entity.getCustomerType(), forgetPasswordEntity.getCustomerType());
		assertEquals(entity.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(entity.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(entity.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(entity.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
	}
	
	@Test
	public void forgetPasswordInQuickRegisterPhaseWithEmailMobile()
	{
		
		try{
			
			registerCommonService.forgetPassword(CUST_EMAIL,CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		try{
		
			registerCommonService.forgetPassword(Long.toString(CUST_MOBILE),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity quickRegisterEntity=
				transactionalUpdatesService.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		
		QuickRegisterEntity entity=quickRegisterEntity.getCustomerQuickRegisterEntity();
		
		ForgetPasswordEntity forgetPasswordEntity=registerCommonService.forgetPassword(Long.toString(entity.getMobile()),CUST_UPDATED_BY,
				ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(entity.getCustomerId(), forgetPasswordEntity.getCustomerId());
		assertEquals(entity.getCustomerType(), forgetPasswordEntity.getCustomerType());
		assertEquals(entity.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(entity.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(entity.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(entity.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
		forgetPasswordEntity=registerCommonService.forgetPassword(entity.getEmail(),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(entity.getCustomerId(), forgetPasswordEntity.getCustomerId());
		assertEquals(entity.getCustomerType(), forgetPasswordEntity.getCustomerType());
		assertEquals(entity.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(entity.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(entity.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(entity.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
	}
	
	@Test
	public void forgotPasswordWithCustomerDetails()
	{
		try{
			
			registerCommonService.forgetPassword(CUST_EMAIL,CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		try{
		
			registerCommonService.forgetPassword(Long.toString(CUST_MOBILE),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity quickRegisterEntity=
				transactionalUpdatesService.saveNewQuickRegisterEntity(standardEmailMobileCustomer());
		
		QuickRegisterEntity entity=quickRegisterEntity.getCustomerQuickRegisterEntity();
		
		CustomerDetails customerDetails=transactionalUpdatesService.deleteQuickRegisterEntityCreateDetails(entity).getCustomerDetails();
		
		ForgetPasswordEntity forgetPasswordEntity=null;
		
		forgetPasswordEntity=registerCommonService.forgetPassword(entity.getEmail(),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(customerDetails.getCustomerId(), forgetPasswordEntity.getCustomerId());
		assertEquals(ENTITY_TYPE_CUSTOMER, forgetPasswordEntity.getCustomerType());
		assertEquals(customerDetails.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(customerDetails.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(customerDetails.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(customerDetails.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
		forgetPasswordEntity=registerCommonService.forgetPassword(Long.toString(entity.getMobile()),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(customerDetails.getCustomerId(), forgetPasswordEntity.getCustomerId());
		assertEquals(ENTITY_TYPE_CUSTOMER, forgetPasswordEntity.getCustomerType());
		assertEquals(customerDetails.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(customerDetails.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(customerDetails.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(customerDetails.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
	}
	
	@Test
	public void forgotPasswordWithVendorDetails()
	{
		try{
			
			registerCommonService.forgetPassword(CUST_EMAIL,CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		try{
		
			registerCommonService.forgetPassword(Long.toString(CUST_MOBILE),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
			
			assertEquals(0, 1);
		
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
		
		CustomerQuickRegisterEmailMobileVerificationEntity quickRegisterEntity=
				transactionalUpdatesService.saveNewQuickRegisterEntity(standardEmailMobileVendor());
		
		QuickRegisterEntity entity=quickRegisterEntity.getCustomerQuickRegisterEntity();
		
		VendorDetails vendorDetails=transactionalUpdatesService.deleteQuickRegisterEntityCreateDetails(entity).getVendorDetails();
		
		ForgetPasswordEntity forgetPasswordEntity=null;
		
		forgetPasswordEntity=registerCommonService.forgetPassword(entity.getEmail(),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(vendorDetails.getVendorId(), forgetPasswordEntity.getCustomerId());
		assertEquals(ENTITY_TYPE_VENDOR, forgetPasswordEntity.getCustomerType());
		assertEquals(vendorDetails.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(vendorDetails.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(vendorDetails.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(vendorDetails.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());
		
		forgetPasswordEntity=registerCommonService.forgetPassword(Long.toString(entity.getMobile()),CUST_UPDATED_BY,ACTOR_ENTITY_SELF_WEB);
		
		assertEquals(vendorDetails.getVendorId(), forgetPasswordEntity.getCustomerId());
		assertEquals(ENTITY_TYPE_VENDOR, forgetPasswordEntity.getCustomerType());
		assertEquals(vendorDetails.getEmail(), forgetPasswordEntity.getEmail());
		assertEquals(vendorDetails.getMobile(), forgetPasswordEntity.getMobile());
		assertEquals(vendorDetails.getIsEmailVerified(), forgetPasswordEntity.getIsEmailVerified());
		assertEquals(vendorDetails.getIsMobileVerified(), forgetPasswordEntity.getIsMobileVerified());

	}

}
