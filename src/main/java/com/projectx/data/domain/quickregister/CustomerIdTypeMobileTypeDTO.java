package com.projectx.data.domain.quickregister;

public class CustomerIdTypeMobileTypeDTO {
	
	private Long customerId;
	
	private Integer customerType;
	
	private Integer mobileType;
	
	public CustomerIdTypeMobileTypeDTO() {
		super();
	}

	

	public CustomerIdTypeMobileTypeDTO(Long customerId, Integer customerType,
			Integer mobileType) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobileType = mobileType;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	
	public Integer getMobileType() {
		return mobileType;
	}



	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}



	public Integer getCustomerType() {
		return customerType;
	}



	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}



	@Override
	public String toString() {
		return "CustomerIdTypeMobileDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", mobile=" + mobileType + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((mobileType == null) ? 0 : mobileType.hashCode());
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
		CustomerIdTypeMobileTypeDTO other = (CustomerIdTypeMobileTypeDTO) obj;
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
		if (mobileType == null) {
			if (other.mobileType != null)
				return false;
		} else if (!mobileType.equals(other.mobileType))
			return false;
		return true;
	}


	
}
