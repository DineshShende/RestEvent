package com.projectx.rest.services.async;

import com.projectx.rest.domain.async.RetriggerDTO;

public interface RetriggerService {

	public void requestRetry(RetriggerDTO retriggerDTO);
	
}
