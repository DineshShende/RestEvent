package com.projectx.web.domain;

public class GetByCustomerIdDTO {

	Long customerId;

	public GetByCustomerIdDTO() {
		
	}

	
	public GetByCustomerIdDTO(Long customerId) {
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
