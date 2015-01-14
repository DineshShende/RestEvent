package com.projectx.rest.repository.completeregister;




import org.springframework.stereotype.Repository;

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;

@Repository
public interface CustomerDetailsRepository {

	 CustomerDetails save( CustomerDetails customerDetails);
	 
	 CustomerDetails findOne(Long customerId);
	/* 
	 CustomerDetails updateFirmAddress(UpdateAddressDTO addressDTO);
	 
	 CustomerDetails updateHomeAddress(UpdateAddressDTO addressDTO);
	*/ 
	 
	 Integer updateMobileVerificationStatus(UpdateMobileVerificationStatusDTO verificationStatusDTO);
	 
	 Integer updateSecondaryMobileVerificationStatus(UpdateMobileVerificationStatusDTO verificationStatusDTO);
	 
	 Integer updateEmailVerificationStatus(UpdateEmailVerificationStatusDTO verificationStatusDTO);
	 
	 Boolean deleteAll();
	 
	 Long count();
	 
	
}
