package com.projectx.mvc.domain.quickregister;

import javax.validation.constraints.NotNull;

import com.projectx.rest.domain.quickregister.AuthenticationDetailsKey;

public class UpdatePasswordDTO {

	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;

	@NotNull
	private String password;
	
	@NotNull
	private Boolean isForcefulChangePassword;

	@NotNull
	private String requestedBy;

	public UpdatePasswordDTO() {

	}




	public UpdatePasswordDTO(Long customerId, Integer customerType,
			String password, Boolean isForcefulChangePassword,
			String requestedBy) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.password = password;
		this.isForcefulChangePassword = isForcefulChangePassword;
		this.requestedBy = requestedBy;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	
	

	public Boolean getIsForcefulChangePassword() {
		return isForcefulChangePassword;
	}

	public void setIsForcefulChangePassword(Boolean isForcefulChangePassword) {
		this.isForcefulChangePassword = isForcefulChangePassword;
	}

	
	
	@Override
	public String toString() {
		return "UpdatePasswordDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", password=" + password
				+ ", isForcefulChangePassword=" + isForcefulChangePassword
				+ ", requestedBy=" + requestedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime
				* result
				+ ((isForcefulChangePassword == null) ? 0
						: isForcefulChangePassword.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
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
		UpdatePasswordDTO other = (UpdatePasswordDTO) obj;
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
		if (isForcefulChangePassword == null) {
			if (other.isForcefulChangePassword != null)
				return false;
		} else if (!isForcefulChangePassword
				.equals(other.isForcefulChangePassword))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		return true;
	}

		
	
}
