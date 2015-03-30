package com.projectx.rest.domain.request;

import java.util.Date;










import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.projectx.rest.util.annotation.FreightRequestByCustomerValid;
import com.projectx.rest.util.serializer.JsonDateDeSerializer;
import com.projectx.rest.util.serializer.JsonDateSerializer;

@FreightRequestByCustomerValid
public class FreightRequestByCustomer {

	private Long requestId;

	@NotNull
	private Integer source;
	
	@NotNull
	private Integer destination;
	
	@NotNull
	private Date pickupDate;
	
	@NotNull
	private Integer noOfVehicles;
	
	private Boolean isFullTruckLoad;
	
	private Boolean isLessThanTruckLoad;

	private Integer capacity;
	
	private String bodyType;
	
	private Integer grossWeight;
	
	private Integer length;
	
	private Integer width;
	
	private Integer height;
	
	private String vehicleBrand;

	private String model;
	
	private String commodity;
	
	private  String pickupTime;

	@NotNull
	private Long customerId;
	
	@NotNull
	private String allocationStatus;
	
	private Long allocatedFor;
	
	@NotNull
	private Date insertTime;
	
	@NotNull
	private Date updateTime;
	
	@NotNull
	private String updatedBy;
	
	public FreightRequestByCustomer() {

	}


	
	public FreightRequestByCustomer(Integer source,
			Integer destination, Date pickupDate, Integer noOfVehicles,
			Boolean isFullTruckLoad, Boolean isLessThanTruckLoad,
			Integer capacity, String bodyType, Integer grossWeight,
			Integer length, Integer width, Integer height, String vehicleBrand,
			String model, String commodity, String pickupTime, Long customerId,
			String allocationStatus, Long allocatedFor, Date insertTime,
			Date updateTime, String updatedBy) {
		super();
		
		this.source = source;
		this.destination = destination;
		this.pickupDate = pickupDate;
		this.noOfVehicles = noOfVehicles;
		this.isFullTruckLoad = isFullTruckLoad;
		this.isLessThanTruckLoad = isLessThanTruckLoad;
		this.capacity = capacity;
		this.bodyType = bodyType;
		this.grossWeight = grossWeight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.vehicleBrand = vehicleBrand;
		this.model = model;
		this.commodity = commodity;
		this.pickupTime = pickupTime;
		this.customerId = customerId;
		this.allocationStatus = allocationStatus;
		this.allocatedFor = allocatedFor;
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
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getPickupDate() {
		return pickupDate;
	}

	@JsonDeserialize(using = JsonDateDeSerializer.class)
	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Integer getNoOfVehicles() {
		return noOfVehicles;
	}

	public void setNoOfVehicles(Integer noOfVehicles) {
		this.noOfVehicles = noOfVehicles;
	}

	public Boolean getIsFullTruckLoad() {
		return isFullTruckLoad;
	}

	public void setIsFullTruckLoad(Boolean isFullTruckLoad) {
		this.isFullTruckLoad = isFullTruckLoad;
	}

	public Boolean getIsLessThanTruckLoad() {
		return isLessThanTruckLoad;
	}

	public void setIsLessThanTruckLoad(Boolean isLessThanTruckLoad) {
		this.isLessThanTruckLoad = isLessThanTruckLoad;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Integer getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Integer grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
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

	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	


	public String getAllocationStatus() {
		return allocationStatus;
	}



	public void setAllocationStatus(String allocationStatus) {
		this.allocationStatus = allocationStatus;
	}



	public Long getAllocatedFor() {
		return allocatedFor;
	}



	public void setAllocatedFor(Long allocatedFor) {
		this.allocatedFor = allocatedFor;
	}




	@Override
	public String toString() {
		return "FreightRequestByCustomer [requestId=" + requestId + ", source="
				+ source + ", destination=" + destination + ", pickupDate="
				+ pickupDate + ", noOfVehicles=" + noOfVehicles
				+ ", isFullTruckLoad=" + isFullTruckLoad
				+ ", isLessThanTruckLoad=" + isLessThanTruckLoad
				+ ", capacity=" + capacity + ", bodyType=" + bodyType
				+ ", grossWeight=" + grossWeight + ", length=" + length
				+ ", width=" + width + ", height=" + height + ", vehicleBrand="
				+ vehicleBrand + ", model=" + model + ", commodity="
				+ commodity + ", pickupTime=" + pickupTime + ", customerId="
				+ customerId + ", allocationStatus=" + allocationStatus
				+ ", allocatedFor=" + allocatedFor + ", insertTime="
				+ insertTime + ", updateTime=" + updateTime + ", updatedBy="
				+ updatedBy + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bodyType == null) ? 0 : bodyType.hashCode());
		result = prime * result
				+ ((capacity == null) ? 0 : capacity.hashCode());
		result = prime * result
				+ ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((destination == null) ? 0 : destination.hashCode());
		result = prime * result
				+ ((grossWeight == null) ? 0 : grossWeight.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result
				+ ((isFullTruckLoad == null) ? 0 : isFullTruckLoad.hashCode());
		result = prime
				* result
				+ ((isLessThanTruckLoad == null) ? 0 : isLessThanTruckLoad
						.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result
				+ ((noOfVehicles == null) ? 0 : noOfVehicles.hashCode());
		result = prime * result
				+ ((pickupDate == null) ? 0 : pickupDate.hashCode());
		result = prime * result
				+ ((pickupTime == null) ? 0 : pickupTime.hashCode());
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((allocationStatus == null) ? 0 : allocationStatus.hashCode());
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result
				+ ((vehicleBrand == null) ? 0 : vehicleBrand.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		FreightRequestByCustomer other = (FreightRequestByCustomer) obj;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
			return false;
		if (capacity == null) {
			if (other.capacity != null)
				return false;
		} else if (!capacity.equals(other.capacity))
			return false;
		if (commodity == null) {
			if (other.commodity != null)
				return false;
		} else if (!commodity.equals(other.commodity))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (grossWeight == null) {
			if (other.grossWeight != null)
				return false;
		} else if (!grossWeight.equals(other.grossWeight))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (isFullTruckLoad == null) {
			if (other.isFullTruckLoad != null)
				return false;
		} else if (!isFullTruckLoad.equals(other.isFullTruckLoad))
			return false;
		if (isLessThanTruckLoad == null) {
			if (other.isLessThanTruckLoad != null)
				return false;
		} else if (!isLessThanTruckLoad.equals(other.isLessThanTruckLoad))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (noOfVehicles == null) {
			if (other.noOfVehicles != null)
				return false;
		} else if (!noOfVehicles.equals(other.noOfVehicles))
			return false;
		if (pickupDate == null) {
			if (other.pickupDate != null)
				return false;
		} else if (!pickupDate.equals(other.pickupDate))
			return false;
		if (pickupTime == null) {
			if (other.pickupTime != null)
				return false;
		} else if (!pickupTime.equals(other.pickupTime))
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
		if (allocationStatus == null) {
			if (other.allocationStatus != null)
				return false;
		} else if (!allocationStatus.equals(other.allocationStatus))
			return false;
		if (allocatedFor == null) {
			if (other.allocatedFor != null)
				return false;
		} else if (!allocatedFor.equals(other.allocatedFor))
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
		if (vehicleBrand == null) {
			if (other.vehicleBrand != null)
				return false;
		} else if (!vehicleBrand.equals(other.vehicleBrand))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}


		
	
}
