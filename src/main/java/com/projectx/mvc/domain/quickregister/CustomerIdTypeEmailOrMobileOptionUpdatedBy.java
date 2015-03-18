package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class CustomerIdTypeEmailOrMobileOptionUpdatedBy {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Integer emailOrMobile;
	
	@NotNull
	private String updatedBy;

	public CustomerIdTypeEmailOrMobileOptionUpdatedBy() {

	}

	public CustomerIdTypeEmailOrMobileOptionUpdatedBy(Long customerId,
			Integer customerType, Integer emailOrMobile, String updatedBy) {

		this.customerId = customerId;
		this.customerType = customerType;
		this.emailOrMobile = emailOrMobile;
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

	public Integer getEmailOrMobile() {
		return emailOrMobile;
	}

	public void setEmailOrMobile(Integer emailOrMobile) {
		this.emailOrMobile = emailOrMobile;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "CustomerIdTypeEmailOrMobileOptionUpdatedBy [customerId="
				+ customerId + ", customerType=" + customerType
				+ ", emailOrMobile=" + emailOrMobile + ", updatedBy="
				+ updatedBy + "]";
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
				+ ((emailOrMobile == null) ? 0 : emailOrMobile.hashCode());
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
		CustomerIdTypeEmailOrMobileOptionUpdatedBy other = (CustomerIdTypeEmailOrMobileOptionUpdatedBy) obj;
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
		if (emailOrMobile == null) {
			if (other.emailOrMobile != null)
				return false;
		} else if (!emailOrMobile.equals(other.emailOrMobile))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}
	
	
	
	
}
