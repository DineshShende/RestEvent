package com.projectx.rest.domain.ivr;

public class KooKooRequestEntity {
	
	
	private Long freightRequestByCustomerId;
	
	private Long freightRequestByVendorId;

	
	
	
	public KooKooRequestEntity() {

	}


	public KooKooRequestEntity(Long freightRequestByCustomerId,
			Long freightRequestByVendorId) {

		this.freightRequestByCustomerId = freightRequestByCustomerId;
		this.freightRequestByVendorId = freightRequestByVendorId;
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


	@Override
	public String toString() {
		return "KooKooResponseEntity [freightRequestByCustomerId="
				+ freightRequestByCustomerId + ", freightRequestByVendorId="
				+ freightRequestByVendorId + "]";
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
		KooKooRequestEntity other = (KooKooRequestEntity) obj;
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
		return true;
	}
	
	

}
