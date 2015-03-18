package com.projectx.rest.services.registercommon;

import com.projectx.rest.domain.registercommon.ForgetPasswordEntity;

public interface RegisterCommonService {

	ForgetPasswordEntity forgetPassword(String entity,String requestedBy);
	
	
}
