package com.projectx.rest.repositoryfixture.completeregister;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.data.domain.completeregister.UpdateAddressDTO;
import com.projectx.data.domain.completeregister.UpdateMobileVerificationStatusDTO;
import com.projectx.rest.domain.completeregister.CustomerDetails;
import com.projectx.rest.domain.quickregister.AuthenticationDetails;
import com.projectx.rest.repository.completeregister.CustomerDetailsRepository;


@Component
@Profile(value="Test")
public class CustomerDetailsMemRepository implements CustomerDetailsRepository {

	
	Map<Long,CustomerDetails> list=new HashMap<Long,CustomerDetails>();
	
	@Override
	public CustomerDetails save(CustomerDetails customerDetails) {

		CustomerDetails savedEntity=list.put(customerDetails.getCustomerId(), customerDetails);
		
		return customerDetails;
	}

	@Override
	public CustomerDetails updateFirmAddress(UpdateAddressDTO addressDTO) {

		CustomerDetails oldRecord=list.get(addressDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(addressDTO.getCustomerId());
		
			oldRecord.setFirmAddressId(addressDTO.getAddress());

			list.put(addressDTO.getCustomerId(), oldRecord);
		
			return oldRecord;
		}
		else
			return new CustomerDetails();

	}

	@Override
	public CustomerDetails updateHomeAddress(UpdateAddressDTO addressDTO) {

		CustomerDetails oldRecord=list.get(addressDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(addressDTO.getCustomerId());
		
			oldRecord.setHomeAddressId(addressDTO.getAddress());

			list.put(addressDTO.getCustomerId(), oldRecord);
		
			return oldRecord;
		}
		else
			return new CustomerDetails();


	}

	@Override
	public Integer updateMobileVerificationStatus(
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {

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
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {

		CustomerDetails oldRecord=list.get(verificationStatusDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(verificationStatusDTO.getCustomerId());
		
			oldRecord.setIsSecondaryMobileVerified(verificationStatusDTO.getStatus());

			list.put(verificationStatusDTO.getCustomerId(), oldRecord);
		
			return 1;
		}
		else
			return 0;

	}

	@Override
	public Integer updateEmailVerificationStatus(
			UpdateMobileVerificationStatusDTO verificationStatusDTO) {
		
		CustomerDetails oldRecord=list.get(verificationStatusDTO.getCustomerId());
		
		if(oldRecord!=null)
		{	
			list.remove(verificationStatusDTO.getCustomerId());
		
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
