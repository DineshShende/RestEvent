package com.projectx.data.domain.completeregister;

public class UpdateEmailVerificationStatusUpdatedByDTO {

	private Long customerId;
	
	private String email;
	
	private Boolean status;
	
	private Integer updatedBy;
	
	private Long updatedById;
	

	public UpdateEmailVerificationStatusUpdatedByDTO() {

	}

	
	public UpdateEmailVerificationStatusUpdatedByDTO(Long customerId,
			String email, Boolean status, Integer updatedBy,Long updatedById) {
		super();
		this.customerId = customerId;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
		return "UpdateEmailVerificationStatusUpdatedByDTO [customerId="
				+ customerId + ", email=" + email + ", status=" + status
				+ ", updatedBy=" + updatedBy + ", updatedById=" + updatedById
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		UpdateEmailVerificationStatusUpdatedByDTO other = (UpdateEmailVerificationStatusUpdatedByDTO) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
