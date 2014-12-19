package com.projectx.data.domain.quickregister;

public class CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO {

	private Long customerId;
	
	private Integer customerType;
	
	private Long mobile;

	public CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO() {

	}

	

	public CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO(
			Long customerId, Integer customerType, Long mobile) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobile = mobile;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	
	
	public Integer getCustomerType() {
		return customerType;
	}



	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}



	@Override
	public String toString() {
		return "CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO [customerId="
				+ customerId
				+ ", customerType="
				+ customerType
				+ ", mobile="
				+ mobile + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO other = (CustomerMobileVerificationDetailsByCustomerIdTypeAndMobileDTO) obj;
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
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		return true;
	}


	
	
}
