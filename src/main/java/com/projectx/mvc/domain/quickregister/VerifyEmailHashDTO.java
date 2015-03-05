package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class VerifyEmailHashDTO {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Integer emailType;
	
	@NotNull
	private String emailHash;
	
	@NotNull
	private String updatedBy;
	
	public VerifyEmailHashDTO() {
	
	}

	public VerifyEmailHashDTO(Long customerId, Integer customerType,
			Integer emailType, String emailHash, String updatedBy) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
		this.emailHash = emailHash;
		this.updatedBy = updatedBy;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
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
		return "VerifyEmailHashDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", emailType=" + emailType
				+ ", emailHash=" + emailHash + ", updatedBy=" + updatedBy + "]";
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
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
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
		VerifyEmailHashDTO other = (VerifyEmailHashDTO) obj;
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
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	
	
}
