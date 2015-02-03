package com.projectx.data.domain.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.rest.domain.request.FreightRequestByVendor;

public class FreightRequestByVendorList {

	List<FreightRequestByVendor> requestList;

	public FreightRequestByVendorList() {
		super();
	}

	@JsonCreator
	public FreightRequestByVendorList(List<FreightRequestByVendor> requestList) {
		super();
		this.requestList = requestList;
	}

	public List<FreightRequestByVendor> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<FreightRequestByVendor> requestList) {
		this.requestList = requestList;
	}

	@Override
	public String toString() {
		return "FreightRequestByVendorList [requestList=" + requestList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((requestList == null) ? 0 : requestList.hashCode());
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
		FreightRequestByVendorList other = (FreightRequestByVendorList) obj;
		if (requestList == null) {
			if (other.requestList != null)
				return false;
		} else if (!requestList.equals(other.requestList))
			return false;
		return true;
	}
	
	
	
}
