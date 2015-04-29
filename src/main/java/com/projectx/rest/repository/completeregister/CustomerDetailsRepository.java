package com.projectx.rest.repository.completeregister;




import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsAlreadyPresentException;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;

@Repository
public interface CustomerDetailsRepository {

	 CustomerDetails save( CustomerDetails customerDetails) throws CustomerDetailsAlreadyPresentException,ValidationFailedException;
	 
	 CustomerDetails findOne(Long customerId) throws CustomerDetailsNotFoundException;
	
	 Boolean deleteAll();
	 
	 Long count();
	 
	
}
