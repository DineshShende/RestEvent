package com.projectx.rest.handlers.quickregister;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.CUST_MOBILE_TYPE_PRIMARY;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.MessageBuilder;
import com.projectx.rest.utils.MessagerSender;

@Component
@Profile(value={"Dev","Test"})

public class MobileVerificationHandler implements MobileVerificationService {

	@Autowired
	HandleCustomerVerification handleCustomerVerification; 
	
	@Autowired
	QuickRegisterService customerQuickRegisterService; 
	
	@Autowired
	AuthenticationService authenticationHandler;
	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	
	private static final Integer MAX_MOBILE_VERIFICATION_ATTEMPTS=3;
	
	@Override
	public MobileVerificationDetails createCustomerMobileVerificationEntity(
			Long customerId,Integer customerType,Long mobile,Integer mobileType) {
		
		MobileVerificationDetails mobileVerificationDetails=new MobileVerificationDetails();
		MobileVerificationDetailsKey key=new MobileVerificationDetailsKey(customerId,customerType,mobile);
		
		mobileVerificationDetails.setKey(key);
		mobileVerificationDetails.setMobileType(mobileType);
		mobileVerificationDetails.setMobilePin(handleCustomerVerification.genarateMobilePin());
		mobileVerificationDetails.setMobileVerificationAttempts(0);
		mobileVerificationDetails.setResendCount(0);
		
		
		return mobileVerificationDetails;
	}
	
	@Override
	public Boolean verifyMobilePin(Long customerId,Integer customerType,Long mobile, Integer mobilePin) {
	
		QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerId);
		MobileVerificationDetails mobileVerificationDetails=getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId,customerType, mobile);
		
		Boolean verificationStatus=false;
		
		Boolean sendPasswordStatus=true;
		
		Integer customerQuickRegisterUpdateStatus=UPDATE_SUCESS;
		Integer customerMobileVerificationDetailsStatus=UPDATE_SUCESS;
		
		if(fetchedEntity.getCustomerId()==null || mobileVerificationDetails.getKey()==null)
			return false;
		
		
		if(mobileVerificationDetails.getMobilePin().equals(mobilePin) && mobileVerificationDetails.getMobileVerificationAttempts()<MAX_MOBILE_VERIFICATION_ATTEMPTS)
		{
			fetchedEntity.setIsMobileVerified(true);
			
			verificationStatus=true;
			
			sendPasswordStatus=authenticationHandler.sendDefaultPassword(fetchedEntity, false);
			
			fetchedEntity.setUpdateTime(new Date());
			fetchedEntity.setUpdatedBy("ONLINE_CUST");
			
			customerQuickRegisterUpdateStatus=customerQuickRegisterService.updateMobileVerificationStatus(fetchedEntity.getCustomerId(), 
					fetchedEntity.getIsMobileVerified(), fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());
			
				
		}
		else
		{
			int currentAttemptCount=mobileVerificationDetails.getMobileVerificationAttempts();
			mobileVerificationDetails.setMobileVerificationAttempts(currentAttemptCount+1);
		
			customerMobileVerificationDetailsStatus=customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(fetchedEntity.getCustomerId(), 
					fetchedEntity.getCustomerType(),fetchedEntity.getMobile(),mobileVerificationDetails.getMobilePin(),
					mobileVerificationDetails.getMobileVerificationAttempts(), mobileVerificationDetails.getResendCount());
		
		}
		
		if(customerQuickRegisterUpdateStatus.equals(UPDATE_SUCESS)&&customerMobileVerificationDetailsStatus.equals(UPDATE_SUCESS) && verificationStatus && sendPasswordStatus)
			return true;
		else
			return false;
		
	}

	@Override
	public Boolean reSetMobilePin(Long customerId,Integer customerType,Long mobile)  {
	
		Integer updateStatus=new Integer(0);
		
		updateStatus=updateMobilePin(customerId, customerType,mobile);
		
		QuickRegisterEntity customer=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerId);
		MobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(customer.getCustomerId(),customer.getCustomerType(), customer.getMobile());
		
		Boolean sentStatus=messagerSender
				.sendPinSMS(customer.getFirstName(), customer.getLastName(), customer.getMobile(), mobileVerificationDetails.getMobilePin());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus)
			return true;
		else
			return false;
	}


	@Override
	public Integer updateMobilePin(Long customerId,Integer customerType,Long mobile) {
		
		Integer mobilePin=handleCustomerVerification.genarateMobilePin();

		return customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(customerId,customerType, mobile, mobilePin, 0, 0);
	}
	
	@Override
	public Boolean reSendMobilePin(Long customerId,Integer customerType, Long mobile) {
		
		Integer updateStatus=new Integer(0);
		Boolean sentStatus=false;
		
		QuickRegisterEntity customer=customerQuickRegisterService.getCustomerQuickRegisterEntityByCustomerId(customerId);
		MobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.getMobileVerificationDetailsByCustomerIdTypeAndMobile(customer.getCustomerId(),customer.getCustomerType(), customer.getMobile());
		
		updateStatus=customerMobileVerificationDetailsRepository.incrementResendCount(customerId,customerType, mobile);
	
		
		if(updateStatus.equals(UPDATE_SUCESS))
			sentStatus=messagerSender
				.sendPinSMS(customer.getFirstName(), customer.getLastName(), customer.getMobile(), mobileVerificationDetails.getMobilePin());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus)
			return true;
		else
			return false;
	}
	

	@Override
	public MobileVerificationDetails saveCustomerMobileVerificationDetails(
			MobileVerificationDetails mobileVerificationDetails) {
		
		MobileVerificationDetails verificationDetails=customerMobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		return verificationDetails;
	}

	@Override
	public MobileVerificationDetails getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(
			Long customerId,Integer customerType, Long mobile) {
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository.
					getMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId,customerType,mobile);
		return fetchedMobileVerificationDetails;
	}

	@Override
	public Boolean clearTestData() {

		return customerMobileVerificationDetailsRepository.clearTestData();
	}

	@Override
	public Boolean deleteByKey(MobileVerificationDetailsKey key) {

		Boolean deletionStatus=customerMobileVerificationDetailsRepository.delete(key);
		
		return deletionStatus;
	}

	@Override
	public Integer count() {
		
		return customerMobileVerificationDetailsRepository.count().intValue();
	}




}
