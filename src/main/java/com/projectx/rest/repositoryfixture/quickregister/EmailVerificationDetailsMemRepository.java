package com.projectx.rest.repositoryfixture.quickregister;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.MobileVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.repository.quickregister.EmailVericationDetailsRepository;

@Component
@Profile(value="Test")
public class EmailVerificationDetailsMemRepository implements EmailVericationDetailsRepository{

	Map<EmailVerificationDetailsKey,EmailVerificationDetails> customerList=new HashMap<EmailVerificationDetailsKey,EmailVerificationDetails>();
	
	@Override
	public EmailVerificationDetails save(
			EmailVerificationDetails emailVerificationDetails) {
		
		customerList.put(emailVerificationDetails.getKey(), emailVerificationDetails);
		return emailVerificationDetails;
	}

	@Override
	public EmailVerificationDetails getByEntityIdTypeAndEmailType(
			Long customerId,Integer customerType, Integer emailType) {

		EmailVerificationDetails customerEmailVerificationDetails;
		
		EmailVerificationDetailsKey key=new EmailVerificationDetailsKey(customerId, customerType, emailType);
		
		customerEmailVerificationDetails=customerList.get(key);
		
		if(customerEmailVerificationDetails==null)
			customerEmailVerificationDetails=new EmailVerificationDetails();
		
		return customerEmailVerificationDetails;
		
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
	public Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,
			Integer emailType, String emailHash, Date emailHashSentTime,
			Integer resetCount) {

		EmailVerificationDetailsKey key=new EmailVerificationDetailsKey(customerId, customerType, emailType); 
		
		EmailVerificationDetails oldRecord=customerList.get(key);
		if(oldRecord!=null)
		{	
			customerList.remove(key);
		
			oldRecord.setEmailHash(emailHash);;
			oldRecord.setEmailHashSentTime(emailHashSentTime);
			oldRecord.setResendCount(resetCount);
								
			customerList.put(key, oldRecord);
		
			return 1;
		}
		else
			return 0;

		
		
	}

	@Override
	public Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,
			Integer emailType) {


		EmailVerificationDetailsKey key=new EmailVerificationDetailsKey(customerId, customerType, emailType);
		
		EmailVerificationDetails oldRecord=customerList.get(key);
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
	public Boolean delete(EmailVerificationDetailsKey key) {

		customerList.remove(key);
		
		return true;
	}

	@Override
	public EmailVerificationDetails getByEmail(
			String email) {
		
		for (EmailVerificationDetailsKey key : customerList.keySet()) {
			
			if(customerList.get(key).getEmail().equals(email))
				return customerList.get(key);
			
		}
		
		return new EmailVerificationDetails();
	}

}
