package com.projectx.mvc.domain.completeregister;

public class VerifyMobileDTO {
	
	
	private Long entityId;
	
	private Integer entityType;
	
	private Integer mobileType;
	
	private Integer mobilePin;
	
	private String requestBy;

	public VerifyMobileDTO() {

	}

	public VerifyMobileDTO(Long entityId, Integer entityType,
			Integer mobileType, Integer mobilePin, String requestBy) {
		super();
		this.entityId = entityId;
		this.entityType = entityType;
		this.mobileType = mobileType;
		this.mobilePin = mobilePin;
		this.requestBy = requestBy;
	}





	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Integer getEntityType() {
		return entityType;
	}

	public void setEntityType(Integer entityType) {
		this.entityType = entityType;
	}

	public Integer getMobileType() {
		return mobileType;
	}

	public void setMobileType(Integer mobileType) {
		this.mobileType = mobileType;
	}

	public Integer getMobilePin() {
		return mobilePin;
	}

	public void setMobilePin(Integer mobilePin) {
		this.mobilePin = mobilePin;
	}
	
	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	@Override
	public String toString() {
		return "VerifyMobileDTO [entityId=" + entityId + ", entityType="
				+ entityType + ", mobileType=" + mobileType + ", mobilePin="
				+ mobilePin + ", requestBy=" + requestBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result
				+ ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result
				+ ((mobilePin == null) ? 0 : mobilePin.hashCode());
		result = prime * result
				+ ((mobileType == null) ? 0 : mobileType.hashCode());
		result = prime * result
				+ ((requestBy == null) ? 0 : requestBy.hashCode());
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
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		if (entityType == null) {
			if (other.entityType != null)
				return false;
		} else if (!entityType.equals(other.entityType))
			return false;
		if (mobilePin == null) {
			if (other.mobilePin != null)
				return false;
		} else if (!mobilePin.equals(other.mobilePin))
			return false;
		if (mobileType == null) {
			if (other.mobileType != null)
				return false;
		} else if (!mobileType.equals(other.mobileType))
			return false;
		if (requestBy == null) {
			if (other.requestBy != null)
				return false;
		} else if (!requestBy.equals(other.requestBy))
			return false;
		return true;
	}

		

}
