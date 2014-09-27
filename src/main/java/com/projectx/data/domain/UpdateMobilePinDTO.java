package com.projectx.data.domain;

public class UpdateMobilePinDTO {

	private Long customerId;
	private Integer mobilePin;
	
	public UpdateMobilePinDTO() {
	
	}
	
	public UpdateMobilePinDTO(Long customerId, Integer mobilePin) {
		super();
		this.customerId = customerId;
		this.mobilePin = mobilePin;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}
	
	
	
}
