package com.projectx.web.domain;

public class CustomerIdDTO {

	Long customerId;

	public CustomerIdDTO() {
		
	}

	
	public CustomerIdDTO(Long customerId) {
		super();
		this.customerId = customerId;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	
	
}
