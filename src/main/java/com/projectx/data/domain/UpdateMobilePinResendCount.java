package com.projectx.data.domain;

public class UpdateMobilePinResendCount {
	
	private Long customerId;
	
	private Long mobile;
	
	private Integer resendCount;

	public UpdateMobilePinResendCount() {
		super();
	}

	public UpdateMobilePinResendCount(Long customerId, Long mobile,
			Integer resendCount) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
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

	public Integer getResendCount() {
		return resendCount;
	}

	public void setResendCount(Integer resendCount) {
		this.resendCount = resendCount;
	}

	@Override
	public String toString() {
		return "UpdateMobilePinResendCount [customerId=" + customerId
				+ ", mobile=" + mobile + ", resendCount=" + resendCount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		UpdateMobilePinResendCount other = (UpdateMobilePinResendCount) obj;
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
		if (resendCount == null) {
			if (other.resendCount != null)
				return false;
		} else if (!resendCount.equals(other.resendCount))
			return false;
		return true;
	}
	
	
	
	
	

}
