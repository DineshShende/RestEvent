package com.projectx.rest.repositoryfixture.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.request.FreightRequestByCustomer;
import com.projectx.rest.domain.request.FreightRequestByVendor;
import com.projectx.rest.repository.request.FreightRequestByVendorRepository;



@Component
@Profile(value="Test")
public class FreightRequestByVendorMemRepository implements
		FreightRequestByVendorRepository {
	
	Map<Long ,FreightRequestByVendor> list=new HashMap<Long ,FreightRequestByVendor>();

	@Override
	public FreightRequestByVendor save(
			FreightRequestByVendor freightRequestByVendor) {

		if(freightRequestByVendor.getRequestId()==null)
			freightRequestByVendor.setRequestId(123L);
		
		list.put(freightRequestByVendor.getRequestId(), freightRequestByVendor);
		
		return freightRequestByVendor;
	}

	@Override
	public FreightRequestByVendor getById(Long requestId) {

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
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public List<FreightRequestByVendor> findByVendor(Long vendorId) {

		List<FreightRequestByVendor> freightRequestByVendorList=new ArrayList<FreightRequestByVendor>();
		
		
		for(Long key:list.keySet())
		{
			if(list.get(key).getVendorId().equals(vendorId))
			freightRequestByVendorList.add(list.get(key));
		}
		
		return freightRequestByVendorList;
		
		
		
	}

	@Override
	public List<FreightRequestByVendor> getMatchingVendorReqFromCustomerReq(
			FreightRequestByCustomer freightRequestByCustomer) {
		
		List<FreightRequestByVendor> returnList=new ArrayList<FreightRequestByVendor>();
		
		returnList.add(new FreightRequestByVendor("122", 124, 435, 32L, new Date(), "6:00AM", 10, 12L, "NEW", new Date(), new Date(), "updatedBy"));
		
		return returnList;
	}

}
