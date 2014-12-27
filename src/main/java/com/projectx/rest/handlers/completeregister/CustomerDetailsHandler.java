package com.projectx.rest.handlers.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;
import com.projectx.rest.services.completeregister.AddressService;
import com.projectx.rest.services.completeregister.CustomerDetailsService;
import com.projectx.rest.services.quickregister.EmailVerificationService;
import com.projectx.rest.services.quickregister.MobileVerificationService;
import com.projectx.rest.utils.MessagerSender;

@Component
@Profile(value="Dev")
public class CustomerDetailsHandler implements CustomerDetailsService {

	
	@Autowired
	CustomerDetailsRepository customerDetailsRepository;
	
	@Autowired
	MobileVerificationService mobileVerificationService;
	
	@Autowired
	EmailVerificationService emailVerificationService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	MessagerSender messagerSender;
	
	private static Integer UPDATE_SUCESS=new Integer(1);

		
	@Override
	public CustomerDetails createCustomerDetailsFromQuickRegisterEntity(
			QuickRegisterEntity quickRegisterEntity) {
	
		CustomerDetails newEntity=new CustomerDetails(quickRegisterEntity.getCustomerId(), quickRegisterEntity.getFirstName(), 
				quickRegisterEntity.getLastName(), null, null, quickRegisterEntity.getMobile(),quickRegisterEntity.getIsMobileVerified(),
				quickRegisterEntity.getEmail(), quickRegisterEntity.getIsEmailVerified(), null, null, null, null, null, 
				false, null, new Date(), new Date(), "CUST_ONLINE");
		
		CustomerDetails createdEntity=customerDetailsRepository.save(newEntity);
		
		return createdEntity;
			
	}

