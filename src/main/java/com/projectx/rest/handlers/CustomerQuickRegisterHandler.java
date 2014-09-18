package com.projectx.rest.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
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
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		Boolean emailAlreadyExist = false;

		Boolean mobileAlreadyExist = false;

		if (customer.getEmail() == null && customer.getMobile() == null)
			throw new Exception();

		if (customer.getEmail() != null
				&& customerQuickRegisterRepository.countByEmail(customer
						.getEmail()) > 0)
			emailAlreadyExist = true;

		if (customer.getMobile() != null
				&& customerQuickRegisterRepository.countByMobile(customer
						.getMobile()) > 0)
			mobileAlreadyExist = true;

		if (emailAlreadyExist || mobileAlreadyExist)
			return true;
		else
			return false;

	}

	@Override
	public CustomerQuickRegisterEntityDTO populateStatus(
			CustomerQuickRegisterEntityDTO customer) throws Exception {

		String status = null;

		if (customer.getEmail() == null && customer.getMobile() == null)
			throw new Exception();

		if (customer.getEmail() != null && customer.getMobile() != null) {
			status = "EmailMobileVerificationPending";
		} else if (customer.getEmail() != null) {
			status = "EmailVerificationPending";
		} else if (customer.getMobile() != null) {
			status = "MobileVerificationPending";
		}

		customer.setStatus(status);

		return customer;
	}

	@Override
	public CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(
			CustomerQuickRegisterEntityDTO customer) {

		CustomerQuickRegisterEntity customerToProcess = customer.toCustomerQuickRegisterEntity();
				
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
			CustomerQuickRegisterEntity customer) throws Exception {

		return customerQuickRegisterRepository.save(customer);
	}

	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByEmail(
			String email) {
		return customerQuickRegisterRepository.findByEmail(email);
	}

	@Override
	public CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByMobile(
			Long mobile) {
		return customerQuickRegisterRepository.findByMobile(mobile);
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
