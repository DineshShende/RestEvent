package com.projectx.data.domain;

public class UpdateStatusByCustomerId {

	private Long customerId;
	
	private String status;

	
	
	public UpdateStatusByCustomerId() {
		super();
	}



	public UpdateStatusByCustomerId(Long customerId, String status) {
		super();
		this.customerId = customerId;
		this.status = status;
	}



	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
