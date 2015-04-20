package com.projectx.data.domain.quickregister;

import javax.validation.constraints.NotNull;

public class CustomerIdTypeMobileTypeRequestedByDTO {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Integer mobileType;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public CustomerIdTypeMobileTypeRequestedByDTO() {

	}

	

	public CustomerIdTypeMobileTypeRequestedByDTO(Long customerId,
			Integer customerType, Integer mobileType, Integer requestedBy,Long requestedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobileType = mobileType;
		this.requestedBy = requestedBy;
		this.requestedById=requestedById;
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


	
	
	public Integer getRequestedBy() {
		return requestedBy;
	}



	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}



	public Long getRequestedById() {
		return requestedById;
	}



	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}



	@Override
	public String toString() {
		return "CustomerIdTypeMobileTypeRequestedByDTO [customerId="
				+ customerId + ", customerType=" + customerType
				+ ", mobileType=" + mobileType + ", requestedBy=" + requestedBy
				+ ", requestedById=" + requestedById + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result
				+ ((mobileType == null) ? 0 : mobileType.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		CustomerIdTypeMobileTypeRequestedByDTO other = (CustomerIdTypeMobileTypeRequestedByDTO) obj;
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
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		return true;
	}



			
	
}
