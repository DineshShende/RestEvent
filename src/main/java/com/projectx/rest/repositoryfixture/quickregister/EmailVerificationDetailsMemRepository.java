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
	public EmailVerificationDetails getEmailVerificationDetailsByCustomerIdTypeAndEmail(
			Long customerId,Integer customerType, String email) {

		EmailVerificationDetails customerEmailVerificationDetails;
		
		EmailVerificationDetailsKey key=new EmailVerificationDetailsKey(customerId, customerType, email);
		
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
			String email, String emailHash, Date emailHashSentTime,
			Integer resetCount) {

		EmailVerificationDetailsKey key=new EmailVerificationDetailsKey(customerId, customerType, email); 
		
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
			String email) {


		EmailVerificationDetailsKey key=new EmailVerificationDetailsKey(customerId, customerType, email);
		
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

}
