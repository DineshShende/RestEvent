package com.projectx.rest.repositoryfixture.completeregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateEmailVerificationStatusUpdatedByDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusUpdatedByDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.exception.repository.completeregister.CustomerDetailsAlreadyPresentException;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;


@Component
@Profile(value="Test")
public class CustomerDetailsMemRepository implements CustomerDetailsRepository {

	
	Map<Long,CustomerDetails> list=new HashMap<Long,CustomerDetails>();
	
	@Override
	public CustomerDetails save(CustomerDetails customerDetails) {

		for(Long key:list.keySet())
		{
			if(list.get(key).getMobile().equals(customerDetails.getMobile()) || list.get(key).getEmail().equals(customerDetails.getEmail()))
			{
				if(customerDetails.getCustomerId()!=key)
					throw new CustomerDetailsAlreadyPresentException();
			}
		}
		if(list.containsKey(customerDetails.getCustomerId()))
			list.replace(customerDetails.getCustomerId(), customerDetails);
		else
			list.put(customerDetails.getCustomerId(), customerDetails);
		
		return customerDetails;
	}

	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusUpdatedByDTO verificationStatusDTO) {

		CustomerDetails oldRecord=list.get(verificationStatusDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(verificationStatusDTO.getCustomerId());
		
			oldRecord.setIsMobileVerified(verificationStatusDTO.getStatus());

			list.put(verificationStatusDTO.getCustomerId(), oldRecord);
		
			return 1;
		}
		else
			return 0;


		
	}

	@Override
	public Integer updateSecondaryMobileVerificationStatus(
			UpdateMobileVerificationStatusUpdatedByDTO verificationStatusDTO) {

		CustomerDetails oldRecord=list.get(verificationStatusDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(verificationStatusDTO.getCustomerId());
		
			oldRecord.setMobile(verificationStatusDTO.getMobile());
			oldRecord.setIsSecondaryMobileVerified(verificationStatusDTO.getStatus());

			list.put(verificationStatusDTO.getCustomerId(), oldRecord);
		
			return 1;
		}
		else
			return 0;

	}

	@Override
	public Integer updateEmailVerificationStatus(
			UpdateEmailVerificationStatusUpdatedByDTO verificationStatusDTO) {
		
		CustomerDetails oldRecord=list.get(verificationStatusDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(verificationStatusDTO.getCustomerId());
		
			oldRecord.setEmail(verificationStatusDTO.getEmail());
			oldRecord.setIsEmailVerified(verificationStatusDTO.getStatus());

			list.put(verificationStatusDTO.getCustomerId(), oldRecord);
		
			return 1;
		}
		else
			return 0;

		
	}
	
	@Override
	public Boolean deleteAll() {
		list.clear();
		return true;
		
	}

	@Override
	public Long count() {
	
		return new Long(list.size());
	}

	@Override
	public CustomerDetails findOne(Long customerId) {

		return list.get(customerId);
	}

	

}
