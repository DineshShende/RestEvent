package com.projectx.mvc.domain.quickregister;

public class CustomerIdTypeDTO {

	private Long customerId;
	
	private Integer customerType;

	public CustomerIdTypeDTO() {
		
	}

	public CustomerIdTypeDTO(Long customerId, Integer customerType) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
	}

	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "CustomerIdTypeDTO [customerId=" + customerId
				+ ", customerType=" + customerType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
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
		CustomerIdTypeDTO other = (CustomerIdTypeDTO) obj;
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
		return true;
	}


	
		
}
