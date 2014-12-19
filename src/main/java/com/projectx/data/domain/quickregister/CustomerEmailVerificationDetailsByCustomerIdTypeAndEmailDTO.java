package com.projectx.data.domain.quickregister;

public class CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO {

	private Long customerId;
	
	private Integer customerType; 
	
	private String email;

	public CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO() {
		super();
	}


	public CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO(
			Long customerId, Integer customerType, String email) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.email = email;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public Integer getCustomerType() {
		return customerType;
	}


	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}


	@Override
	public String toString() {
		return "CustomerEmailVerificationDetailsByCustomerIdAndEmailDTO [customerId="
				+ customerId
				+ ", customerType="
				+ customerType
				+ ", email="
				+ email + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO other = (CustomerEmailVerificationDetailsByCustomerIdTypeAndEmailDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}


	
	
	
}
