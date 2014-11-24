package com.projectx.rest.domain;



public class CustomerAuthenticationDetails {

	private Long customerId;
	
	private String email;
	
	private Long mobile;
	
	private String password;
	
	private String passwordType;
	
	private String emailPassword;
	
	private Integer resendCount;
	
	private Integer lastUnsucessfullAttempts;

	public CustomerAuthenticationDetails() {

	}

	public CustomerAuthenticationDetails(Long customerId, String email,
			Long mobile, String password, String passwordType,
			String emailPassword, Integer resendCount,
			Integer lastUnsucessfullAttempts) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.passwordType = passwordType;
		this.emailPassword = emailPassword;
		this.resendCount = resendCount;
		this.lastUnsucessfullAttempts = lastUnsucessfullAttempts;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
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

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	public Integer getLastUnsucessfullAttempts() {
		return lastUnsucessfullAttempts;
	}

	public void setLastUnsucessfullAttempts(Integer lastUnsucessfullAttempts) {
		this.lastUnsucessfullAttempts = lastUnsucessfullAttempts;
	}

	@Override
	public String toString() {
		return "CustomerAuthenticationDetails [customerId=" + customerId
				+ ", email=" + email + ", mobile=" + mobile + ", password="
				+ password + ", passwordType=" + passwordType
				+ ", emailPassword=" + emailPassword + ", resendCount="
				+ resendCount + ", lastUnsucessfullAttempts="
				+ lastUnsucessfullAttempts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailPassword == null) ? 0 : emailPassword.hashCode());
		result = prime
				* result
				+ ((lastUnsucessfullAttempts == null) ? 0
						: lastUnsucessfullAttempts.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((passwordType == null) ? 0 : passwordType.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
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
		CustomerAuthenticationDetails other = (CustomerAuthenticationDetails) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailPassword == null) {
			if (other.emailPassword != null)
				return false;
		} else if (!emailPassword.equals(other.emailPassword))
			return false;
		if (lastUnsucessfullAttempts == null) {
			if (other.lastUnsucessfullAttempts != null)
				return false;
		} else if (!lastUnsucessfullAttempts
				.equals(other.lastUnsucessfullAttempts))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
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
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		return true;
	}


	
}
