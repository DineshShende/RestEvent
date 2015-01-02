package com.projectx.rest.repositoryfixture.completeregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;

@Component
@Profile(value="Test")
public class DocumentDetailsMemRepository implements DocumentDetailsRepository {

	Map<DocumentKey,DocumentDetails> documentList=new HashMap<DocumentKey,DocumentDetails>(); 
	
	@Override
	public DocumentDetails saveCustomerDocument(DocumentDetails customerDocument) {
		
		documentList.put(customerDocument.getKey(), customerDocument);
		
		return customerDocument;
	}

	@Override
	public DocumentDetails getCustomerDocumentByCustomerId(
			DocumentKey documentKey) {

		DocumentDetails fetchedEntity=documentList.get(documentKey);
		
		if(fetchedEntity==null)
			return new DocumentDetails();
		
		return fetchedEntity;
		
	}

	@Override
	public Integer count() {

		Integer count=documentList.size();
		
		return count;
		
	}

	@Override
	public Boolean clearTestData() {

		documentList.clear();
		
		return true;
	}

}
