package com.projectx.rest.repository.ivr;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixtures.ivr.PreBookDataFixture.standardPreBookEntity;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.ivr.PreBookEntity;
import com.projectx.rest.exception.repository.quickregister.ResourceNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class PreBookRepositoryTest {

	@Autowired
	PreBookEntityRepository preBookRepository;
	
	@Before
	public void setUp()
	{
		preBookRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
		
	}
	
	@Test
	public void saveAndGetByCustomerRequestId()
	{
		assertEquals(0, preBookRepository.count().intValue());
		
		PreBookEntity preBookEntity=preBookRepository.save(standardPreBookEntity());
		
		assertEquals(1, preBookRepository.count().intValue());
		
		assertEquals(preBookEntity, preBookRepository.getByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()));
		
	}
	
	@Test
	public void saveAndDeleteByCustomerRequestId()
	{
		assertEquals(0, preBookRepository.count().intValue());
		
		PreBookEntity preBookEntity=preBookRepository.save(standardPreBookEntity());
		
		assertEquals(1, preBookRepository.count().intValue());
		
		assertEquals(preBookEntity, preBookRepository.getByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()));
		
		assertEquals(1, preBookRepository.deleteByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId()).intValue());
		
		try{
		preBookRepository.getByFreightRequestByCustomerId(preBookEntity.getFreightRequestByCustomerId());
		assertEquals(0, 1);
		}catch(ResourceNotFoundException e)
		{
			assertEquals(1, 1);
		}
		
	}

}
