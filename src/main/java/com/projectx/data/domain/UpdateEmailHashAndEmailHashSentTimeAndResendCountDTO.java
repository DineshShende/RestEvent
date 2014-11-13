package com.projectx.data.domain;

import java.util.Date;

public class UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO {
	
	private Long customerId;
	
	private String email;
	
	private String emailHash;
	
	private Date emailHashSentTime;
	
	private Integer resendCount;

	public UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO() {
		super();
	}

	public UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(
			Long customerId, String email, String emailHash,
			Date emailHashSentTime, Integer resendCount) {
		super();
		this.customerId = customerId;
		this.email = email;
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
		return "UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO [customerId="
				+ customerId
				+ ", email="
				+ email
				+ ", emailHash="
				+ emailHash
				+ ", emailHashSentTime="
				+ emailHashSentTime
				+ ", resendCount="
				+ resendCount + "]";
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
		UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO other = (UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO) obj;
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
		} else if (!emailHashSentTime.equals(other.emailHashSentTime))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		return true;
	}

	
}