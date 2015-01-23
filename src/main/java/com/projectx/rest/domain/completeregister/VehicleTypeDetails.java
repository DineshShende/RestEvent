package com.projectx.rest.domain.completeregister;



public class VehicleTypeDetails {

	private Long vehicleTypeId;
	
	private String vehicleTypeName;
	
	public VehicleTypeDetails() {

	}

	public VehicleTypeDetails(Long vehicleTypeId, String vehicleTypeName) {

		this.vehicleTypeId = vehicleTypeId;
		this.vehicleTypeName = vehicleTypeName;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	@Override
	public String toString() {
		return "VehicleTypeDetails [vehicleTypeId=" + vehicleTypeId
				+ ", vehicleTypeName=" + vehicleTypeName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vehicleTypeId == null) ? 0 : vehicleTypeId.hashCode());
		result = prime * result
				+ ((vehicleTypeName == null) ? 0 : vehicleTypeName.hashCode());
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
		VehicleTypeDetails other = (VehicleTypeDetails) obj;
		if (vehicleTypeId == null) {
			if (other.vehicleTypeId != null)
				return false;
		} else if (!vehicleTypeId.equals(other.vehicleTypeId))
			return false;
		if (vehicleTypeName == null) {
			if (other.vehicleTypeName != null)
				return false;
		} else if (!vehicleTypeName.equals(other.vehicleTypeName))
			return false;
		return true;
	}

	
}
