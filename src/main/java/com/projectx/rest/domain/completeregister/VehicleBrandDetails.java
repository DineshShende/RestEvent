package com.projectx.rest.domain.completeregister;



public class VehicleBrandDetails {


	private Long vehicleBrandId;
	
	private VehicleTypeDetails vehicleTypeId;
	
	private String vehicleBrandName;
	
	private String modelNumber;

	public VehicleBrandDetails() {

	}

	public VehicleBrandDetails(Long vehicleBrandId,
			VehicleTypeDetails vehicleTypeId, String vehicleBrandName,
			String modelNumber) {

		this.vehicleBrandId = vehicleBrandId;
		this.vehicleTypeId = vehicleTypeId;
		this.vehicleBrandName = vehicleBrandName;
		this.modelNumber = modelNumber;
	}

	public Long getVehicleBrandId() {
		return vehicleBrandId;
	}

	public void setVehicleBrandId(Long vehicleBrandId) {
		this.vehicleBrandId = vehicleBrandId;
	}

	public VehicleTypeDetails getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(VehicleTypeDetails vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicleBrandName() {
		return vehicleBrandName;
	}

	public void setVehicleBrandName(String vehicleBrandName) {
		this.vehicleBrandName = vehicleBrandName;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	@Override
	public String toString() {
		return "VehicleBrandDetails [vehicleBrandId=" + vehicleBrandId
				+ ", vehicleTypeId=" + vehicleTypeId + ", vehicleBrandName="
				+ vehicleBrandName + ", modelNumber=" + modelNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((modelNumber == null) ? 0 : modelNumber.hashCode());
		result = prime * result
				+ ((vehicleBrandId == null) ? 0 : vehicleBrandId.hashCode());
		result = prime
				* result
				+ ((vehicleBrandName == null) ? 0 : vehicleBrandName.hashCode());
		result = prime * result
				+ ((vehicleTypeId == null) ? 0 : vehicleTypeId.hashCode());
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
		VehicleBrandDetails other = (VehicleBrandDetails) obj;
		if (modelNumber == null) {
			if (other.modelNumber != null)
				return false;
		} else if (!modelNumber.equals(other.modelNumber))
			return false;
		if (vehicleBrandId == null) {
			if (other.vehicleBrandId != null)
				return false;
		} else if (!vehicleBrandId.equals(other.vehicleBrandId))
			return false;
		if (vehicleBrandName == null) {
			if (other.vehicleBrandName != null)
				return false;
		} else if (!vehicleBrandName.equals(other.vehicleBrandName))
			return false;
		if (vehicleTypeId == null) {
			if (other.vehicleTypeId != null)
				return false;
		} else if (!vehicleTypeId.equals(other.vehicleTypeId))
			return false;
		return true;
	}
	
	
	
}
