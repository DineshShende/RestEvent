package com.projectx.rest.domain.quickregister;

public class CustomerQuickRegisterStatusEntity {

	private Boolean status;
	private QuickRegisterEntity customer;
	
	public CustomerQuickRegisterStatusEntity() {
		super();
	}

	
	
	
	public CustomerQuickRegisterStatusEntity(Boolean status,
			QuickRegisterEntity customer) {
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




	public QuickRegisterEntity getCustomer() {
		return customer;
	}




	public void setCustomer(QuickRegisterEntity customer) {
		this.customer = customer;
	}




	@Override
	public String toString() {
		return "CustomerQuickDetailsSentStatusEntity [status=" + status
				+ ", customer=" + customer + "]";
	}
	
	
	
	
}
