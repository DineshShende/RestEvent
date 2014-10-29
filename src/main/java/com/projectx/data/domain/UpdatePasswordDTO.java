package com.projectx.data.domain;

public class UpdatePasswordDTO {

	private Long customerId;
	
	private String password;
	
	private String passwordType;

	public UpdatePasswordDTO() {
		super();
	}

	public UpdatePasswordDTO(Long customerId, String password,
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
	
	
}
