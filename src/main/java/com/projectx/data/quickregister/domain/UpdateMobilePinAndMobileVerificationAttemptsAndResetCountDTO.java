package com.projectx.data.quickregister.domain;


public class UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO {

	private Long customerId;
	
	private Integer customerType;
	private Long mobile;
	private Integer mobilePin;
	private Integer mobileVerificationAttempts;
	private Integer resendCount;
	
	
	
	public UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO() {
		super();
	}


	public UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO(
			Long customerId, Integer customerType, Long mobile,
			Integer mobilePin, Integer mobileVerificationAttempts,
			Integer resendCount) {
		super();
		this.customerId = customerId;
		this.customerType = customerType;
		this.mobile = mobile;
		this.mobilePin = mobilePin;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
		this.resendCount = resendCount;
	}





	public Long getCustomerId() {
		return customerId;
	}



	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}



	public Long getMobile() {
		return mobile;
	}



	public void setMobile(Long mobile) {
		this.mobile = mobile;
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


	@Override
	public String toString() {
		return "UpdateMobilePinAndMobileVerificationAttemptsAndResetCountDTO [customerId="
				+ customerId
				+ ", customerType="
				+ customerType
				+ ", mobile="
				+ mobile
				+ ", mobilePin="
				+ mobilePin
				+ ", mobileVerificationAttempts="
				+ mobileVerificationAttempts
				+ ", resendCount=" + resendCount + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerType == null) ? 0 : customerType.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
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
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
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
		return true;
	}


		
}
