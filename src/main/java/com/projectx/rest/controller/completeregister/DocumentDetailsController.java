package com.projectx.rest.controller.completeregister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.UpdateDocumentDTO;
import com.projectx.mvc.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.services.completeregister.DocumentDetailsService;


@RestController
@RequestMapping(value="/document")
public class DocumentDetailsController {
	
	@Autowired
	DocumentDetailsService documentDetailsService;

	@RequestMapping(value="/saveCustomerDocument",method=RequestMethod.POST)
	public DocumentDetails saveCustomerDocument(@RequestBody DocumentDetails customerDocument)
	{
		DocumentDetails savedEntity=documentDetailsService.saveCustomerDocument(customerDocument);
		
		return savedEntity;
	}
	
	@RequestMapping(value="/getCustomerDocumentById",method=RequestMethod.POST)
	public DocumentDetails getCustomerDocumentById(@RequestBody DocumentKey documentKey)
	{
		DocumentDetails fetchedEntity=documentDetailsService.getCustomerDocumentById(documentKey);
		
		return fetchedEntity;
	}

	@RequestMapping(value="/updateDocument",method=RequestMethod.POST)
	public DocumentDetails updateDocument(@RequestBody UpdateDocumentDTO updateDocumentDTO)
	{
		DocumentDetails updatedEntity=documentDetailsService
				.updateDocument(updateDocumentDTO.getKey(), updateDocumentDTO.getDocument(), updateDocumentDTO.getContentType());
		
		return updatedEntity;
	}
	
	@RequestMapping(value="/updateVerificationStatusAndRemark",method=RequestMethod.POST)
	public DocumentDetails updateDocumentVerificationDetails(@RequestBody UpdateDocumentVerificationStatusAndRemarkDTO updateDocumentDTO)
	{
		DocumentDetails updatedEntity=documentDetailsService
				.updateVerificationStatusAndRemark(updateDocumentDTO.getKey(), updateDocumentDTO.getVerificationStatus(), updateDocumentDTO.getVerificationRemark());
		
		return updatedEntity;
	}
	
	
	@RequestMapping(value="/count",method=RequestMethod.GET)
	public Integer count()
	{
		Integer result=documentDetailsService.count();
		
		return result;
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTest()
	{
		documentDetailsService.clearTestData();
		
		return true;
	}
	
}
