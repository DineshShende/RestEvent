package com.projectx.rest.repositoryfixture.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.completeregister.DriverDetails;
import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.request.FreightRequestByCustomerRepository;

@Component
@Profile(value="Test")
public class FreightRequestByCustomerMemRepository implements
		FreightRequestByCustomerRepository {

	Map<Long,FreightRequestByCustomer> list=new HashMap<Long,FreightRequestByCustomer>();
	
	@Override
	public FreightRequestByCustomer save(
			FreightRequestByCustomer freightRequestByCustomer) {

		if(freightRequestByCustomer.getRequestId()==null)
			freightRequestByCustomer.setRequestId(123L);
		
		list.put(freightRequestByCustomer.getRequestId(), freightRequestByCustomer);
		
		return freightRequestByCustomer;
	}

	@Override
	public FreightRequestByCustomer getById(Long requestId) {
		
		return list.get(requestId);
	}

	@Override
	public Boolean deleteById(Long requestId) {

		list.remove(requestId);
		
		return true;
	}

	@Override
	public Boolean clearTestData() {

		list.clear();
		return true;
	}

	@Override
	public Integer count() {

		return list.size();
	}

	@Override
	public List<FreightRequestByCustomer> findByCustomerId(Long customerId) {
		
		List<FreightRequestByCustomer> freightRequestByCustomerList=new ArrayList<FreightRequestByCustomer>();
		
		
		for(Long key:list.keySet())
		{
			if(list.get(key).getCustomerId().equals(customerId))
				freightRequestByCustomerList.add(list.get(key));
		}
		
		return freightRequestByCustomerList;

		
		
	}

	@Override
	public List<FreightRequestByCustomer> getMatchingCustReqForVendorReq(
			FreightRequestByVendor freightRequestByVendor) {
		// TODO Auto-generated method stub
		return null;
	}

}
