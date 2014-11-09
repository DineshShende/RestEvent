package com.projectx.rest.repository;

import org.springframework.stereotype.Repository;

import com.projectx.rest.domain.CustomerDocument;

@Repository
public interface CustomerDocumentRepository {

	CustomerDocument saveCustomerDocument(CustomerDocument customerDocument);
	
	CustomerDocument getCustomerDocumentByCustomerId(Long customerId);
	
}
