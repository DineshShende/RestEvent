package com.projectx.rest.domain.quickregister;

public class EmailVerificationDetailsKey {

	private Long customerId;
	
	private Integer customerType;
	
	private Integer emailType;

	public EmailVerificationDetailsKey() {
		super();
	}

	public EmailVerificationDetailsKey(Long customerId, Integer customerType,
			Integer emailType) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
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

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmail(Integer emailType) {
		this.emailType = emailType;
	}

	@Override
	public String toString() {
		return "EmailVerificationDetailsKey [customerId=" + customerId
				+ ", customerType=" + customerType + ", email=" + emailType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((emailType == null) ? 0 : emailType.hashCode());
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
		EmailVerificationDetailsKey other = (EmailVerificationDetailsKey) obj;
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
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		return true;
	}
	
	
	
}
