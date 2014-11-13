package com.projectx.rest.repositoryfixture;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerAuthenticationDetails;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerAuthenticationDetailsRepository;


@Component
@Profile("Test")
public class CustomerAuthenticationDetailsMemRepository implements CustomerAuthenticationDetailsRepository{

	Map<Long,CustomerAuthenticationDetails> customerList;
	
	
	public CustomerAuthenticationDetailsMemRepository() {
	
		this.customerList = new HashMap<Long,CustomerAuthenticationDetails>();
	}


	@Override
	public CustomerAuthenticationDetails save(
			CustomerAuthenticationDetails authenticationDetails) {

			
		CustomerAuthenticationDetails authenticationDetailsReturned=customerList.put(authenticationDetails.getCustomerId(), authenticationDetails);
		
		return authenticationDetails;
	}


	

	@Override
	public CustomerAuthenticationDetails getCustomerAuthenticationDetailsByEmail(String email) {
		
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getEmail()!=null && customerList.get(key).getEmail().equals(email))
			{
				return customerList.get(key);	
			}
		}
				
		return new CustomerAuthenticationDetails();
	}


	@Override
	public CustomerAuthenticationDetails getCustomerAuthenticationDetailsByMobile(Long mobile) {
		
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getMobile()!=null && customerList.get(key).getMobile().equals(mobile))
			{
				return customerList.get(key);	
			}
		}
				
		return new CustomerAuthenticationDetails();
	}


	@Override
	public CustomerAuthenticationDetails getCustomerAuthenticationDetailsByCustomerId(Long customerId) {
		
		CustomerAuthenticationDetails fetchedEntity= customerList.get(customerId);
		
		if(fetchedEntity==null)
			return new CustomerAuthenticationDetails();
		
		return fetchedEntity; 
	}


	@Override
	public Integer updatePasswordAndPasswordTypeAndCounts(Long customerId,
			String password, String passwordType) {
		
		CustomerAuthenticationDetails oldRecord=customerList.get(customerId);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setPassword(password);
			oldRecord.setPasswordType(passwordType);
			oldRecord.setLoginVerificationCount(0);
			oldRecord.setResendCount(0);

			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}


	@Override
	public Integer updateEmailPasswordAndPasswordTypeAndCounts(Long customerId,
			String emailPassword) {
		
		CustomerAuthenticationDetails oldRecord=customerList.get(customerId);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setEmailPassword(emailPassword);
			oldRecord.setPasswordType("Default");
			oldRecord.setLoginVerificationCount(0);
			oldRecord.setResendCount(0);

			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}


	@Override
	public Integer updateResendCount(Long customerId, Integer resendCount) {
		
		CustomerAuthenticationDetails oldRecord=customerList.get(customerId);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setResendCount(resendCount);

			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}


	@Override
	public Integer updateLastUnsucessfullAttempts(Long customerId,
			Integer lastUnsucessfullAttempts) {
		
		CustomerAuthenticationDetails oldRecord=customerList.get(customerId);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setLoginVerificationCount(lastUnsucessfullAttempts);

			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}
	

	@Override
	public Integer count() {
		
		return new Integer(customerList.size());
	}


	@Override
	public Boolean clearLoginDetailsForTesting() {
		customerList.clear();
		return true;
	}

	
	
/*

	@Override
	public CustomerAuthenticationDetails loginVerification(String email,
			Long mobile, String password) {
		
		for(Long key:customerList.keySet())
		{
			if(((customerList.get(key).getEmail()!=null && customerList.get(key).getEmail().equals(email))|| (customerList.get(key).getMobile()!=null && customerList.get(key).getMobile().equals(mobile)))&& customerList.get(key).getPassword().equals(password))
			{
				return customerList.get(key);	
			}
		}
				
		return new CustomerAuthenticationDetails();
	}
*/

}
