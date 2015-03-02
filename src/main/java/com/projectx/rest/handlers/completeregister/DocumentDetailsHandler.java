package com.projectx.rest.handlers.completeregister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;
import com.projectx.rest.services.completeregister.DocumentDetailsService;


@Component

public class DocumentDetailsHandler implements DocumentDetailsService {
	
	@Autowired
	DocumentDetailsRepository documentDetailsRepository;

	
	@Override
	
	public DocumentDetails saveCustomerDocument(
			DocumentDetails customerDocument) {
		
		customerDocument.setInsertTime(new Date());
		customerDocument.setUpdatedBy("CUST_ONLINE");
		customerDocument.setUpdateTime(new Date());
		
		DocumentDetails savedEntity=documentDetailsRepository.saveCustomerDocument(customerDocument);
		
		return savedEntity;
	}

	/* (non-Javadoc)
	 * @see com.projectx.rest.handlers.completeregister.DocumentDetailsService#getCustomerDocumentById(com.projectx.rest.domain.completeregister.DocumentKey)
	 */
	@Override
	
	public DocumentDetails getById(
			DocumentKey documentKey) throws DocumentDetailsNotFoundException{
		
		DocumentDetails fetchedEntity=documentDetailsRepository.getByCustomerId(documentKey);
		
		return fetchedEntity;
	}

	@Override
	public DocumentDetails updateDocument(DocumentKey key, byte[] document,
			String contentType) {
		
		DocumentDetails fetchedEntity=documentDetailsRepository.getByCustomerId(key);
		
		if(fetchedEntity!=null)
		{
			fetchedEntity.setDocument(document);
			fetchedEntity.setContentType(contentType);
			fetchedEntity.setVerificationRemark("NOT VERIFIED");
			fetchedEntity.setVerificationStatus(1);
			fetchedEntity.setUpdatedBy("CUST_ONLINE");
			fetchedEntity.setUpdateTime(new Date());
		
			DocumentDetails updatedEntity=documentDetailsRepository.saveCustomerDocument(fetchedEntity);
			
			return updatedEntity;
		}
		else
			return new DocumentDetails();
		
	}

	@Override
	public DocumentDetails updateVerificationStatusAndRemark(DocumentKey key,
			Integer verificationStatus, String verificationRemark) {
		
		DocumentDetails fetchedEntity=documentDetailsRepository.getByCustomerId(key);
		
		if(fetchedEntity!=null)
		{
			fetchedEntity.setVerificationStatus(verificationStatus);
			fetchedEntity.setVerificationRemark(verificationRemark);
			fetchedEntity.setUpdatedBy("CUST_ONLINE");
			fetchedEntity.setUpdateTime(new Date());
		
			DocumentDetails updatedEntity=documentDetailsRepository.saveCustomerDocument(fetchedEntity);
			
			return updatedEntity;
		}
		else
			return new DocumentDetails();
		
	}

	@Override
	public Integer count() {

		Integer count=documentDetailsRepository.count();
		
		return count;
	}

	@Override
	public Boolean clearTestData() {

		Boolean status=documentDetailsRepository.clearTestData();
		
		return status;
	}
	
}
