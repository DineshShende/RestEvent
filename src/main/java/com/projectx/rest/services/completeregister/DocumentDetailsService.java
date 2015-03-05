package com.projectx.rest.services.completeregister;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotFoundException;

public interface DocumentDetailsService {

	public DocumentDetails saveCustomerDocument(DocumentDetails customerDocument);

	public DocumentDetails getById(DocumentKey documentKey) throws DocumentDetailsNotFoundException;
	
	public DocumentDetails updateDocument(DocumentKey key,byte[] document,String contentType,String requestedBy) 
			throws DocumentDetailsNotFoundException;
	
	public DocumentDetails updateVerificationStatusAndRemark(DocumentKey key,Integer verificationStatus,
			String verificationRemark,String requestedBy)throws DocumentDetailsNotFoundException;
	
	public Integer count();
	
	public Boolean clearTestData();

}