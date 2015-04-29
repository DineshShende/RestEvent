package com.projectx.rest.controller.async;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.domain.comndto.ResponseDTO;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.repository.async.RetriggerDetailsRepository;

@RestController
@RequestMapping(value="/retrigger")
public class RetriggerDetailsController {

	
	@Autowired
	RetriggerDetailsRepository retriggerDetailsRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ResponseDTO<RetriggerDetails>> save(@Valid @RequestBody RetriggerDetails retriggerDetails,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors())
			return new ResponseEntity<ResponseDTO<RetriggerDetails>>(new ResponseDTO<RetriggerDetails>("VALIDATION_FAILED", null),HttpStatus.OK);
		
		RetriggerDetails savedEntity=null;
		
		try{
			savedEntity=retriggerDetailsRepository.save(retriggerDetails);
		}catch(ValidationFailedException e)
		{
			return new ResponseEntity<ResponseDTO<RetriggerDetails>>(new ResponseDTO<RetriggerDetails>(e.getMessage(), null),HttpStatus.OK); 
		}
		
		
		return new ResponseEntity<ResponseDTO<RetriggerDetails>>(new ResponseDTO<RetriggerDetails>("", savedEntity), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET)
	public ResponseEntity<ResponseDTO<List<RetriggerDetails>>> findAll()
	{
		List<RetriggerDetails> list=new ArrayList<RetriggerDetails>();
		
		Iterable<RetriggerDetails> iterator=retriggerDetailsRepository.findAll();
		
		iterator.forEach(e->list.add(e));
		
		return new ResponseEntity<ResponseDTO<List<RetriggerDetails>>>(new ResponseDTO<List<RetriggerDetails>>("",list), HttpStatus.OK);
	}

	@RequestMapping(value="/deleteById/{retriggerId}",method=RequestMethod.GET)
	public ResponseEntity<ResponseDTO<Boolean>> deleteById(@PathVariable Long retriggerId)
	{
		try{
		retriggerDetailsRepository.deleteById(retriggerId);
		return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("",true), HttpStatus.OK);
		}catch(DataIntegrityViolationException e)
		{
			return new ResponseEntity<ResponseDTO<Boolean>>(new ResponseDTO<Boolean>("NOT_FOUND",false), HttpStatus.OK);
		}
		
		
	}
	
	@RequestMapping(value="/clearTestData",method=RequestMethod.GET)
	public Boolean clearTestData()
	{
		
			retriggerDetailsRepository.clearTestData();
		
		return true;
	}
	
	
}
