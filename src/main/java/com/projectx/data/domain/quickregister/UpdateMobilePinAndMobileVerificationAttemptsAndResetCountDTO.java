package com.projectx.data.domain.quickregister;


public class UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO {

	private Long customerId;
	
	private Integer customerType;
	private Integer mobileType;
	private Integer mobilePin;
	private Integer mobileVerificationAttempts;
	private Integer resendCount;
	private Integer updatedBy;
	private Long updatedById;
	
	
	public UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO() {

	}


	public UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(
			Long customerId, Integer customerType, Integer mobileType,
			Integer mobilePin, Integer mobileVerificationAttempts,
			Integer resendCount, Integer updatedBy,Long updatedById) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobileType = mobileType;
		this.mobilePin = mobilePin;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
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


	public Integer getMobileType() {
		return mobileType;
	}


	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}


	public Integer getMobilePin() {
		return mobilePin;
	}



	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}



	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}



	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
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
		return "UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO [customerId="
				+ customerId
				+ ", customerType="
				+ customerType
				+ ", mobileType="
				+ mobileType
				+ ", mobilePin="
				+ mobilePin
				+ ", mobileVerificationAttempts="
				+ mobileVerificationAttempts
				+ ", resendCount="
				+ resendCount
				+ ", updatedBy="
				+ updatedBy
				+ ", updatedById=" + updatedById + "]";
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
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result
				+ ((mobileType == null) ? 0 : mobileType.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
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
		UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO other = (UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO) obj;
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
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (mobileType == null) {
			if (other.mobileType != null)
				return false;
		} else if (!mobileType.equals(other.mobileType))
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
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
