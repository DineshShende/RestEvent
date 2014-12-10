package com.projectx.web.domain.quickregister;

public class VerifyMobileDTO {
	
	Long customerId;
	
	Long mobile;
	
	Integer mobilePin;
	

	public VerifyMobileDTO() {
	
	}


	public VerifyMobileDTO(Long customerId, Long mobile, Integer mobilePin) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
		this.mobilePin = mobilePin;
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


	@Override
	public String toString() {
		return "VerifyMobileDTO [customerId=" + customerId + ", mobile="
				+ mobile + ", mobilePin=" + mobilePin + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
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
		VerifyMobileDTO other = (VerifyMobileDTO) obj;
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
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		return true;
	}
	
	
	

}
