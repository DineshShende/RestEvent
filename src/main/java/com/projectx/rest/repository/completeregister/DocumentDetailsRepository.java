package com.projectx.rest.repository.completeregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotSavedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;

@Repository
public interface DocumentDetailsRepository {

	DocumentDetails saveCustomerDocument(DocumentDetails customerDocument) throws DocumentDetailsNotSavedException,ValidationFailedException;
	
	DocumentDetails getByCustomerId(DocumentKey documentKey) throws DocumentDetailsNotFoundException;

	Integer count();
	
	Boolean clearTestData();
}
