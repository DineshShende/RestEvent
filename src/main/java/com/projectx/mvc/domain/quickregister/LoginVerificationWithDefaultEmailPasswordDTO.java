package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

public class LoginVerificationWithDefaultEmailPasswordDTO {
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private String emailPassword;

	public LoginVerificationWithDefaultEmailPasswordDTO() {

	}

	public LoginVerificationWithDefaultEmailPasswordDTO(Long customerId,
			Integer customerType, String emailPassword) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailPassword = emailPassword;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	
	
	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "LoginVerificationWithDefaultEmailPasswordDTO [customerId="
				+ customerId + ", customerType=" + customerType
				+ ", emailPassword=" + emailPassword + "]";
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
				+ ((emailPassword == null) ? 0 : emailPassword.hashCode());
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
		LoginVerificationWithDefaultEmailPasswordDTO other = (LoginVerificationWithDefaultEmailPasswordDTO) obj;
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
		if (emailPassword == null) {
			if (other.emailPassword != null)
				return false;
		} else if (!emailPassword.equals(other.emailPassword))
			return false;
		return true;
	}

	
}
