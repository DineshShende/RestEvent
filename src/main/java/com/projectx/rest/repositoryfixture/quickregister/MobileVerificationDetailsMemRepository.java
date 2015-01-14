package com.projectx.rest.repositoryfixture.quickregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.quickregister.CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileTypeDTO;
import com.projectx.mvc.domain.quickregister.VerifyMobileDTO;
import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.domain.quickregister.MobileVerificationDetailsKey;
import com.projectx.rest.repository.quickregister.MobileVerificationDetailsRepository;



@Component
@Profile("Test")
public class MobileVerificationDetailsMemRepository implements
		MobileVerificationDetailsRepository {

	Map<MobileVerificationDetailsKey,MobileVerificationDetails> customerList=new HashMap<MobileVerificationDetailsKey,MobileVerificationDetails>();
	
	
	@Override
	public MobileVerificationDetails save(
			MobileVerificationDetails mobileVerificationDetails) {
		
		
		customerList.put(mobileVerificationDetails.getKey(), mobileVerificationDetails);
		
		return mobileVerificationDetails;
	}

	@Override
	public MobileVerificationDetails geByEntityIdTypeAndMobileType(Long customerId,Integer customerType,Integer mobileType) {
		
		MobileVerificationDetailsKey key=new MobileVerificationDetailsKey(customerId, customerType, mobileType);
		
		MobileVerificationDetails customerMobileVerificationDetails;
		
		customerMobileVerificationDetails=customerList.get(key);
		
		if(customerMobileVerificationDetails==null)
			customerMobileVerificationDetails=new MobileVerificationDetails();
		
		return customerMobileVerificationDetails;
	}

	@Override
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(Long customerId,Integer customerType,Integer mobileType,Integer mobilePin,Integer mobileVerificationAttempts,Integer resendCount) {
		
		MobileVerificationDetailsKey key=new MobileVerificationDetailsKey(customerId, customerType, mobileType);
		
		MobileVerificationDetails oldRecord=customerList.get(key);
		if(oldRecord!=null)
		{	
			customerList.remove(key);
		
			oldRecord.setMobilePin(mobilePin);
			oldRecord.setMobileVerificationAttempts(mobileVerificationAttempts);
			oldRecord.setResendCount(resendCount);
								
			customerList.put(key, oldRecord);
		
			return 1;
		}
		else
			return 0;
		
	}

	

	@Override
	public Long count() {
		
		return Long.valueOf(customerList.size());
	}

	@Override
	public Boolean clearTestData() {
		customerList.clear();
		
		return true;
	}

	@Override
	public Integer incrementMobileVerificationAttempts(Long customerId,Integer customerType,
			Integer mobileType) {
		
		MobileVerificationDetailsKey key=new MobileVerificationDetailsKey(customerId, customerType, mobileType);
		
		MobileVerificationDetails oldRecord=customerList.get(key);
		if(oldRecord!=null)
		{	
			customerList.remove(key);
		
			oldRecord.setMobileVerificationAttempts(oldRecord.getMobileVerificationAttempts()+1);
								
			customerList.put(key, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}

	@Override
	public Integer incrementResendCount(Long customerId,Integer customerType, Integer mobileType) {
		
		MobileVerificationDetailsKey key=new MobileVerificationDetailsKey(customerId, customerType, mobileType);
		
		MobileVerificationDetails oldRecord=customerList.get(key);
		if(oldRecord!=null)
		{	
			customerList.remove(key);
		
			oldRecord.setResendCount(oldRecord.getResendCount()+1);
								
			customerList.put(key, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}

	@Override
	public Boolean delete(MobileVerificationDetailsKey key) {

		customerList.remove(key);
		
		return true;
		
	}

	@Override
	public MobileVerificationDetails getByMobile(
			Long mobile) {

		for (MobileVerificationDetailsKey key : customerList.keySet()) {
			
			if(customerList.get(key).getMobile().equals(mobile))
				return customerList.get(key);
			
		}
		
		return new MobileVerificationDetails();
		
	}

}
