package com.projectx.data.domain.quickregister;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

public class MobilePinPasswordList {

	List<MobilePinPasswordDTO> list;

	
	
	public MobilePinPasswordList() {
	}


	@JsonCreator
	public MobilePinPasswordList(List<MobilePinPasswordDTO> list) {
		this.list = list;
	}


	public List<MobilePinPasswordDTO> getList() {
		return list;
	}


	public void setList(List<MobilePinPasswordDTO> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "MobilePinPasswordList [list=" + list + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
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
		MobilePinPasswordList other = (MobilePinPasswordList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	
	
}
