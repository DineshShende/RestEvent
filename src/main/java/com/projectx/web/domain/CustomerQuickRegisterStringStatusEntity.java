package com.projectx.web.domain;

import com.projectx.rest.domain.CustomerQuickRegisterEntity;

public class CustomerQuickRegisterStringStatusEntity {

	private String status;
	
	private CustomerQuickRegisterEntity customer;

	public CustomerQuickRegisterStringStatusEntity(String status,
			CustomerQuickRegisterEntity customer) {
		super();
		this.status = status;
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerQuickRegisterEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerQuickRegisterEntity customer) {
		this.customer = customer;
	}


	
	
	
	
}
