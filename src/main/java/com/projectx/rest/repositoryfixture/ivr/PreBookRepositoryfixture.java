package com.projectx.rest.repositoryfixture.ivr;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.ivr.PreBookEntity;
import com.projectx.rest.repository.ivr.PreBookEntityRepository;

@Component
@Profile("Test")
public class PreBookRepositoryfixture implements PreBookEntityRepository {

	
	Map<Long,PreBookEntity> list=new HashMap<Long,PreBookEntity>();
	
	@Override
	public PreBookEntity save(PreBookEntity preBookEntity) {

		list.put(preBookEntity.getFreightRequestByCustomerId(), preBookEntity);
		
		return preBookEntity;
	}

	@Override
	public PreBookEntity getByFreightRequestByCustomerId(
			Long freightRequestByCustomerId) {

		PreBookEntity fetchedEntity=list.get(freightRequestByCustomerId);
		
		return fetchedEntity;
	}

	@Override
	public Integer deleteByFreightRequestByCustomerId(
			Long freightRequestByCustomerId) {

		list.remove(freightRequestByCustomerId);
		
		return 1;
	}

	

	@Override
	public Integer count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean clearTestData() {
		// TODO Auto-generated method stub
		return null;
	}

}
