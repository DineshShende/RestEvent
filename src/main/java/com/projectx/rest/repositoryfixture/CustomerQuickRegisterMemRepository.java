package com.projectx.rest.repositoryfixture;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.projectx.rest.fixtures.CustomerQuickRegisterDataFixture.*;

import com.projectx.data.domain.UpdateEmailMobileVerificationStatus;
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
		 {
		
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
	public void clearCustomerQuickRegister() {
		customerList.clear();
		
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

	@Override
	public Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy) {
		CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setIsMobileVerified(status);
			oldRecord.setUpdateTime(updateTime);
			oldRecord.setUpdatedBy(updatedBy);
								
			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}

	@Override
	public Integer updateEmailVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy) {
		
		CustomerQuickRegisterEntity oldRecord=customerList.get(customerId);
		if(oldRecord!=null)
		{	
			customerList.remove(customerId);
		
			oldRecord.setIsEmailVerified(status);
			oldRecord.setUpdateTime(updateTime);
			oldRecord.setUpdatedBy(updatedBy);
								
			customerList.put(customerId, oldRecord);
		
			return 1;
		}
		else
			return 0;
	}

}
