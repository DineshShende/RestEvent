package com.projectx.rest.handlers.ivr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectx.rest.domain.ivr.PreBookEntity;
import com.projectx.rest.repository.ivr.PreBookEntityRepository;
import com.projectx.rest.service.ivr.PreBookService;

@Component
public class PreBookEntityHandler implements PreBookService {

	@Autowired
	PreBookEntityRepository preBookEntityRepository;
	
	@Override
	public PreBookEntity save(PreBookEntity preBookEntity) {
		
		return preBookEntityRepository.save(preBookEntity);
	}

	@Override
	public PreBookEntity getByFreightRequestByCustomerId(
			Long freightRequestByCustomerId) {
		
		return preBookEntityRepository.getByFreightRequestByCustomerId(freightRequestByCustomerId);
	}

	@Override
	public Integer deleteByFreightRequestByCustomerId(
			Long freightRequestByCustomerId) {

		return preBookEntityRepository.deleteByFreightRequestByCustomerId(freightRequestByCustomerId);
	}

	@Override
	public Integer count() {

		return preBookEntityRepository.count();
	}

	@Override
	public Boolean clearTestData() {

		return preBookEntityRepository.clearTestData();
	}

}
