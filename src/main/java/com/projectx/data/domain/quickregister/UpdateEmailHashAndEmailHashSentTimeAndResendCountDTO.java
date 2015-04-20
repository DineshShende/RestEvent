package com.projectx.data.domain.quickregister;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;

public class UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO {
	
	private Long customerId;
	
	private Integer customerType;
	
	private Integer emailType;
	
	private String emailHash;
	
	private Date emailHashSentTime;
	
	private Integer resendCount;
	
	private Integer updatedBy;
	
	private Long updatedById;

	public UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO() {

	}

	public UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO(
			Long customerId, Integer customerType, Integer emailType,
			String emailHash, Date emailHashSentTime, Integer resendCount,
			Integer updatedBy,Long updatedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
		this.emailHash = emailHash;
		this.emailHashSentTime = emailHashSentTime;
		this.resendCount = resendCount;
		this.updatedBy = updatedBy;
		this.updatedById=updatedById;
	}



	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getEmailType() {
		return emailType;
	}



	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
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

	
	
	public Integer getCustomerType() {
		return customerType;
	}



	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}



	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedById() {
		return updatedById;
	}

	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	@Override
	public String toString() {
		return "UpdateEmailHashAndEmailHashSentTimeAndResendCountDTO [customerId="
				+ customerId
				+ ", customerType="
				+ customerType
				+ ", emailType="
				+ emailType
				+ ", emailHash="
				+ emailHash
				+ ", emailHashSentTime="
				+ emailHashSentTime
				+ ", resendCount="
				+ resendCount
				+ ", updatedBy="
				+ updatedBy
				+ ", updatedById="
				+ updatedById + "]";
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
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime
				* result
				+ ((emailHashSentTime == null) ? 0 : emailHashSentTime
						.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result
				+ ((resendCount == null) ? 0 : resendCount.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		if (customerType == null) {
			if (other.customerType != null)
				return false;
		} else if (!customerType.equals(other.customerType))
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
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
			return false;
		return true;
	}

	
	
}
