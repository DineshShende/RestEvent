package com.projectx.data.quickregister.domain;

import java.util.Date;

public class UpdateEmailHashAndMobilePinSentTimeDTO {

	private Long customerId;
	private Date emailSentTime;
	private Date mobilePinSentTime;
	
	public UpdateEmailHashAndMobilePinSentTimeDTO() {
	
	}

	public UpdateEmailHashAndMobilePinSentTimeDTO(Long customerId,
			Date emailSentTime, Date mobilePinSentTime) {
		super();
		this.customerId = customerId;
		this.emailSentTime = emailSentTime;
		this.mobilePinSentTime = mobilePinSentTime;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getEmailSentTime() {
		return emailSentTime;
	}

	public void setEmailSentTime(Date emailSentTime) {
		this.emailSentTime = emailSentTime;
	}

	public Date getMobilePinSentTime() {
		return mobilePinSentTime;
	}

	public void setMobilePinSentTime(Date mobilePinSentTime) {
		this.mobilePinSentTime = mobilePinSentTime;
	}

	@Override
	public String toString() {
		return "UpdateEmailHashAndMobilePinSentTimeDTO [customerId="
				+ customerId + ", emailSentTime=" + emailSentTime
				+ ", mobilePinSentTime=" + mobilePinSentTime + "]";
	}

	
	
}
