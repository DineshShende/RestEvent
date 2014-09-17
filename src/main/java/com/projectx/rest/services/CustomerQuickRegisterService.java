package com.projectx.rest.services;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;
import com.projectx.rest.domain.CustomerQuickRegisterKey;
import com.projectx.web.domain.CustomerQuickRegisterEntityDTO;

public interface CustomerQuickRegisterService {
	
	Boolean checkIfAlreadyRegistered(CustomerQuickRegisterEntityDTO customer);
	
	CustomerQuickRegisterEntityDTO populateStatus(CustomerQuickRegisterEntityDTO customer) throws Exception;
	
	CustomerQuickRegisterEntity saveNewCustomerQuickRegisterEntity(CustomerQuickRegisterEntity customer);
	
	CustomerQuickRegisterEntity getCustomerQuickRegisterEntityByKey(CustomerQuickRegisterKey key);
	
	CustomerQuickRegisterEntity handleNewCustomerQuickRegistration(CustomerQuickRegisterEntityDTO customer);
	
	Long generateEmailHash(CustomerQuickRegisterEntityDTO customer);
	
	Integer genarateMobilePin(CustomerQuickRegisterEntityDTO customer);
	
	String composeSMS(CustomerQuickRegisterEntity customer);
	
	String composeEmail(CustomerQuickRegisterEntity customer);
	
	Boolean sendPinSMS();
	
	Boolean sendHashEmail();

}
