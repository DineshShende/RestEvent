package com.projectx.rest.domain.quickregister;

public class CustomerQuickRegisterStatusEntity {

	private String status;
	private QuickRegisterEntity customer;
	
	public CustomerQuickRegisterStatusEntity() {
		super();
	}

	public CustomerQuickRegisterStatusEntity(String status,
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

	@Override
	public String toString() {
		return "CustomerQuickRegisterStatusEntity [status=" + status
				+ ", customer=" + customer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerQuickRegisterStatusEntity other = (CustomerQuickRegisterStatusEntity) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	
	
	
		
	
}
