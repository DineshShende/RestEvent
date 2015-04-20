package com.projectx.data.domain.request;

public class UpdateReservationStatus {
	
	private Long entityIdTobeReserved;
	
	private String oldStatus;
	
	private String newStatus;
	
	private Long entityIdTobeReservedFor;

	private Integer updatedBy;
	
	private Long updatedById;
	
	
	public UpdateReservationStatus() {

	}




	public UpdateReservationStatus(Long entityIdTobeReserved, String oldStatus,
			String newStatus, Long entityIdTobeReservedFor,Integer updatedBy,Long updatedById) {

		this.entityIdTobeReserved = entityIdTobeReserved;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.entityIdTobeReservedFor = entityIdTobeReservedFor;
		this.updatedBy=updatedBy;
		this.updatedById=updatedById;
	}




	public Long getEntityIdTobeReserved() {
		return entityIdTobeReserved;
	}




	public void setEntityIdTobeReserved(Long entityIdTobeReserved) {
		this.entityIdTobeReserved = entityIdTobeReserved;
	}




	public String getOldStatus() {
		return oldStatus;
	}




	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}




	public String getNewStatus() {
		return newStatus;
	}




	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}




	public Long getEntityIdTobeReservedFor() {
		return entityIdTobeReservedFor;
	}




	public void setEntityIdTobeReservedFor(Long entityIdTobeReservedFor) {
		this.entityIdTobeReservedFor = entityIdTobeReservedFor;
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
		return "UpdateReservationStatus [entityIdTobeReserved="
				+ entityIdTobeReserved + ", oldStatus=" + oldStatus
				+ ", newStatus=" + newStatus + ", entityIdTobeReservedFor="
				+ entityIdTobeReservedFor + ", updatedBy=" + updatedBy
				+ ", updatedById=" + updatedById + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((entityIdTobeReserved == null) ? 0 : entityIdTobeReserved
						.hashCode());
		result = prime
				* result
				+ ((entityIdTobeReservedFor == null) ? 0
						: entityIdTobeReservedFor.hashCode());
		result = prime * result
				+ ((newStatus == null) ? 0 : newStatus.hashCode());
		result = prime * result
				+ ((oldStatus == null) ? 0 : oldStatus.hashCode());
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
		UpdateReservationStatus other = (UpdateReservationStatus) obj;
		if (entityIdTobeReserved == null) {
			if (other.entityIdTobeReserved != null)
				return false;
		} else if (!entityIdTobeReserved.equals(other.entityIdTobeReserved))
			return false;
		if (entityIdTobeReservedFor == null) {
			if (other.entityIdTobeReservedFor != null)
				return false;
		} else if (!entityIdTobeReservedFor
				.equals(other.entityIdTobeReservedFor))
			return false;
		if (newStatus == null) {
			if (other.newStatus != null)
				return false;
		} else if (!newStatus.equals(other.newStatus))
			return false;
		if (oldStatus == null) {
			if (other.oldStatus != null)
				return false;
		} else if (!oldStatus.equals(other.oldStatus))
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
