package com.projectx.data.domain;

public class UpdatePasswordAndPasswordTypeDTO {

	private Long customerId;
	private String password;
	private String passwordType;
	
	public UpdatePasswordAndPasswordTypeDTO() {
		
	}

	public UpdatePasswordAndPasswordTypeDTO(Long customerId, String password,
			String passwordType) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.passwordType = passwordType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordType() {
		return passwordType;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	@Override
	public String toString() {
		return "UpdatePasswordDTO [customerId=" + customerId + ", password="
				+ password + ", passwordType=" + passwordType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordType == null) ? 0 : passwordType.hashCode());
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
		UpdatePasswordAndPasswordTypeDTO other = (UpdatePasswordAndPasswordTypeDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordType == null) {
			if (other.passwordType != null)
				return false;
		} else if (!passwordType.equals(other.passwordType))
			return false;
		return true;
	}
	
	
	
	
	
}