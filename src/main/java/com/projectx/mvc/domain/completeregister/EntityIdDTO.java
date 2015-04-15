package com.projectx.mvc.domain.completeregister;

public class EntityIdDTO {
	
	private Long entityId;

	public EntityIdDTO() {

	}

	public EntityIdDTO(Long entityId) {
		super();
		this.entityId = entityId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	@Override
	public String toString() {
		return "EntityIdDTO [entityId=" + entityId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityId == null) ? 0 : entityId.hashCode());
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
		EntityIdDTO other = (EntityIdDTO) obj;
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		return true;
	}
	
	

}
