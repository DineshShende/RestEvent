package com.projectx.data.domain;

public class UpdateMobileVerificationAttemptsDTO {

	private Long customerId;
	
	private Long mobile;
	
	private Integer mobileVerificationAttempts;

	public UpdateMobileVerificationAttemptsDTO() {
		super();
	}

	public UpdateMobileVerificationAttemptsDTO(Long customerId, Long mobile,
			Integer mobileVerificationAttempts) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
		this.mobileVerificationAttempts = mobileVerificationAttempts;
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

	public Integer getMobileVerificationAttempts() {
		return mobileVerificationAttempts;
	}

	public void setMobileVerificationAttempts(Integer mobileVerificationAttempts) {
		this.mobileVerificationAttempts = mobileVerificationAttempts;
	}

	@Override
	public String toString() {
		return "UpdateMobileVerificationAttemptsDTO [customerId=" + customerId
				+ ", mobile=" + mobile + ", mobileVerificationAttempts="
				+ mobileVerificationAttempts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime
				* result
				+ ((mobileVerificationAttempts == null) ? 0
						: mobileVerificationAttempts.hashCode());
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
		UpdateMobileVerificationAttemptsDTO other = (UpdateMobileVerificationAttemptsDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (mobileVerificationAttempts == null) {
			if (other.mobileVerificationAttempts != null)
				return false;
		} else if (!mobileVerificationAttempts
				.equals(other.mobileVerificationAttempts))
			return false;
		return true;
	}
	
	
	
	
}