	@Override
	public CustomerDetails mergeCustomerDetails(CustomerDetails customerDetails) {
		
		CustomerDetails oldEntity=customerDetailsRepository.findOne(customerDetails.getCustomerId());
		
		Boolean deletionStatusMobile=true;
		Boolean deletionStatusEmail=true;
		Boolean deletionStatusSeconadryMobile=true;
		Boolean deletionStatusHomeAddress=true;
		Boolean deletionStatusFirmAddress=true;
		
		if(oldEntity!=null && oldEntity.getCustomerId()!=null)
		{
			
			if((oldEntity.getMobile()==null && customerDetails.getMobile()!=null) || 
					(oldEntity.getMobile()!=null && customerDetails.getMobile()!=null &&!oldEntity.getMobile().equals(customerDetails.getMobile())))
			{
				if(oldEntity.getMobile()!=null)
				{
					deletionStatusMobile=mobileVerificationService
							.deleteByKey(new MobileVerificationDetailsKey(oldEntity.getCustomerId(), 1, oldEntity.getMobile()));
				
				}
				Boolean mobileDetailsExists=checkIfMobileSaved(oldEntity.getCustomerId(),1, customerDetails.getMobile());
				
				if(!mobileDetailsExists && deletionStatusMobile)
				{
					MobileVerificationDetails createdEntity=mobileVerificationService
							.createCustomerMobileVerificationEntity(oldEntity.getCustomerId(), 1, customerDetails.getMobile(), 1,customerDetails.getUpdatedBy());
					
					mobileVerificationService.saveCustomerMobileVerificationDetails(createdEntity);
				}
				
				
			}
			
			if((oldEntity.getEmail()==null && customerDetails.getEmail()!=null) ||
					(oldEntity.getEmail()!=null && customerDetails.getEmail()!=null && !oldEntity.getEmail().equals(customerDetails.getEmail()) ))
			{
				if( oldEntity.getEmail()!=null)
				{
					 deletionStatusEmail=emailVerificationService
							.deleteByKey(new EmailVerificationDetailsKey(oldEntity.getCustomerId(), 1, oldEntity.getEmail()));
				}
				
				Boolean emailDetailsExists=checkIfEmailSaved(oldEntity.getCustomerId(),1, customerDetails.getEmail());
				
				if(!emailDetailsExists && deletionStatusEmail)
				{
					EmailVerificationDetails createdEntity=emailVerificationService
							.createCustomerEmailVerificationEntity(oldEntity.getCustomerId(), 1, customerDetails.getEmail(), 1,customerDetails.getUpdatedBy());
					
					emailVerificationService.saveCustomerEmailVerificationDetails(createdEntity);
				}
				
				
			}
			
			if((oldEntity.getSecondaryMobile()==null && customerDetails.getSecondaryMobile()!=null) || 
					(oldEntity.getSecondaryMobile()!=null && customerDetails.getSecondaryMobile()!=null &&!oldEntity.getSecondaryMobile().equals(customerDetails.getSecondaryMobile())))
			{
				if(oldEntity.getSecondaryMobile()!=null)
				{
					 deletionStatusSeconadryMobile=mobileVerificationService
							.deleteByKey(new MobileVerificationDetailsKey(oldEntity.getCustomerId(), 1, oldEntity.getSecondaryMobile()));
				
				}
				Boolean mobileDetailsExists=checkIfMobileSaved(oldEntity.getCustomerId(),1, customerDetails.getSecondaryMobile());
				
				if(!mobileDetailsExists && deletionStatusSeconadryMobile)
				{
					MobileVerificationDetails createdEntity=mobileVerificationService
							.createCustomerMobileVerificationEntity(oldEntity.getCustomerId(), 1, customerDetails.getSecondaryMobile(), 2,customerDetails.getUpdatedBy());
					
					mobileVerificationService.saveCustomerMobileVerificationDetails(createdEntity);
				}
				
				
			}
			
			if(customerDetails.getIsEmailVerified()==null)
				customerDetails.setIsEmailVerified(oldEntity.getIsEmailVerified());
			
			if(customerDetails.getIsMobileVerified()==null)
				customerDetails.setIsMobileVerified(oldEntity.getIsMobileVerified());
			
			if(customerDetails.getIsSecondaryMobileVerified()==null)
				customerDetails.setIsSecondaryMobileVerified(oldEntity.getIsSecondaryMobileVerified());
		
			CustomerDetails updatedCustomerDetails=customerDetailsRepository.save(customerDetails);
			
			if(oldEntity.getHomeAddressId()!=null&&customerDetails.getHomeAddressId()!=null&&!oldEntity.getHomeAddressId().equals(customerDetails.getHomeAddressId()))
			{
				deletionStatusHomeAddress=addressService.deleteById(oldEntity.getHomeAddressId().getAddressId());				
			}
			
			if(oldEntity.getFirmAddressId()!=null&&customerDetails.getFirmAddressId()!=null&&!oldEntity.getFirmAddressId().equals(customerDetails.getFirmAddressId()))
			{
				deletionStatusFirmAddress=addressService.deleteById(oldEntity.getFirmAddressId().getAddressId());
			}
			
			//if(deletionStatusMobile && deletionStatusEmail && deletionStatusSeconadryMobile && deletionStatusHomeAddress && deletionStatusFirmAddress)
				return updatedCustomerDetails;
			
			//else
				//return oldEntity;
			
		}
		else
		{
			Boolean mobileSavedStatus=true;
			Boolean secondaryMobileSavedStatus=true;
			Boolean emailSavedStatus=true;
			
			Boolean mobileDetailsExists=checkIfMobileSaved(customerDetails.getCustomerId(),1, customerDetails.getMobile());
						
			if(!mobileDetailsExists)
			{
				MobileVerificationDetails createdEntity=mobileVerificationService
						.createCustomerMobileVerificationEntity(customerDetails.getCustomerId(), 1, customerDetails.getMobile(), 1,customerDetails.getUpdatedBy());
				
				if(mobileVerificationService.saveCustomerMobileVerificationDetails(createdEntity).getKey()!=null)
					mobileSavedStatus=true;
				else
					mobileSavedStatus=false;
			}
			
			mobileDetailsExists=checkIfMobileSaved(customerDetails.getCustomerId(),1, customerDetails.getSecondaryMobile());
			
			if(!mobileDetailsExists)
			{
				MobileVerificationDetails createdEntity=mobileVerificationService
						.createCustomerMobileVerificationEntity(customerDetails.getCustomerId(), 1, customerDetails.getSecondaryMobile(), 2,customerDetails.getUpdatedBy());
				
				if(mobileVerificationService.saveCustomerMobileVerificationDetails(createdEntity).getKey()!=null)
					secondaryMobileSavedStatus=true;
				else
					secondaryMobileSavedStatus=false;
			}
			
			Boolean emailDetailsExists=checkIfEmailSaved(customerDetails.getCustomerId(),1, customerDetails.getEmail());
			
			if(!emailDetailsExists)
			{
				EmailVerificationDetails createdEntity=emailVerificationService
						.createCustomerEmailVerificationEntity(customerDetails.getCustomerId(), 1, customerDetails.getEmail(), 1,customerDetails.getUpdatedBy());
				
				if(emailVerificationService.saveCustomerEmailVerificationDetails(createdEntity).getKey()!=null)
					emailSavedStatus=true;
				else	
					emailSavedStatus=false;
			}
			
			if(mobileSavedStatus && secondaryMobileSavedStatus && emailSavedStatus)
				return customerDetailsRepository.save(customerDetails);
			else
			{
				//If one of the creation fails rollback other created entities. 
				
				return new CustomerDetails();
			}
		}

	}

	@Override
	public Boolean checkIfMobileSaved(Long customerId, Integer customerType,
			Long mobile) {
		
		MobileVerificationDetails mobileVerificationDetails=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, customerType, mobile);
		
