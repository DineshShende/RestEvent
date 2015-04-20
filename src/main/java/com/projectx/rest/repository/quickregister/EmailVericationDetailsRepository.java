package com.projectx.rest.repository.quickregister;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.EmailVerificationDetails;
import com.projectx.rest.domain.quickregister.EmailVerificationDetailsKey;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.EmailVerificationDetailNotFoundException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;


@Repository
public interface EmailVericationDetailsRepository {

	EmailVerificationDetails save(EmailVerificationDetails mobileVerificationDetails)
						throws ResourceAlreadyPresentException,ValidationFailedException;
	
	EmailVerificationDetails getByEntityIdTypeAndEmailType(Long customerId,Integer customerType,Integer emailType) throws EmailVerificationDetailNotFoundException;
	
	EmailVerificationDetails getByEmail(String email) throws EmailVerificationDetailNotFoundException;
	
	Integer resetEmailHashAndEmailHashSentTime(Long customerId,Integer customerType,Integer emailType,String emailHash,Date emailHashSentTime,
			Integer resetCount,Integer updatedBy,Long updatedById);
	
	Integer incrementResendCountByCustomerIdAndEmail(Long customerId,Integer customerType,Integer emailType,
			Integer updatedBy,Long updatedById);
	
	Long count();
	
	Boolean delete(EmailVerificationDetailsKey key);
	
	Boolean clearTestData();
	
}
