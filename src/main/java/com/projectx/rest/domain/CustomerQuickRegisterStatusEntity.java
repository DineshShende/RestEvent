package com.projectx.rest.domain;

public class CustomerQuickRegisterStatusEntity {

	private Boolean status;
	private CustomerQuickRegisterEntity customer;
	
	public CustomerQuickRegisterStatusEntity() {
		super();
	}

	
	
	
	public CustomerQuickRegisterStatusEntity(Boolean status,
			CustomerQuickRegisterEntity customer) {
		super();
		this.status = status;
		this.customer = customer;
	}


	
	


	public Boolean getStatus() {
		return status;
	}




	public void setStatus(Boolean status) {
		this.status = status;
	}




	public CustomerQuickRegisterEntity getCustomer() {
		return customer;
	}




	public void setCustomer(CustomerQuickRegisterEntity customer) {
		this.customer = customer;
	}




	@Override
	public String toString() {
		return "CustomerQuickDetailsSentStatusEntity [status=" + status
				+ ", customer=" + customer + "]";
	}
	
	
	
	
}
