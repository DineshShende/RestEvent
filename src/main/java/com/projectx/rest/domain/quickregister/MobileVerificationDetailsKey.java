package com.projectx.rest.domain.quickregister;

public class MobileVerificationDetailsKey {

	private Long customerId;
	
	private Integer customerType;
	
	private Integer mobileType;

	public MobileVerificationDetailsKey() {
		super();
	}

	public MobileVerificationDetailsKey(Long customerId, Integer customerType,
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

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}


	public Integer getMobileType() {
		return mobileType;
	}

	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}

	@Override
	public String toString() {
		return "MobileVerificationDetailsKey [customerId=" + customerId
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
		MobileVerificationDetailsKey other = (MobileVerificationDetailsKey) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} 
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
