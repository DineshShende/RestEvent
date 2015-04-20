package com.projectx.data.domain.completeregister;

public class UpdateMobileVerificationStatusUpdatedByDTO {

	private Long customerId;
	
	private Long mobile;
	
	private Boolean status;
	
	private Integer updatedBy;
	
	private Long updatedById;

	public UpdateMobileVerificationStatusUpdatedByDTO() {

	}

	
	public UpdateMobileVerificationStatusUpdatedByDTO(Long customerId,
			Long mobile, Boolean status, Integer updatedBy,Long updatedById) {
		super();
		this.customerId = customerId;
		this.mobile = mobile;
		this.status = status;
		this.updatedBy = updatedBy;
		this.updatedById=updatedById;
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



	public Integer getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}


	public Long getUpdatedById() {
		return updatedById;
	}


	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}


	@Override
	public String toString() {
		return "UpdateMobileVerificationStatusUpdatedByDTO [customerId="
				+ customerId + ", mobile=" + mobile + ", status=" + status
				+ ", updatedBy=" + updatedBy + ", updatedById=" + updatedById
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((updatedById == null) ? 0 : updatedById.hashCode());
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
		UpdateMobileVerificationStatusUpdatedByDTO other = (UpdateMobileVerificationStatusUpdatedByDTO) obj;
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
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedById == null) {
			if (other.updatedById != null)
				return false;
		} else if (!updatedById.equals(other.updatedById))
			return false;
		return true;
	}


	
}