		if(mobileVerificationDetails!=null && mobileVerificationDetails.getKey()!=null)
			return true;
		else
			return false;
		
	}

	@Override
	public MobileVerificationDetails saveMobileVerificationDetails(MobileVerificationDetails mobileVerificationDetails) {
		
		MobileVerificationDetails savedEntity=mobileVerificationService.saveCustomerMobileVerificationDetails(mobileVerificationDetails);
		
		return savedEntity;		
		
	}

	@Override
	public Boolean checkIfEmailSaved(Long customerId, Integer customerType,
			String email) {
		
		
		EmailVerificationDetails emailVerificationDetails=emailVerificationService
				.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId, customerType, email);
		
		if(emailVerificationDetails!=null && emailVerificationDetails.getKey()!=null)
			return true;
		else
			return false;
		
	}

	@Override
	public EmailVerificationDetails saveEmailVerificationDetails(
			EmailVerificationDetails emailVerificationDetails) {
		
		EmailVerificationDetails savedEntity=emailVerificationService.saveCustomerEmailVerificationDetails(emailVerificationDetails);
		
		return savedEntity;		
		
	}

	@Override
	public Boolean verifyMobileDetails(Long customerId, Integer customerType,
			Long mobile, Integer mobileType,Integer mobilePin) {
		
		MobileVerificationDetails fetchedEntity=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, customerType, mobile);
		
		if(fetchedEntity!=null && fetchedEntity.getKey()!=null && fetchedEntity.getMobilePin().equals(mobilePin))
		{
			Integer verificationStatusUpdate=0;
			if(mobileType.equals(1))
			{
				verificationStatusUpdate=customerDetailsRepository
						.updateMobileVerificationStatus(new UpdateMobileVerificationStatusDTO(customerId, true));
			}
			else
			{
				verificationStatusUpdate=customerDetailsRepository
						.updateSecondaryMobileVerificationStatus(new UpdateMobileVerificationStatusDTO(customerId, true));
			}
			
			if(verificationStatusUpdate.equals(UPDATE_SUCESS))
				return true;
			else
				return false;
		}
		else
			return false;
	}

	
	@Override
	public Boolean verifyEmailDetails(Long customerId, Integer customerType,
			String email,  String emailHash) {
		
		EmailVerificationDetails fetchedEntity=emailVerificationService.
				getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId, customerType, email);
		
		Integer updateStatus=new Integer(1);
		
		if(fetchedEntity!=null && fetchedEntity.getKey()!=null && fetchedEntity.getEmailHash().equals(emailHash))
		{
			updateStatus=customerDetailsRepository
					.updateEmailVerificationStatus(new UpdateMobileVerificationStatusDTO(customerId, true));
			
			if(updateStatus.equals(UPDATE_SUCESS))
				return true;
			else
				return false;
		}
		else
		{
			return false;
		}
			
	}
	

	@Override
	public Boolean sendMobileVerificationDetails(Long customerId,
			Integer customerType, Long mobile) {

		MobileVerificationDetails fetchedEntity=mobileVerificationService
				.getCustomerMobileVerificationDetailsByCustomerIdTypeAndMobile(customerId, customerType, mobile);
		
		CustomerDetails customerDetails=customerDetailsRepository.findOne(customerId);
		
		Boolean sendStatus=messagerSender.sendPinSMS(customerDetails.getFirstName(), customerDetails.getLastName(), 
				mobile, fetchedEntity.getMobilePin());
		
		return sendStatus;
	}

	@Override
	public Boolean sendEmailVerificationDetails(Long customerId,
			Integer customerType, String email) {

		EmailVerificationDetails emailVerificationDetails=
				emailVerificationService.getCustomerEmailVerificationDetailsByCustomerIdTypeAndEmail(customerId, customerType, email);
		
		CustomerDetails customerDetails=customerDetailsRepository.findOne(customerId);
		
		Boolean sendStatus=messagerSender.sendHashEmail(customerDetails.getCustomerId(), customerDetails.getFirstName(),
				customerDetails.getLastName(), email, emailVerificationDetails.getEmailHash());
		
		return sendStatus;
	}


	@Override
	public CustomerDetails findById(Long customerId) {
		
		CustomerDetails fetchedEntity=customerDetailsRepository.findOne(customerId);
		
		return fetchedEntity;

	}


	@Override
	public void clearTestData() {

		customerDetailsRepository.deleteAll();
		
		
	}

	@Override
	public Integer count() {
		
		return customerDetailsRepository.count().intValue();
		
	}


	
	/*
	@Override
	public CustomerDetails updateHomeAddress(Long customerId, Address address) {

		CustomerDetails updatedEntity=customerDetailsRepository.updateHomeAddress(new UpdateAddressDTO(customerId, address));
		
		return updatedEntity;
		
	}

	@Override
	public CustomerDetails updateFirmAddress(Long customerId, Address address) {
		
		CustomerDetails updatedEntity=customerDetailsRepository.updateFirmAddress(new UpdateAddressDTO(customerId, address));
		
		return updatedEntity;
		
	}
	*/
	

}
