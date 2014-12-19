package com.projectx.data.domain.quickregister;

import java.util.Date;

public class UpdateMobilePinDTO {

	private Long  customerId;
	private Integer mobilePin;
	private Date updateTime;
	
	public UpdateMobilePinDTO() {
	
	}

	public UpdateMobilePinDTO(Long customerId, Integer mobilePin,
			Date updateTime) {
		super();
		this.customerId = customerId;
		this.mobilePin = mobilePin;
		this.updateTime = updateTime;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UpdateMobilePinDTO [customerId=" + customerId + ", mobilePin="
				+ mobilePin + ", updateTime=" + updateTime + "]";
	}

	
	
	
}
