package com.projectx.data.domain.async;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.domain.comndto.ResponseDTO;

public class RetriggerList {

	//Comment to check git updates
	List<RetriggerDetails> list;

	@JsonCreator
	public RetriggerList(List<RetriggerDetails> list) {
		super();
		this.list = list;
	} 

	public RetriggerList() {
		super();
	}

	public List<RetriggerDetails> getList() {
		return list;
	}

	public void setList(List<RetriggerDetails> list) {
		this.list = list;
	}

	
	
}
