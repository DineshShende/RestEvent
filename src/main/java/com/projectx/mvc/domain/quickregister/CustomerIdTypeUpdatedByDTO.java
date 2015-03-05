package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class CustomerIdTypeUpdatedByDTO {
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private String updatedBy;

	public CustomerIdTypeUpdatedByDTO() {

	}

	
	
	public CustomerIdTypeUpdatedByDTO(Long customerId, Integer customerType,
			String updatedBy) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.updatedBy = updatedBy;
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



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	@Override
	public String toString() {
		return "CustomerIdTypeUpdatedBy [customerId=" + customerId
				+ ", customerType=" + customerType + ", updatedBy=" + updatedBy
				+ "]";
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
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
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
		CustomerIdTypeUpdatedByDTO other = (CustomerIdTypeUpdatedByDTO) obj;
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
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}
	
	

}
