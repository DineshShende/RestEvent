package com.projectx.rest.domain;

import java.util.Date;

public class CustomerEmailVerificationDetails {
	
	private Long customerId;
	
	private String email;
	
	private String emailType;
	
	private String emailHash;
	
	private Date emailHashSentTime;
	
	private Integer resendCount;

	public CustomerEmailVerificationDetails() {
		super();
	}

	public CustomerEmailVerificationDetails(Long customerId, String email,
			String emailType, String emailHash, Date emailHashSentTime,
			Integer resendCount) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.emailType = emailType;
		this.emailHash = emailHash;
		this.emailHashSentTime = emailHashSentTime;
		this.resendCount = resendCount;
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

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}

	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
	}

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	@Override
	public String toString() {
		return "CustomerEmailVerificationDetails [customerId=" + customerId
				+ ", email=" + email + ", emailType=" + emailType
				+ ", emailHash=" + emailHash + ", emailHashSentTime="
				+ emailHashSentTime + ", resendCount=" + resendCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime
				* result
				+ ((emailHashSentTime == null) ? 0 : emailHashSentTime
						.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
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
		CustomerEmailVerificationDetails other = (CustomerEmailVerificationDetails) obj;
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
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (emailHashSentTime == null) {
			if (other.emailHashSentTime != null)
				return false;
		} else if (true)
			return true;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		return true;
	}


	
}
