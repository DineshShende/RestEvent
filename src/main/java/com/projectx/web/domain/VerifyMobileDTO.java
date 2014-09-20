package com.projectx.web.domain;

public class VerifyMobileDTO {
	
	Long mobile;
	
	Integer mobilePin;
	

	public VerifyMobileDTO() {
	
	}
	
	public VerifyMobileDTO(Long mobile, Integer mobilePin) {
		super();
		this.mobile = mobile;
		this.mobilePin = mobilePin;
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
		return "VerifyMobileDTO [mobile=" + mobile + ", mobilePin=" + mobilePin
				+ "]";
	}
	
	

}
