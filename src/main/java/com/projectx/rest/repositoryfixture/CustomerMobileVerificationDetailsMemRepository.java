package com.projectx.rest.repositoryfixture;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.CustomerMobileVerificationDetailsByCustomerIdAndMobileDTO;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerMobileVerificationDetailsRepository;
import com.projectx.web.domain.VerifyMobileDTO;



@Component
@Profile("Test")
public class CustomerMobileVerificationDetailsMemRepository implements
		CustomerMobileVerificationDetailsRepository {

	Map<Key,CustomerMobileVerificationDetails> customerList=new HashMap<Key,CustomerMobileVerificationDetails>();
	
	
	@Override
	public CustomerMobileVerificationDetails save(
			CustomerMobileVerificationDetails mobileVerificationDetails) {
		
		customerList.put(new Key(mobileVerificationDetails.getCustomerId(),mobileVerificationDetails.getMobile()), mobileVerificationDetails);
		
		return mobileVerificationDetails;
	}

	@Override
	public CustomerMobileVerificationDetails getMobileVerificationDetailsByCustomerIdAndMobile(Long customerId,Long mobile) {
		
		CustomerMobileVerificationDetails customerMobileVerificationDetails;
		
		customerMobileVerificationDetails=customerList.get(new Key(customerId,mobile));
		
		if(customerMobileVerificationDetails==null)
			customerMobileVerificationDetails=new CustomerMobileVerificationDetails();
		
		return customerMobileVerificationDetails;
	}

	@Override
	public Integer updateMobilePinAndMobileVerificationAttemptsAndResendCount(Long customerId,Long mobile,Integer mobilePin,Integer mobileVerificationAttempts,Integer resendCount) {
		
		Key key=new Key(customerId, mobile); 
		
		CustomerMobileVerificationDetails oldRecord=customerList.get(key);
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
	public Integer incrementMobileVerificationAttempts(Long customerId,
			Long mobile) {
		
		Key key=new Key(customerId, mobile); 
		
		CustomerMobileVerificationDetails oldRecord=customerList.get(key);
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
	public Integer incrementResendCount(Long customerId, Long mobile) {
		
		Key key=new Key(customerId, mobile); 
		
		CustomerMobileVerificationDetails oldRecord=customerList.get(key);
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

}
class Key
{
	private Long customerId;
	private Long mobile;

	public Key() {
		super();
	}
	
	public Key(Long customerId, Long mobile) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}

	
	
}