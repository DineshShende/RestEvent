package com.projectx.rest.controller.completeregister;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.mvc.domain.completeregister.UpdateDocumentDTO;
import com.projectx.mvc.domain.completeregister.UpdateDocumentVerificationStatusAndRemarkDTO;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotFoundException;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotSavedException;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.services.completeregister.DocumentDetailsService;


@RestController
@RequestMapping(value="/document")
public class DocumentDetailsController {
	
	@Autowired
	DocumentDetailsService documentDetailsService;

	@RequestMapping(value="/saveCustomerDocument",method=RequestMethod.POST)
	public ResponseEntity<DocumentDetails> saveCustomerDocument(@Valid @RequestBody DocumentDetails customerDocument,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			DocumentDetails savedEntity=documentDetailsService.saveCustomerDocument(customerDocument);
			return new ResponseEntity<DocumentDetails>(savedEntity, HttpStatus.OK);
		}catch(ValidationFailedException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}catch (DocumentDetailsNotSavedException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@RequestMapping(value="/getCustomerDocumentById",method=RequestMethod.POST)
	public ResponseEntity<DocumentDetails> getCustomerDocumentById(@Valid @RequestBody DocumentKey documentKey,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		ResponseEntity<DocumentDetails> result=null;
		try{
			DocumentDetails fetchedEntity=documentDetailsService.getById(documentKey);
			result=new ResponseEntity<DocumentDetails>(fetchedEntity, HttpStatus.FOUND);
		}catch(DocumentDetailsNotFoundException e)
		{
			result=new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return result;
	}

	@RequestMapping(value="/updateDocument",method=RequestMethod.POST)
	public ResponseEntity<DocumentDetails> updateDocument(@Valid @RequestBody UpdateDocumentDTO updateDocumentDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			DocumentDetails updatedEntity=documentDetailsService
					.updateDocument(updateDocumentDTO.getKey(), updateDocumentDTO.getDocument(), updateDocumentDTO.getContentType(),
							updateDocumentDTO.getRequestedBy());
			
			return new ResponseEntity<DocumentDetails>(updatedEntity, HttpStatus.OK);
			
		}catch(DocumentDetailsNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	@RequestMapping(value="/updateVerificationStatusAndRemark",method=RequestMethod.POST)
	public ResponseEntity<DocumentDetails> updateDocumentVerificationDetails(@Valid @RequestBody UpdateDocumentVerificationStatusAndRemarkDTO 
			updateDocumentDTO,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		
		try{
			DocumentDetails updatedEntity=documentDetailsService
					.updateVerificationStatusAndRemark(updateDocumentDTO.getKey(), updateDocumentDTO.getVerificationStatus(), 
							updateDocumentDTO.getVerificationRemark(),updateDocumentDTO.getRequestedBy());
			return new ResponseEntity<DocumentDetails>(updatedEntity, HttpStatus.OK);
			
		}catch(DocumentDetailsNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
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
