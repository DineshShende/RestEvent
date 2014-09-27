package com.projectx.data.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.rest.domain.CustomerQuickRegisterEntity;

public class ResponseCustomerList {

	List<CustomerQuickRegisterEntity> customerList;


	
	@JsonCreator
	public ResponseCustomerList(List<CustomerQuickRegisterEntity> customerList) {
		super();
		this.customerList = customerList;
	}

	public List<CustomerQuickRegisterEntity> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<CustomerQuickRegisterEntity> customerList) {
		this.customerList = customerList;
	}
	
	
}
