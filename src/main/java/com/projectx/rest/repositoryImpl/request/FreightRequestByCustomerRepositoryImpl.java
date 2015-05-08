package com.projectx.rest.repositoryImpl.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.projectx.data.domain.request.FreightRequestByCustomerList;
import com.projectx.data.domain.request.FreightRequestByVendorWithRequiredAllocationStatus;
import com.projectx.data.domain.request.UpdateReservationStatus;
import com.projectx.rest.domain.comndto.ResponseDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.exception.repository.completeregister.ValidationFailedException;
import com.projectx.rest.exception.repository.quickregister.ResourceAlreadyPresentException;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;

@Component
@Profile(value={"Dev","Prod"})
@PropertySource(value="classpath:/application.properties")
public class FreightRequestByCustomerRepositoryImpl implements
		FreightRequestByCustomerRepository {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Environment env;

	
	
	@Override
	public FreightRequestByCustomer save(
			FreightRequestByCustomer freightRequestByCustomer) throws ResourceAlreadyPresentException,ValidationFailedException{

		HttpEntity<FreightRequestByCustomer> entity=new HttpEntity<FreightRequestByCustomer>(freightRequestByCustomer);
		
		ResponseEntity<FreightRequestByCustomer> result=null; 
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer",HttpMethod.POST,
					entity, FreightRequestByCustomer.class);

		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.CREATED)
			return result.getBody();
		else
			throw new ResourceAlreadyPresentException();

	}

	@Override
	public FreightRequestByCustomer getById(Long requestId) throws ResourceNotFoundException{
		

		ResponseEntity<FreightRequestByCustomer> result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer/getById/"+requestId,
				HttpMethod.GET,null,FreightRequestByCustomer.class);;
		
		if(result.getStatusCode()==HttpStatus.FOUND)		
			return result.getBody();
		else
			throw new ResourceNotFoundException();

	}

	@Override
	public Boolean deleteById(Long requestId) {

		ResponseEntity<ResponseDTO<Boolean>> result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer/deleteById/"+requestId,
				HttpMethod.GET,null,new ParameterizedTypeReference<ResponseDTO<Boolean>>() {});
		
		if(result.getStatusCode()==HttpStatus.OK && result.getBody().getErrorMessage().equals(""))
				return result.getBody().getResult();
		else
			throw new ResourceNotFoundException(result.getBody().getErrorMessage());
		
	}



	@Override
	public List<FreightRequestByCustomer> findByCustomerId(Long customerId) {
	
		
		FreightRequestByCustomerList result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/findByCustomer/"+customerId,
				FreightRequestByCustomerList.class);
		
		return result.getRequestList();

		
	}

	@Override
	public List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(
			FreightRequestByVendor freightRequestByVendor,String allocationStatus) {
		
		FreightRequestByVendorWithRequiredAllocationStatus statusDTO=
				new FreightRequestByVendorWithRequiredAllocationStatus(allocationStatus, freightRequestByVendor);

		FreightRequestByCustomerList result=restTemplate.postForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/getMatchingCustReqForVendorReq",
				statusDTO, FreightRequestByCustomerList.class);
				
				
		return result.getRequestList();
	}

	@Override
	public Boolean clearTestData() {

		Boolean result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/clearTestData",
				Boolean.class);
		
		return result;
		
	}

	@Override
	public Integer count() {

		Integer result=restTemplate.getForObject(env.getProperty("data.url")+"/request/freightByRequestCustomer/count",
				Integer.class);
		
		return result;
		
	}

	@Override
	public Integer updateReservationStatusWithReservedFor(
			Long freightRequestByCustomerId, String oldStatus,
			String reservationStatus, Long reservedBy,Integer updatedBy,Long updatedById) {
		
		UpdateReservationStatus updateReservationStatus=new UpdateReservationStatus(freightRequestByCustomerId,
				oldStatus, reservationStatus, reservedBy,updatedBy,updatedById);
		
		HttpEntity<UpdateReservationStatus> entity=new HttpEntity<UpdateReservationStatus>(updateReservationStatus);
		
		ResponseEntity<Integer> result=null;
		
		try{
			result=restTemplate.exchange(env.getProperty("data.url")+"/request/freightByRequestCustomer/updateReservationStatus",
					HttpMethod.POST, entity, Integer.class);
		}catch(RestClientException e)
		{
			throw new ValidationFailedException();
		}
		
		if(result.getStatusCode()==HttpStatus.OK)
			return result.getBody();
		
		throw new ResourceNotFoundException();
	}
	
}
