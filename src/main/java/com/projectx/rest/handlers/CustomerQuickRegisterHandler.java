package com.projectx.rest.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterKey;
import com.projectx.rest.repository.CustomerQuickRegisterRepository;
import com.projectx.rest.services.CustomerQuickRegisterService;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

@Component
public class CustomerQuickRegisterHandler implements
		CustomerQuickRegisterService {

	@Autowired
	CustomerQuickRegisterRepository customerQuickRegisterRepository;

	@Override
	public Boolean checkIfAlreadyRegistered(
			CustomerQuickRegisterEntityDTO customer) {
		CustomerQuickRegisterKey key = new CustomerQuickRegisterKey(
				customer.getEmail(), customer.getMobile());

		if (customerQuickRegisterRepository.checkIfAlreadyExist(key))
			return true;
		else
			return false;

	}

	@Override
	public CustomerQuickRegisterEntityDTO populateStatus(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		String status = null;

		if (customer.getEmail().equalsIgnoreCase("")
				&& customer.getMobile() == 0L)
			throw new Exception();

		if (!customer.getEmail().equalsIgnoreCase("")
				&& customer.getMobile() != 0L) {
			status = "EmailMobileVerificationPending";
		} else if (!customer.getEmail().equalsIgnoreCase("")) {
			status = "EmailVerificationPending";
		} else if (customer.getMobile() != 0L) {
			status = "MobileVerificationPending";
		}

		customer.setStatus(status);

		return customer;
	}



	@Override
	public CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(
			CustomerQuickRegisterEntityDTO customer) {

		CustomerQuickRegisterEntity customerToProcess = new CustomerQuickRegisterEntity();

		customerToProcess.setFirstName(customer.getFirstName());
		customerToProcess.setLastName(customer.getLastName());
		customerToProcess.setKey(new CustomerQuickRegisterKey(customer
				.getEmail(), customer.getMobile()));
		customerToProcess.setPin(customer.getPin());
		customerToProcess.setStatus(customer.getStatus());

		if (customerToProcess.getStatus().equals(
				"EmailMobileVerificationPending")) {
			customerToProcess.setEmailHash(generateEmailHash(customer));
			customerToProcess.setMobilePin(genarateMobilePin(customer));
		} else if (customerToProcess.getStatus().equals(
				"EmailVerificationPending")) {
			customerToProcess.setEmailHash(generateEmailHash(customer));
		} else if (customerToProcess.getStatus().equals(
				"MobileVerificationPending")) {
			customerToProcess.setMobilePin(genarateMobilePin(customer));
		}

		return customerToProcess;
	}
	
	
	
	@Override
	public CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(
			CustomerQuickRegisterEntity customer) {

		return customerQuickRegisterRepository.save(customer);
	}
	
	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByKey(
			CustomerQuickRegisterKey key) {
		return customerQuickRegisterRepository.getByKey(key);
	}


	@Override
	public Long generateEmailHash(CustomerQuickRegisterEntityDTO customer) {

		return 1010101010L;
	}

	@Override
	public Integer genarateMobilePin(CustomerQuickRegisterEntityDTO customer) {

		return 101010;
	}

	@Override
	public Boolean verifyEmail(String email, Long emailHash) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean verifyMobile(Long mobile, Integer mobilePin) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String composeSMS(CustomerQuickRegisterEntity customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String composeEmail(CustomerQuickRegisterEntity customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendPinSMS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendHashEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
