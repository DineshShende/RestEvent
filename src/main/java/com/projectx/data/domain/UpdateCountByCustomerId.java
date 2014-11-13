package com.projectx.data.domain;

public class UpdateCountByCustomerId {

	private Long customerId;
	
	private Integer count;

	
	
	
	public UpdateCountByCustomerId() {
		super();
	}




	public UpdateCountByCustomerId(Long customerId, Integer count) {
		super();
		this.customerId = customerId;
		this.count = count;
	}




	public Long getCustomerId() {
		return customerId;
	}




	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}




	public Integer getCount() {
		return count;
	}




	public void setCount(Integer count) {
		this.count = count;
	}




	@Override
	public String toString() {
		return "UpdateCountByCustomerId [customerId=" + customerId + ", count="
				+ count + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
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
		UpdateCountByCustomerId other = (UpdateCountByCustomerId) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}
	
	
	
	
	
}
