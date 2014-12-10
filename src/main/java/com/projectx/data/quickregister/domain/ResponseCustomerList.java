package com.projectx.data.quickregister.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;

public class ResponseCustomerList {

	List<QuickRegisterEntity> customerList;


	
	@JsonCreator
	public ResponseCustomerList(List<QuickRegisterEntity> customerList) {
		super();
		this.customerList = customerList;
	}

	public List<QuickRegisterEntity> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<QuickRegisterEntity> customerList) {
		this.customerList = customerList;
	}
	
	
}
