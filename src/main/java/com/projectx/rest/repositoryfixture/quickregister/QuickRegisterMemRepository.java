package com.projectx.rest.repositoryfixture.quickregister;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.projectx.rest.fixtures.quickregister.CustomerQuickRegisterDataFixture.*;

import com.projectx.data.domain.quickregister.MobilePinPasswordDTO;
import com.projectx.data.domain.quickregister.UpdateEmailMobileVerificationStatus;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.quickregister.QuickRegisterRepository;

@Component
@Profile("Test")
public class QuickRegisterMemRepository implements
		QuickRegisterRepository {

	Map<Long,QuickRegisterEntity> customerList;
	
	
	public QuickRegisterMemRepository() {
	
		this.customerList = new HashMap<Long,QuickRegisterEntity>();
	}

	@Override
	public QuickRegisterEntity save(QuickRegisterEntity customer)
		 {
		
		if(customer.getCustomerId()==null)
			customer.setCustomerId(CUST_ID);
		
		 customerList.put(customer.getCustomerId(), customer);
		
		return customer;
		 
	}

	@Override
	public List<QuickRegisterEntity> findAll() {

		List<QuickRegisterEntity> customerArrayList=new ArrayList<QuickRegisterEntity>();
		
		
		for(Long key:customerList.keySet())
		{
			customerArrayList.add(customerList.get(key));
		}
		
		return customerArrayList;
	}

	@Override
	public QuickRegisterEntity findByCustomerId(Long customerId) {
		
		QuickRegisterEntity fetchedEntity= customerList.get(customerId);
		
		if(fetchedEntity==null)
			fetchedEntity=new QuickRegisterEntity();
		
		return fetchedEntity;
	}

	@Override
	public void clearCustomerQuickRegister() {
		customerList.clear();
		
	}

	@Override
	public QuickRegisterEntity findByEmail(String email) {
		
		QuickRegisterEntity returnEntity=new QuickRegisterEntity();
		
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getEmail()!=null && customerList.get(key).getEmail().equals(email))
				
			returnEntity=customerList.get(key);	
		}
		
		return returnEntity;
	}

	@Override
	public QuickRegisterEntity findByMobile(Long mobile) {

		QuickRegisterEntity returnEntity=new QuickRegisterEntity();
		
		for(Long key:customerList.keySet())
		{
			if(customerList.get(key).getMobile()!=null && customerList.get(key).getMobile().equals(mobile))
				
			returnEntity=customerList.get(key);	
		}
		
		return returnEntity;
	}

	@Override
	public Integer updateMobileVerificationStatus(Long customerId,Boolean status,Date updateTime,String updatedBy) {
		QuickRegisterEntity oldRecord=customerList.get(customerId);
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
		
		QuickRegisterEntity oldRecord=customerList.get(customerId);
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

	@Override
	public List<MobilePinPasswordDTO> getTestData() {
		// TODO Auto-generated method stub
		return null;
	}

}
