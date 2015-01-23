package com.projectx.rest.handlers.quickregister;



import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.completeregister.VendorDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.completeregister.DriverDetailsService;
import com.projectx.rest.services.completeregister.TransactionalUpdatesService;
import com.projectx.rest.services.completeregister.VendorDetailsService;
import com.projectx.rest.services.quickregister.AuthenticationService;
import com.projectx.rest.services.quickregister.QuickRegisterService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.utils.HandleCustomerVerification;
import com.projectx.rest.utils.InformationMapper;
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
	TransactionalUpdatesService transactionalUpdatesService;
	
	@Autowired
	DriverDetailsService driverDetailsService;
	
	
	@Autowired
	MessageBuilder messageBuilder;
	
	@Autowired
	MessagerSender messagerSender;
	
	@Autowired
	InformationMapper informationMapper;
	
	private static final Integer UPDATE_SUCESS=new Integer(1);
	
	private static final Integer ENTITY_TYPE_CUSTOMER=1;
	
	private static final Integer ENTITY_TYPE_VENDOR=2;
	
	private static final Integer ENTITY_TYPE_DRIVER=3;
	
	
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
	
				
		Boolean verificationStatus=false;
		Boolean sendPasswordStatus=true;
		
		Integer UpdateStatus=UPDATE_SUCESS;
		Boolean updateDetails=true;
		
			
		if(verifyMobilePin(customerId, customerType, mobileType, mobilePin))
		{
			QuickRegisterEntity fetchedEntity=customerQuickRegisterService.getByEntityId(customerId);
			
			verificationStatus=true;
			
			if(fetchedEntity.getCustomerId()!=null)
			{
				fetchedEntity.setIsMobileVerified(true);
				
				fetchedEntity.setUpdateTime(new Date());
				fetchedEntity.setUpdatedBy("CUST_ONLINE");
				
				UpdateStatus=customerQuickRegisterService.updateMobileVerificationStatus(fetchedEntity.getCustomerId(), 
						fetchedEntity.getIsMobileVerified(), fetchedEntity.getUpdateTime(), fetchedEntity.getUpdatedBy());
				
				if(UpdateStatus.equals(UPDATE_SUCESS))
					sendPasswordStatus=authenticationHandler.sendDefaultPassword(fetchedEntity, false);
			
			}
			else
			{
				if(customerType.equals(ENTITY_TYPE_DRIVER))
				{
					MobileVerificationDetails mobile=getByEntityIdTypeAndMobileType(customerId, customerType, mobileType);
					
					Integer updationStatus=driverDetailsService.updateMobileAndVerificationStatus(customerId, mobile.getMobile(), true);
					
					if(updationStatus.equals(UPDATE_SUCESS))
						updateDetails=true;
					else
						updateDetails=false;
				}
				else
				{	
				updateDetails=transactionalUpdatesService
						.updateMobileInDetailsEnityAndAuthenticationDetails(customerId, customerType, mobileType);
				}
			}
				
		}
				
		if(UpdateStatus.equals(UPDATE_SUCESS)&& verificationStatus && sendPasswordStatus && updateDetails )
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
	public Boolean sendOrResendOrResetMobilePin(Long customerId,Integer customerType, Integer mobileType,Boolean resetFlag,Boolean resendFlag) {
		
		Integer updateStatus=new Integer(1);
		Boolean sentStatus=false;
		
		Integer mobilePinUpdateStatus=UPDATE_SUCESS;

		
		MobileVerificationDetails mobileVerificationDetails=customerMobileVerificationDetailsRepository
				.geByEntityIdTypeAndMobileType(customerId, customerType,mobileType);
		
		
		if(resetFlag || mobileVerificationDetails.getMobilePin()==null)
		{	
			mobilePinUpdateStatus=updateMobilePin(customerId, customerType,mobileType);
			resetFlag=true;
		}	
		
		
		HashMap<String ,Object> basicInfo=informationMapper.getBasicInfoByEntityIdType(customerId, customerType);
		
		String firstName=(String)basicInfo.get("firstName");
		String lastName =(String)basicInfo.get("lastName");


		if(resetFlag)
			mobileVerificationDetails=customerMobileVerificationDetailsRepository.geByEntityIdTypeAndMobileType(customerId, customerType,mobileType);
		
		if(resendFlag)
				updateStatus=customerMobileVerificationDetailsRepository.incrementResendCount(customerId,customerType, mobileType);
	
		
		if(updateStatus.equals(UPDATE_SUCESS) && mobilePinUpdateStatus.equals(UPDATE_SUCESS))
			sentStatus=messagerSender
				.sendPinSMS(firstName, lastName, mobileVerificationDetails.getMobile(), mobileVerificationDetails.getMobilePin());
		
		if(updateStatus.equals(UPDATE_SUCESS) && sentStatus && mobilePinUpdateStatus.equals(UPDATE_SUCESS))
			return true;
		else
			return false;
	}

	@Override
	public Boolean sendMobilePin(Long entityId, Integer entityType,
			Integer mobileType) {
		
		Boolean result=sendOrResendOrResetMobilePin(entityId, entityType, mobileType, false, false);
		
		return result;
	}



	@Override
	public Boolean reSendMobilePin(Long customerId, Integer customerType,
			Integer mobileType) {
		
		Boolean result=sendOrResendOrResetMobilePin(customerId, customerType, mobileType, false, true);
		
		return result;
	}



	@Override
	public Boolean reSetMobilePin(Long customerId, Integer customerType,
			Integer mobileType) {
		
		Boolean result=sendOrResendOrResetMobilePin(customerId, customerType, mobileType, true, false);
		
		return result;
	}

	
	@Override
	public Integer updateMobilePin(Long customerId,Integer customerType,Integer mobileType) {
		
		Integer mobilePin=handleCustomerVerification.genarateMobilePin();

		return customerMobileVerificationDetailsRepository.updateMobilePinAndMobileVerificationAttemptsAndResendCount(customerId,customerType, mobileType, mobilePin, 0, 0);
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
	public MobileVerificationDetails getByMobile(
			Long mobile) {
		
		MobileVerificationDetails fetchedMobileVerificationDetails=customerMobileVerificationDetailsRepository.
				getByMobile(mobile);
	return fetchedMobileVerificationDetails;
		
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
	public Boolean clearTestData() {

		return customerMobileVerificationDetailsRepository.clearTestData();
	}

/*
 
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
 
 */


	
	
}
