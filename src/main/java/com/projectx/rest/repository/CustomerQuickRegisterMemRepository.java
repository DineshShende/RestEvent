package com.projectx.rest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;

@Repository
@Profile("Test")
public class CustomerQuickRegisterMemRepository implements
		CustomerQuickRegisterRepository {

	List<CustomerQuickRegisterEntity> customerList;

	public CustomerQuickRegisterMemRepository() {

		this.customerList = new ArrayList<CustomerQuickRegisterEntity>();
	}

	@Override
	public CustomerQuickRegisterEntity save(CustomerQuickRegisterEntity customer) throws Exception {
		if(countByEmail(customer.getEmail())==0 && countByMobile(customer.getMobile())==0)
		{
			customerList.add(customer);
			return customer;
		}			
		else
		{
			throw new Exception();
		}
			
		
	}

	@Override
	public CustomerQuickRegisterEntity findByEmail(String email) {

		CustomerQuickRegisterEntity resultEntity = null;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getEmail()!=null &&customerList.get(i).getEmail().equalsIgnoreCase(email))
				resultEntity = customerList.get(i);
		}

		return resultEntity;
	}

	@Override
	public CustomerQuickRegisterEntity findByMobile(Long mobile) {

		CustomerQuickRegisterEntity resultEntity = null;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getMobile()!=null && customerList.get(i).getMobile().equals(mobile))
				resultEntity = customerList.get(i);
		}

		return resultEntity;
	}

	@Override
	public List<CustomerQuickRegisterEntity> findAll() {

		return this.customerList;
	}

	@Override
	public int countByEmail(String email) {
		int count = 0;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getEmail()!=null && customerList.get(i).getEmail().equalsIgnoreCase(email))
				count++;
		}

		return count;
	}

	@Override
	public int countByMobile(Long mobile) {

		int count = 0;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getMobile()!=null &&customerList.get(i).getMobile().equals(mobile) )
				count++;
		}

		return count;

	}

	@Override
	public Long deleteByEmail(String email) {
		Long count = 0L;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getEmail()!=null&&customerList.get(i).getEmail().equalsIgnoreCase(email)) {
				customerList.remove(i);
				count++;
			}
		}

		return count;
	}

	@Override
	public Long deleteByMobile(Long mobile) {
		Long count = 0L;

		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getMobile()!=null && customerList.get(i).getMobile().equals(mobile)) {
				customerList.remove(i);
				count++;
			}
		}

		return count;
	}

	@Override
	public void clearCustomerQuickRegister() {
		customerList.clear();
		
	}

	@Override
	public String fetchStatusByEmail(String email) throws Exception {
		
		for(int i=0;i<customerList.size();i++)
		{
			if(customerList.get(i).getEmail()!=null && customerList.get(i).getEmail().equalsIgnoreCase(email))
			{
				return customerList.get(i).getStatus();
			}
		}
			
		throw new Exception();
	}

	@Override
	public String fetchStatusByMobile(Long mobile) throws Exception {
		for(int i=0;i<customerList.size();i++)
		{
			if(customerList.get(i).getMobile()!=null && customerList.get(i).getMobile().equals(mobile))
			{
				return customerList.get(i).getStatus();
			}
		}
			
		throw new Exception();
	}
	
	@Override
	public Integer updateStatusAfterMobileVerification(Long mobile,
			String status) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getMobile()!=null && customerList.get(i).getMobile().equals(mobile))
			{
				CustomerQuickRegisterEntity customer=customerList.get(i);
				deleteByMobile(mobile);
				customer.setStatus(status);
				customerList.add(customer);
				return new Integer(1);
			}
		}
		return new Integer(0);
	}

	@Override
	public Integer updateStatusAfterEmailVerfication(String email,
			String status) {
		
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getEmail()!=null && customerList.get(i).getEmail().equalsIgnoreCase(email)) {
				CustomerQuickRegisterEntity customer=customerList.get(i);
				deleteByEmail(email);
				customer.setStatus(status);
				customerList.add(customer);
				return new Integer(1);
			}
		}
		
		return new Integer(0);
	}



}
