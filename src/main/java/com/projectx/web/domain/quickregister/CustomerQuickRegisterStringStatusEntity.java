package com.projectx.web.domain.quickregister;

import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

public class CustomerQuickRegisterStringStatusEntity {

	private String status;
	
	private QuickRegisterEntity customer;

	public CustomerQuickRegisterStringStatusEntity(String status,
			QuickRegisterEntity customer) {
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

	public QuickRegisterEntity getCustomer() {
		return customer;
	}

	public void setCustomer(QuickRegisterEntity customer) {
		this.customer = customer;
	}


	
	
	
	
}
