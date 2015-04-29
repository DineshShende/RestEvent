package com.projectx.mvc.domain.handshake;

public class TriggerDealDTO {
	
	private Long freightRequestByCustomerId;

	private Long freightRequestByVendorId;
	
	private Integer requestedBy;
	
	private Long requestedById;

	
	
	public TriggerDealDTO() {
		super();
	}



	public TriggerDealDTO(Long freightRequestByCustomerId,
			Long freightRequestByVendorId, Integer requestedBy,
			Long requestedById) {
		super();
		this.freightRequestByCustomerId = freightRequestByCustomerId;
		this.freightRequestByVendorId = freightRequestByVendorId;
		this.requestedBy = requestedBy;
		this.requestedById = requestedById;
	}



	public Long getFreightRequestByCustomerId() {
		return freightRequestByCustomerId;
	}



	public void setFreightRequestByCustomerId(Long freightRequestByCustomerId) {
		this.freightRequestByCustomerId = freightRequestByCustomerId;
	}



	public Long getFreightRequestByVendorId() {
		return freightRequestByVendorId;
	}



	public void setFreightRequestByVendorId(Long freightRequestByVendorId) {
		this.freightRequestByVendorId = freightRequestByVendorId;
	}



	public Integer getRequestedBy() {
		return requestedBy;
	}



	public void setRequestedBy(Integer requestedBy) {
		this.requestedBy = requestedBy;
	}



	public Long getRequestedById() {
		return requestedById;
	}



	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}



	@Override
	public String toString() {
		return "TriggerDealDTO [freightRequestByCustomerId="
				+ freightRequestByCustomerId + ", freightRequestByVendorId="
				+ freightRequestByVendorId + ", requestedBy=" + requestedBy
				+ ", requestedById=" + requestedById + "]";
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
				+ ((requestedBy == null) ? 0 : requestedBy.hashCode());
		result = prime * result
				+ ((requestedById == null) ? 0 : requestedById.hashCode());
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
		TriggerDealDTO other = (TriggerDealDTO) obj;
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
		if (requestedBy == null) {
			if (other.requestedBy != null)
				return false;
		} else if (!requestedBy.equals(other.requestedBy))
			return false;
		if (requestedById == null) {
			if (other.requestedById != null)
				return false;
		} else if (!requestedById.equals(other.requestedById))
			return false;
		return true;
	}
	
	
	
}
