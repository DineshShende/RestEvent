package com.projectx.rest.repository.completeregister;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.quickregister.CustomerDocument;

@Repository
public interface CustomerDocumentRepository {

	CustomerDocument saveCustomerDocument(CustomerDocument customerDocument);
	
	CustomerDocument getCustomerDocumentByCustomerId(Long customerId);
	
}
