package com.projectx.rest.handlers.quickregister;



import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
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
	CustomerDetailsService customerDetailsService;
	
	@Autowired
	VendorDetailsService vendorDetailsService;
	
	@Autowired
	MobileVerificationDetailsRepository customerMobileVerificationDetailsRepository;
	
	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	
	private static final Integer ENTITY_TYPE_CUSTOMER=new Integer(1);
	
	private static final Integer ENTITY_TYPE_VENDOR=new Integer(2);
	
	private static final Integer ENTITY_TYPE_QUICK_CUST=new Integer(10);
	private static final Integer ENTITY_TYPE_QUICK_VENDOR=new Integer(20);
	
	private static final Integer MAX_MOBILE_VERIFICATION_ATTEMPTS=3;
	
	@Override
	public MobileVerificationDetails createEntity(
			Long customerId,Integer customerType,Long mobile,Integer mobileType,String updatedBy) {
		
		MobileVerificationDetails mobileVerificationDetails=new MobileVerificationDetails();
		MobileVerificationDetailsKey key=new MobileVerificationDetailsKey(customerId,customerType,mobileType);
		
		mobileVerificationDetails.setKey(key);
		mobileVerificationDetails.setMobile(mobile);
		mobileVerificationDetails.setMobilePin(handleCustomerVerification.genarateMobilePin());
		mobileVerificationDetails.setMobileVerificationAttempts(0);
		mobileVerificationDetails.setResendCount(0);
		mobileVerificationDetails.setUpdatedBy(updatedBy);
		mobileVerificationDetails.setInsertTime(new Date());
		mobileVerificationDetails.setUpdateTime(new Date());
		
		return mobileVerificationDetails;
	}
	
	
	
	@Override
	public Boolean verifyMobilePinUpdateStatusAndSendPassword(Long customerId,Integer customerType,Integer mobileType, Integer mobilePin) {
	
		QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getByEntityId(customerId);
		MobileVerificationDetails mobileVerificationDetails=getByEntityIdTypeAndMobileType(customerId,customerType, mobileType);
		
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
			fetchedEntity.setUpdatedBy("CUST_ONLINE");
			
			customerQuickRegisterUpdateStatus=customerQuickRegisterService.updateMobileVerificationStatus(fetchedEntity.getCustomerId(), 
					fetchedEntity.getIsMobileVerified(), fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());
			
				
		}
		else
		{
			int currentAttemptCount=mobileVerificationDetails.getMobileVerificationAttempts();
			mobileVerificationDetails.setMobileVerificationAttempts(currentAttemptCount+1);
		
			customerMobileVerificationDetailsStatus=customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(fetchedEntity.getCustomerId(), 
					fetchedEntity.getCustomerType(),mobileVerificationDetails.getKey().getMobileType(),mobileVerificationDetails.getMobilePin(),
					mobileVerificationDetails.getMobileVerificationAttempts(), mobileVerificationDetails.getResendCount());
		
		}
		
		if(customerQuickRegisterUpdateStatus.equals(UPDATE_SUCESS)&&customerMobileVerificationDetailsStatus.equals(UPDATE_SUCESS) && verificationStatus && sendPasswordStatus)
			return true;
		else
			return false;
		
	}

	@Override
	public Boolean verifyMobilePin(Long customerId,Integer customerType,Integer mobileType, Integer mobilePin) {
		
		MobileVerificationDetails mobileVerificationDetails=getByEntityIdTypeAndMobileType(customerId,customerType, mobileType);
		
		if(mobileVerificationDetails.getKey()==null)
			return false;
		
		if(mobileVerificationDetails.getMobilePin().equals(mobilePin) && mobileVerificationDetails.getMobileVerificationAttempts()<MAX_MOBILE_VERIFICATION_ATTEMPTS)
		{
			return true;
												
		}
		else
		{
			int currentAttemptCount=mobileVerificationDetails.getMobileVerificationAttempts();
			mobileVerificationDetails.setMobileVerificationAttempts(currentAttemptCount+1);
		
			customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(customerId, 
					customerType,mobileVerificationDetails.getKey().getMobileType(),mobileVerificationDetails.getMobilePin(),
					mobileVerificationDetails.getMobileVerificationAttempts(), mobileVerificationDetails.getResendCount());
		
			return false;
		}
		
		
	}

	
	@Override
	public Boolean reSetMobilePin(Long customerId,Integer customerType,Integer mobileType)  {
	
		Integer updateStatus=new Integer(0);
		
		updateStatus=updateMobilePin(customerId, customerType,mobileType);
		
		String firstName=null;
		String lastName = null;
		
		QuickRegisterEntity quickRegisterEntity=customerQuickRegisterService.getByEntityId(customerId);
		
		if(customerType.equals(ENTITY_TYPE_CUSTOMER))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				firstName=quickRegisterEntity.getFirstName();
				lastName=quickRegisterEntity.getLastName();
			}
			else
			{	
				CustomerDetails customerDetails=customerDetailsService.findById(customerId);
				firstName=customerDetails.getFirstName();
				lastName=customerDetails.getLastName();
			}
			
		}
		else if(customerType.equals(ENTITY_TYPE_VENDOR))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				firstName=quickRegisterEntity.getFirstName();
				lastName=quickRegisterEntity.getLastName();
			}
			else
			{
				VendorDetails vendorDetails=vendorDetailsService.findById(customerId);
				firstName=vendorDetails.getFirstName();
				lastName=vendorDetails.getLastName();
			}
		}
		
		
		MobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.geByEntityIdTypeAndMobileType(customerId,customerType, mobileType);
		
		Boolean sentStatus=messagerSender
				.sendPinSMS(firstName, lastName, mobileVerificationDetails.getMobile(), mobileVerificationDetails.getMobilePin());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus)
			return true;
		else
			return false;
	}


	@Override
	public Integer updateMobilePin(Long customerId,Integer customerType,Integer mobileType) {
		
		Integer mobilePin=handleCustomerVerification.genarateMobilePin();

		return customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(customerId,customerType, mobileType, mobilePin, 0, 0);
	}
	
	@Override
	public Boolean reSendMobilePin(Long customerId,Integer customerType, Integer mobileType) {
		
		Integer updateStatus=new Integer(0);
		Boolean sentStatus=false;
		
		String firstName=null;
		String lastName = null;
		
		
QuickRegisterEntity quickRegisterEntity=customerQuickRegisterService.getByEntityId(customerId);
		
		if(customerType.equals(ENTITY_TYPE_CUSTOMER))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				firstName=quickRegisterEntity.getFirstName();
				lastName=quickRegisterEntity.getLastName();
			}
			else
			{	
				CustomerDetails customerDetails=customerDetailsService.findById(customerId);
				firstName=customerDetails.getFirstName();
				lastName=customerDetails.getLastName();
			}
			
		}
		else if(customerType.equals(ENTITY_TYPE_VENDOR))
		{
			if(quickRegisterEntity.getCustomerId()!=null)
			{
				firstName=quickRegisterEntity.getFirstName();
				lastName=quickRegisterEntity.getLastName();
			}
			else
			{
				VendorDetails vendorDetails=vendorDetailsService.findById(customerId);
				firstName=vendorDetails.getFirstName();
				lastName=vendorDetails.getLastName();
			}
		}


		MobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.geByEntityIdTypeAndMobileType(customerId, customerType,mobileType);
		
		updateStatus=customerMobileVerificationDetailsRepository.incrementResendCount(customerId,customerType, mobileType);
	
		
		if(updateStatus.equals(UPDATE_SUCESS))
			sentStatus=messagerSender
				.sendPinSMS(firstName, lastName, mobileVerificationDetails.getMobile(), mobileVerificationDetails.getMobilePin());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus)
			return true;
		else
			return false;
	}
	

	@Override
	public MobileVerificationDetails saveDetails(
			MobileVerificationDetails mobileVerificationDetails) {
		
		MobileVerificationDetails verificationDetails=customerMobileVerificationDetailsRepository.save(mobileVerificationDetails);
		
		return verificationDetails;
	}

	@Override
	public MobileVerificationDetails getByEntityIdTypeAndMobileType(
			Long customerId,Integer customerType, Integer mobileType) {
		
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository.
					geByEntityIdTypeAndMobileType(customerId,customerType,mobileType);
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

	@Override
	public MobileVerificationDetails getByMobile(
			Long mobile) {
		
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository.
				getByMobile(mobile);
	return fetchedMobileVerificationDetails;
		
	}

	@Override
	public String checkIfMobileAlreadyExist(Long customerId,Integer customerType,Integer mobileType,Long mobile) {

	MobileVerificationDetails fetchedEntity=customerMobileVerificationDetailsRepository.getByMobile(mobile);
		
		if(fetchedEntity.getKey()!=null && fetchedEntity.getKey().getCustomerId().equals(customerId)&&
				fetchedEntity.getKey().getCustomerType().equals(customerType) && fetchedEntity.getKey().getMobileType().equals(mobileType))
			return "EXIST";
		else if(fetchedEntity.getKey()!=null)
			return "EXISTWITHOTHERENTITY";
		else
			return "NOTEXIST";
		
	}

}
