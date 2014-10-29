package com.projectx.data.domain;

import java.util.Date;


public class UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO {

	private Long customerId;
	private String status;
	private Date statusChangeTime;
	private Integer mobileVerificationAttempts;
	
	
	
	public UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO() {
		super();
	}


	public UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO(Long customerId, String status,
			Date statusChangeTime,Integer mobileVerificationAttempts) {
		super();
		this.customerId = customerId;
		this.status = status;
		this.statusChangeTime = statusChangeTime;
		this.mobileVerificationAttempts=mobileVerificationAttempts;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getStatusChangeTime() {
		return statusChangeTime;
	}

	//@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setStatusChangeTime(Date statusChangeTime) {
		
		this.statusChangeTime = statusChangeTime;
	}

	
	

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}


	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}


	@Override
	public String toString() {
		return "UpdateStatusWithCustomerIdDTO [customerId=" + customerId
				+ ", status=" + status + ", statusChangeTime="
				+ statusChangeTime + ", mobileVerificationAttempts="
				+ mobileVerificationAttempts + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime
				* result
				+ ((statusChangeTime == null) ? 0 : statusChangeTime.hashCode());
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
		UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO other = (UpdateStatusAndMobileVerificationAttemptsWithCustomerIdDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusChangeTime == null) {
			if (other.statusChangeTime != null)
				return false;
		} else if (!statusChangeTime.equals(other.statusChangeTime))
			return false;
		return true;
	}


		
	
	
}
