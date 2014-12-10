package com.projectx.data.quickregister.domain;

import java.util.Date;

public class UpdateEmailHashDTO {

	private Long customerId;
	private String emailHash;
	private Date updateTime;
	
	public UpdateEmailHashDTO() {
	
	}

	public UpdateEmailHashDTO(Long customerId, String emailHash, Date updateTime) {
		super();
		this.customerId = customerId;
		this.emailHash = emailHash;
		this.updateTime = updateTime;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "UpdateEmailHashDTO [customerId=" + customerId + ", emailHash="
				+ emailHash + ", updateTime=" + updateTime + "]";
	}

		
	
}
