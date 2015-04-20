package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class CustomerIdTypeUpdatedByDTO {
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Integer updatedBy;
	
	@NotNull
	private Long updatedById;

	public CustomerIdTypeUpdatedByDTO() {

	}

	
	
	public CustomerIdTypeUpdatedByDTO(Long customerId, Integer customerType,
			Integer updatedBy,Long updatedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.updatedBy = updatedBy;
		this.updatedById=updatedById;
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



	public Integer getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Long getUpdatedById() {
		return updatedById;
	}



	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}



	@Override
	public String toString() {
		return "CustomerIdTypeUpdatedByDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", updatedBy=" + updatedBy
				+ ", updatedById=" + updatedById + "]";
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
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
			return false;
		return true;
	}


	

}
