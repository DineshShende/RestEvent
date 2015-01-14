package com.projectx.rest.domain.quickregister;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;

public class EmailVerificationDetails {
	
	private EmailVerificationDetailsKey key;
	
	private String  email;
	
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
			String email, String emailHash, Date emailHashSentTime,
			Integer resendCount, Date insertTime, Date updateTime,
			String updatedBy) {
		super();
		this.key = key;
		this.email = email;
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

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getEmailHashSentTime() {
		return emailHashSentTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setEmailHashSentTime(Date emailHashSentTime) {
		this.emailHashSentTime = emailHashSentTime;
	}


	public Integer getResendCount() {
		return resendCount;
	}


	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return UpdateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
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
		return "EmailVerificationDetails [key=" + key + ", email="
				+ email + ", emailHash=" + emailHash
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
				+ ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
