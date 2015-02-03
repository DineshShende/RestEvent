package com.projectx.rest.domain.request;

import java.util.Date;





import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;


public class FreightRequestByVendor {

	private Long requestId;
	
	private String vehicleRegistrationNumber;
	
	private Integer source;
	
	private Integer destination;
	
	private Long driverId;
	
	private Date availableDate;
	
	private String availableTime;
	
	private Integer pickupRangeInKm;
	
	private Long vendorId;


	private String status;


	private Date insertTime;
	
	private Date updateTime;
	
	private String updatedBy;
	

	
	public FreightRequestByVendor() {

	}

	public FreightRequestByVendor(String vehicleRegistrationNumber, Integer source,
			Integer destination, Long driverId, Date availableDate,
			String availableTime, Integer pickupRangeInKm,Long vendorId,String status, Date insertTime,
			Date updateTime, String updatedBy) {
		super();
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
		this.source = source;
		this.destination = destination;
		this.driverId = driverId;
		this.availableDate = availableDate;
		this.availableTime = availableTime;
		this.pickupRangeInKm = pickupRangeInKm;
		this.vendorId=vendorId;
		this.status=status;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
		this.updatedBy = updatedBy;
	}


	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public String getVehicleRegistrationNumber() {
		return vehicleRegistrationNumber;
	}

	public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
		this.vehicleRegistrationNumber = vehicleRegistrationNumber;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getDestination() {
		return destination;
	}

	public void setDestination(Integer destination) {
		this.destination = destination;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getAvailableDate() {
		return availableDate;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public Integer getPickupRangeInKm() {
		return pickupRangeInKm;
	}

	public void setPickupRangeInKm(Integer pickupRangeInKm) {
		this.pickupRangeInKm = pickupRangeInKm;
	}

	
	@JsonSerialize(using=JsonDateSerializer.class)	
	public Date getInsertTime() {
		return insertTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getUpdateTime() {
		return updateTime;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	
	
	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FreightRequestByVendor [requestId=" + requestId
				+ ", vehicleRegistrationNumber=" + vehicleRegistrationNumber
				+ ", source=" + source + ", destination=" + destination
				+ ", driverId=" + driverId + ", availableDate=" + availableDate
				+ ", availableTime=" + availableTime + ", pickupRangeInKm="
				+ pickupRangeInKm + ", vendorId=" + vendorId + ", status="
				+ status + ", insertTime=" + insertTime + ", updateTime="
				+ updateTime + ", updatedBy=" + updatedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((availableDate == null) ? 0 : availableDate.hashCode());
		result = prime * result
				+ ((availableTime == null) ? 0 : availableTime.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((driverId == null) ? 0 : driverId.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((pickupRangeInKm == null) ? 0 : pickupRangeInKm.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime
				* result
				+ ((vehicleRegistrationNumber == null) ? 0
						: vehicleRegistrationNumber.hashCode());
		result = prime * result
				+ ((vendorId == null) ? 0 : vendorId.hashCode());
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
		FreightRequestByVendor other = (FreightRequestByVendor) obj;
		if (availableDate == null) {
			if (other.availableDate != null)
				return false;
		} else if (!availableDate.equals(other.availableDate))
			return false;
		if (availableTime == null) {
			if (other.availableTime != null)
				return false;
		} else if (!availableTime.equals(other.availableTime))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (pickupRangeInKm == null) {
			if (other.pickupRangeInKm != null)
				return false;
		} else if (!pickupRangeInKm.equals(other.pickupRangeInKm))
			return false;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (vehicleRegistrationNumber == null) {
			if (other.vehicleRegistrationNumber != null)
				return false;
		} else if (!vehicleRegistrationNumber
				.equals(other.vehicleRegistrationNumber))
			return false;
		if (vendorId == null) {
			if (other.vendorId != null)
				return false;
		} else if (!vendorId.equals(other.vendorId))
			return false;
		return true;
	}

		
	
}
