package com.projectx.data.domain.completeregister;

public class UpdateMobileVerificationStatusDTO {

	private Long customerId;
	
	private Long mobile;
	
	private Boolean status;

	public UpdateMobileVerificationStatusDTO() {

	}

	public UpdateMobileVerificationStatusDTO(Long customerId, Long mobile,
			Boolean status) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
		this.status = status;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "UpdateMobileVerificationStatusDTO [customerId=" + customerId
				+ ", mobile=" + mobile + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		UpdateMobileVerificationStatusDTO other = (UpdateMobileVerificationStatusDTO) obj;
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

		
	
}
