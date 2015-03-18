package com.projectx.rest.repositoryfixture.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.async.RetriggerDetails;
import com.projectx.rest.domain.quickregister.QuickRegisterEntity;
import com.projectx.rest.repository.async.RetriggerDetailsRepository;

@Component
@Profile(value="Test")
public class RetriggerDetailsMemRepository implements
		RetriggerDetailsRepository {

	Map<Long,RetriggerDetails> list=new HashMap<Long,RetriggerDetails>();
	
	@Override
	public RetriggerDetails save(RetriggerDetails retriggerDetails) {
		
		list.put(retriggerDetails.getRetriggerId(), retriggerDetails);
		
		return retriggerDetails;
	}

	@Override
	public List<RetriggerDetails> findAll() {

		List<RetriggerDetails> retriggerList=new ArrayList<RetriggerDetails>();
		
		
		for(Long key:list.keySet())
		{
			retriggerList.add(list.get(key));
		}
		
		return retriggerList;
		
		
		
	}

	@Override
	public Boolean deleteById(Long retriggerId) {

		list.remove(retriggerId);
		
		return true;
	}

	@Override
	public void clearTestData() {
		list.clear();

	}

}
