package com.projectx.data.domain.quickregister;

import javax.validation.constraints.NotNull;

public class SendResendResetEmailHashDTO {
	
	@NotNull
	private Long customerId;
	
	@NotNull
	private Integer customerType;
	
	@NotNull
	private Integer emailType;
	
	@NotNull
	private Integer sendOrResendOrResetFlag;
	
	@NotNull
	private Integer requestedBy;
	
	@NotNull
	private Long requestedById;

	public SendResendResetEmailHashDTO() {
		super();
	}

	public SendResendResetEmailHashDTO(Long customerId, Integer customerType,
			Integer emailType, Integer sendOrResendOrResetFlag,
			Integer requestedBy, Long requestedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.emailType = emailType;
		this.sendOrResendOrResetFlag = sendOrResendOrResetFlag;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
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

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public Integer getSendOrResendOrResetFlag() {
		return sendOrResendOrResetFlag;
	}

	public void setSendOrResendOrResetFlag(Integer sendOrResendOrResetFlag) {
		this.sendOrResendOrResetFlag = sendOrResendOrResetFlag;
	}

	public Integer getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}

	public Long getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}

	@Override
	public String toString() {
		return "SendResendResetEmailHashDTO [customerId=" + customerId
				+ ", customerType=" + customerType + ", emailType=" + emailType
				+ ", sendOrResendOrResetFlag=" + sendOrResendOrResetFlag
				+ ", requestedBy=" + requestedBy + ", requestedById="
				+ requestedById + "]";
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
				+ ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
		result = prime
				* result
				+ ((sendOrResendOrResetFlag == null) ? 0
						: sendOrResendOrResetFlag.hashCode());
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
		SendResendResetEmailHashDTO other = (SendResendResetEmailHashDTO) obj;
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
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		if (sendOrResendOrResetFlag == null) {
			if (other.sendOrResendOrResetFlag != null)
				return false;
		} else if (!sendOrResendOrResetFlag
				.equals(other.sendOrResendOrResetFlag))
			return false;
		return true;
	}
	
	
	

}
