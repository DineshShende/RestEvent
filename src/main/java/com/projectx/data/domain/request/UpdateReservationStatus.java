package com.projectx.data.domain.request;

public class UpdateReservationStatus {
	
	private Long freightRequestByVendorId;
	
	private String oldStatus;
	
	private String newStatus;
	
	private Long freightRequestByCustomerId;

	
	
	public UpdateReservationStatus() {

	}



	public UpdateReservationStatus(Long freightRequestByVendorId,
			String oldStatus, String newStatus, Long freightRequestByCustomerId) {

		this.freightRequestByVendorId = freightRequestByVendorId;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.freightRequestByCustomerId = freightRequestByCustomerId;
	}



	public Long getFreightRequestByVendorId() {
		return freightRequestByVendorId;
	}



	public void setFreightRequestByVendorId(Long freightRequestByVendorId) {
		this.freightRequestByVendorId = freightRequestByVendorId;
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



	public Long getFreightRequestByCustomerId() {
		return freightRequestByCustomerId;
	}



	public void setFreightRequestByCustomerId(Long freightRequestByCustomerId) {
		this.freightRequestByCustomerId = freightRequestByCustomerId;
	}



	@Override
	public String toString() {
		return "UpdateReservationStatus [freightRequestByVendorId="
				+ freightRequestByVendorId + ", oldStatus=" + oldStatus
				+ ", newStatus=" + newStatus + ", freightRequestByCustomerId="
				+ freightRequestByCustomerId + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((freightRequestByCustomerId == null) ? 0
						: freightRequestByCustomerId.hashCode());
		result = prime
				* result
				+ ((freightRequestByVendorId == null) ? 0
						: freightRequestByVendorId.hashCode());
		result = prime * result
				+ ((newStatus == null) ? 0 : newStatus.hashCode());
		result = prime * result
				+ ((oldStatus == null) ? 0 : oldStatus.hashCode());
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
		if (freightRequestByCustomerId == null) {
			if (other.freightRequestByCustomerId != null)
				return false;
		} else if (!freightRequestByCustomerId
				.equals(other.freightRequestByCustomerId))
			return false;
		if (freightRequestByVendorId == null) {
			if (other.freightRequestByVendorId != null)
				return false;
		} else if (!freightRequestByVendorId
				.equals(other.freightRequestByVendorId))
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
		return true;
	}
	
	

}
