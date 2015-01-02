package com.projectx.rest.repository.completeregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;

@Repository
public interface DocumentDetailsRepository {

	DocumentDetails saveCustomerDocument(DocumentDetails customerDocument);
	
	DocumentDetails getCustomerDocumentByCustomerId(DocumentKey documentKey);

	Integer count();
	
	Boolean clearTestData();
}
