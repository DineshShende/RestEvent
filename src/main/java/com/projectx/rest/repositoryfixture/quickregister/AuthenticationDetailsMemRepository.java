package com.projectx.rest.repositoryfixture.quickregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;

import com.projectx.rest.repository.quickregister.AuthenticationDetailsRepository;


@Component
@Profile("Test")



public class AuthenticationDetailsMemRepository implements AuthenticationDetailsRepository{

	Map<AuthenticationDetailsKey,AuthenticationDetails> customerList;
	
	
	public AuthenticationDetailsMemRepository() {
	
		this.customerList = new HashMap<AuthenticationDetailsKey,AuthenticationDetails>();
	}


	@Override
	public AuthenticationDetails save(
			AuthenticationDetails authenticationDetails) {

		AuthenticationDetails authenticationDetailsReturned=customerList.put(authenticationDetails.getKey(), authenticationDetails);
		
		return authenticationDetails;
	}


	

	@Override
	public AuthenticationDetails getByEmail(String email) {
		
		for(AuthenticationDetailsKey key:customerList.keySet())
		{
			if(customerList.get(key).getEmail()!=null && customerList.get(key).getEmail().equals(email))
			{
				return customerList.get(key);	
			}
		}
				
		return new AuthenticationDetails();
	}


	@Override
	public AuthenticationDetails getByMobile(Long mobile) {
		
		for(AuthenticationDetailsKey key:customerList.keySet())
		{
			if(customerList.get(key).getMobile()!=null && customerList.get(key).getMobile().equals(mobile))
			{
				return customerList.get(key);	
			}
		}
				
		return new AuthenticationDetails();
	}


	@Override
	public AuthenticationDetails getByCustomerIdType(Long customerId,Integer customerType) {
		
		AuthenticationDetails fetchedEntity= customerList.get(new AuthenticationDetailsKey(customerId, customerType));
		
		if(fetchedEntity==null)
			return new AuthenticationDetails();
		
		return fetchedEntity; 
	}


	@Override
	public Integer updatePasswordEmailPasswordAndPasswordTypeAndCounts(Long customerId,Integer customerType,
			String password,String emailPassword, String passwordType,String updatedBy) {
		
		AuthenticationDetailsKey key=new AuthenticationDetailsKey(customerId, customerType);
		
		AuthenticationDetails oldRecord=customerList.get(key);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setPassword(password);
			oldRecord.setEmailPassword(emailPassword);			
			oldRecord.setPasswordType(passwordType);
			oldRecord.setLastUnsucessfullAttempts(0);
			oldRecord.setResendCount(0);
			oldRecord.setUpdatedBy(updatedBy);

			customerList.put(key, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}


	@Override
	public Integer incrementResendCount(Long customerId,Integer customerType,String updatedBy) {
		
		AuthenticationDetailsKey key=new AuthenticationDetailsKey(customerId, customerType);
		
		AuthenticationDetails oldRecord=customerList.get(key);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setResendCount(oldRecord.getResendCount()+1);
			oldRecord.setUpdatedBy(updatedBy);

			customerList.put(key, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}


	@Override
	public Integer incrementLastUnsucessfullAttempts(Long customerId,Integer customerType,String updatedBy) {
		
		AuthenticationDetailsKey key=new AuthenticationDetailsKey(customerId, customerType);
		
		AuthenticationDetails oldRecord=customerList.get(key);
		
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setLastUnsucessfullAttempts(oldRecord.getLastUnsucessfullAttempts()+1);
			oldRecord.setUpdatedBy(updatedBy);

			customerList.put(key, oldRecord);
		
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
