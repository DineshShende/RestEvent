package com.projectx.data.domain;

public class UpdateEmailPassword {

	private Long customerId;
	
	private String emailPassword;

	public UpdateEmailPassword() {
		super();
	}

	public UpdateEmailPassword(Long customerId, String emailPassword) {
		super();
		this.customerId = customerId;
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

	@Override
	public String toString() {
		return "UpdateEmailPassword [customerId=" + customerId
				+ ", emailPassword=" + emailPassword + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
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
		UpdateEmailPassword other = (UpdateEmailPassword) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (emailPassword == null) {
			if (other.emailPassword != null)
				return false;
		} else if (!emailPassword.equals(other.emailPassword))
			return false;
		return true;
	}
	
	
	
	
	
}
