package com.projectx.data.domain.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projectx.rest.domain.request.TestRequest;



public class RequestTestList {

	List<TestRequest> requestList;

	@JsonCreator
	public RequestTestList(List<TestRequest> requestList) {
		super();
		this.requestList = requestList;
	}

	public List<TestRequest> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<TestRequest> requestList) {
		this.requestList = requestList;
	}
	
	
	
}
