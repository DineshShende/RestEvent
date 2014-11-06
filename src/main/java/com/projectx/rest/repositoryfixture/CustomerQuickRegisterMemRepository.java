package com.projectx.rest.repositoryfixture;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;

@Component
@Profile("Test")
public class CustomerQuickRegisterMemRepository implements
		CustomerQuickRegisterRepository {

	Map<Long,CustomerQuickRegisterEntity> customerList;
	
	
	public CustomerQuickRegisterMemRepository() {
	
		this.customerList = new HashMap<Long,CustomerQuickRegisterEntity>();
	}

	@Override
	public CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer)
			throws Exception {
		
		if(customer.getCustomerId()==null)
			customer.setCustomerId(CUST_ID);
		
		 customerList.put(customer.getCustomerId(), customer);
		
		return customer;
		 
	}

	@Override
	public List<CustomerQuickRegisterEntity> findAll() {

		List<CustomerQuickRegisterEntity> customerArrayList=new ArrayList<CustomerQuickRegisterEntity>();
		
		
		for(Long key:customerList.keySet())
		{
			customerArrayList.add(customerList.get(key));
		}
		
		return customerArrayList;
	}

	@Override
	public CustomerQuickRegisterEntity findByCustomerId(Long customerId) {
		
		CustomerQuickRegisterEntity fetchedEntity= customerList.get(customerId);
		
		if(fetchedEntity==null)
			fetchedEntity=new CustomerQuickRegisterEntity();
		
		return fetchedEntity;
	}

	@Override
	public Integer countByEmail(String email) {
		
		int count=0;
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getEmail()!=null && customerList.get(key).getEmail().equals(email))
				count++;
		}
		
		return count;
	}

	@Override
	public Integer countByMobile(Long mobile) {
		int count=0;
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getMobile()!=null && customerList.get(key).getMobile().equals(mobile))
				count++;
		}
		
		return count;
	}

	@Override
	public Integer updateStatusAndMobileVerificationAttemptsByCustomerId(
			Long customerId, String status, Date lastStatusChangedTime,
			Integer mobileVerificationAttempts) {
		
		CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setStatus(status);
			oldRecord.setLastStatusChangedTime(lastStatusChangedTime);
			oldRecord.setMobileVerificationAttempts(mobileVerificationAttempts);
		
			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}

	@Override
	public Integer updateEmailHash(Long customerId, String emailHash,
			Date updateTime) {
		CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setEmailHash(emailHash);
			oldRecord.setEmailHashSentTime(updateTime);
					
			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}

	@Override
	public Integer updateMobilePin(Long customerId, Integer mobilePin,
			Date updateTime) {
		CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setMobilePin(mobilePin);
			oldRecord.setMobilePinSentTime(updateTime);
					
			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}
/*
	@Override
	public Integer updatePassword(Long customerId, String password,
			String passwordType) {
		CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setPassword(password);
			oldRecord.setPasswordType(passwordType);;
					
			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}
*/
	@Override
	public void clearCustomerQuickRegister() {
		customerList.clear();
		
	}

	@Override
	public Integer updateEmailHashAndMobilePinSentTime(Long customerId,
			Date emailHashSentTine, Date mobilePinSentTime) {

			CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
			if(oldRecord!=null)
			{	
				customerList.remove(customerId);
			
				oldRecord.setEmailHashSentTime(emailHashSentTine);
				oldRecord.setMobilePinSentTime(mobilePinSentTime);
						
				customerList.put(customerId, oldRecord);
			
				return 1;
			}
			else
				return 0;
		}

	@Override
	public CustomerQuickRegisterEntity findByEmail(String email) {
		
		CustomerQuickRegisterEntity returnEntity=new CustomerQuickRegisterEntity();
		
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getEmail()!=null && customerList.get(key).getEmail().equals(email))
				
			returnEntity=customerList.get(key);	
		}
		
		return returnEntity;
	}

	@Override
	public CustomerQuickRegisterEntity findByMobile(Long mobile) {

		CustomerQuickRegisterEntity returnEntity=new CustomerQuickRegisterEntity();
		
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getMobile()!=null && customerList.get(key).getMobile().equals(mobile))
				
			returnEntity=customerList.get(key);	
		}
		
		return returnEntity;
	}
	

	
}
