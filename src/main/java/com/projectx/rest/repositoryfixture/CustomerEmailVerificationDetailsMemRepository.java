package com.projectx.rest.repositoryfixture;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerEmailVerificationDetails;
import com.projectx.rest.domain.CustomerMobileVerificationDetails;
import com.projectx.rest.repository.CustomerEmailVericationDetailsRepository;

@Component
@Profile(value="Test")
public class CustomerEmailVerificationDetailsMemRepository implements CustomerEmailVericationDetailsRepository{

	Map<KeyEmail,CustomerEmailVerificationDetails> customerList=new HashMap<KeyEmail,CustomerEmailVerificationDetails>();
	
	@Override
	public CustomerEmailVerificationDetails save(
			CustomerEmailVerificationDetails emailVerificationDetails) {
		
		customerList.put(new KeyEmail(emailVerificationDetails.getCustomerId(),emailVerificationDetails.getEmail()), emailVerificationDetails);
		return emailVerificationDetails;
	}

	@Override
	public CustomerEmailVerificationDetails getEmailVerificationDetailsByCustomerIdAndEmail(
			Long customerId, String email) {

		CustomerEmailVerificationDetails customerEmailVerificationDetails;
		
		customerEmailVerificationDetails=customerList.get(new KeyEmail(customerId,email));
		
		if(customerEmailVerificationDetails==null)
			customerEmailVerificationDetails=new CustomerEmailVerificationDetails();
		
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
	public Integer resetEmailHashAndEmailHashSentTime(Long customerId,
			String email, String emailHash, Date emailHashSentTime,
			Integer resetCount) {

		KeyEmail key=new KeyEmail(customerId, email); 
		
		CustomerEmailVerificationDetails oldRecord=customerList.get(key);
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
	public Integer incrementResendCountByCustomerIdAndEmail(Long customerId,
			String email) {


		KeyEmail key=new KeyEmail(customerId, email); 
		
		CustomerEmailVerificationDetails oldRecord=customerList.get(key);
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
class KeyEmail
{
	private Long customerId;
	private String email;

	public KeyEmail() {
		super();
	}

	public KeyEmail(Long customerId, String email) {
		super();
		this.customerId = customerId;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		KeyEmail other = (KeyEmail) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
		
}