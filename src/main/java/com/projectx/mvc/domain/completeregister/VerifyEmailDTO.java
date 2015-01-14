package com.projectx.mvc.domain.completeregister;

public class VerifyEmailDTO {

	private Long entityId;
	
	private Integer entityType;
	
	private Integer emailType;
	
	private String emailHash;

	public VerifyEmailDTO() {

	}

	public VerifyEmailDTO(Long entityId, Integer entityType, Integer emailType,
			String emailHash) {
		super();
		this.entityId = entityId;
		this.entityType = entityType;
		this.emailType = emailType;
		this.emailHash = emailHash;
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

	public Integer getEmailType() {
		return emailType;
	}

	public void setEmailType(Integer emailType) {
		this.emailType = emailType;
	}

	public String getEmailHash() {
		return emailHash;
	}



	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	@Override
	public String toString() {
		return "VerifyEmailDTO [entityId=" + entityId + ", entityType="
				+ entityType + ", emailType=" + emailType + ", emailHash="
				+ emailHash + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailHash == null) ? 0 : emailHash.hashCode());
		result = prime * result
				+ ((emailType == null) ? 0 : emailType.hashCode());
		result = prime * result
				+ ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result
				+ ((entityType == null) ? 0 : entityType.hashCode());
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
		VerifyEmailDTO other = (VerifyEmailDTO) obj;
		if (emailHash == null) {
			if (other.emailHash != null)
				return false;
		} else if (!emailHash.equals(other.emailHash))
			return false;
		if (emailType == null) {
			if (other.emailType != null)
				return false;
		} else if (!emailType.equals(other.emailType))
			return false;
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
		return true;
	}


	
}
