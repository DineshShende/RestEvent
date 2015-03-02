package com.projectx.data.domain.completeregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.rest.domain.completeregister.VehicleDetails;

public class VehicleList {

	List<VehicleDetails> vehicleList;

	@JsonCreator
	public VehicleList(List<VehicleDetails> vehicleList) {
		super();
		this.vehicleList = vehicleList;
	}

	public List<VehicleDetails> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<VehicleDetails> vehicleList) {
		this.vehicleList = vehicleList;
	}
	
	
}
