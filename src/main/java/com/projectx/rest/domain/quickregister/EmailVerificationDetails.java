package com.projectx.rest.domain.quickregister;

import java.util.Date;

public class EmailVerificationDetails {
	
	private EmailVerificationDetailsKey key;
	
	private String emailType;
	
	private String emailHash;
	
	private Date emailHashSentTime;
	
	private Integer resendCount;
	
	private Date insertTime;
	
	private Date UpdateTime;
	
	private String updatedBy;

	public EmailVerificationDetails() {
		super();
	}

	
	public EmailVerificationDetails(EmailVerificationDetailsKey key,
			String emailType, String emailHash, Date emailHashSentTime,
			Integer resendCount, Date insertTime, Date updateTime,
			String updatedBy) {
		super();
		this.key = key;
		this.emailType = emailType;
		this.emailHash = emailHash;
		this.emailHashSentTime = emailHashSentTime;
		this.resendCount = resendCount;
		this.insertTime = insertTime;
		UpdateTime = updateTime;
		this.updatedBy = updatedBy;
	}


	public EmailVerificationDetailsKey getKey() {
		return key;
	}


	public void setKey(EmailVerificationDetailsKey key) {
		this.key = key;
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


	public Date getInsertTime() {
		return insertTime;
	}


	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}


	public Date getUpdateTime() {
		return UpdateTime;
	}


	public void setUpdateTime(Date updateTime) {
		UpdateTime = updateTime;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	@Override
	public String toString() {
		return "EmailVerificationDetails [key=" + key + ", emailType="
				+ emailType + ", emailHash=" + emailHash
				+ ", emailHashSentTime=" + emailHashSentTime + ", resendCount="
				+ resendCount + ", insertTime=" + insertTime + ", UpdateTime="
				+ UpdateTime + ", updatedBy=" + updatedBy + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((UpdateTime == null) ? 0 : UpdateTime.hashCode());
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime
				* result
				+ ((emailHashSentTime == null) ? 0 : emailHashSentTime
						.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
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
		EmailVerificationDetails other = (EmailVerificationDetails) obj;
		if (UpdateTime == null) {
			if (other.UpdateTime != null)
				return false;
		} else if (Math.abs(UpdateTime.getTime()-other.UpdateTime.getTime())>10000)
			return false;
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (emailHashSentTime == null) {
			if (other.emailHashSentTime != null)
				return false;
		} else if (Math.abs(emailHashSentTime.getTime()-other.emailHashSentTime.getTime())>10000)
			return false;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (Math.abs(insertTime.getTime()-other.insertTime.getTime())>10000)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}

	
}
