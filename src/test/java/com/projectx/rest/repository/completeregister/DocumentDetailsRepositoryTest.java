package com.projectx.rest.repository.completeregister;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE_TEST;
import static com.projectx.rest.fixture.completeregister.DocumentDetailsDataFixture.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.exception.repository.completeregister.DocumentDetailsNotFoundException;
import com.projectx.rest.repository.completeregister.DocumentDetailsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE_TEST)
public class DocumentDetailsRepositoryTest {

	
	@Autowired
	DocumentDetailsRepository customerDocumentRepository;
	
	@Before
	public void setUp()
	{
		customerDocumentRepository.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveDocumentDetails()
	{
		assertEquals(0, customerDocumentRepository.count().intValue());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.saveCustomerDocument(standardDocumentDetails()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
	}
	
	
	
	@Test
	public void saveAndGetByKeyDocumentDetails()
	{
		assertEquals(0, customerDocumentRepository.count().intValue());

		DocumentDetails documentDetails=null;
		
		try
		{
			documentDetails=customerDocumentRepository.getByCustomerId(standardDocumentKey());
		}
		catch(DocumentDetailsNotFoundException  e)
		{
			assertNull(documentDetails);
		}
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.saveCustomerDocument(standardDocumentDetails()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.getByCustomerId(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
	}
	
	@Test
	public void updateDocumentAndContentType()
	{
		assertEquals(0, customerDocumentRepository.count().intValue());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.saveCustomerDocument(standardDocumentDetails()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.getByCustomerId(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
		
		DocumentDetails updatedDocument=customerDocumentRepository.saveCustomerDocument(standardDocumentDetailsWithNewDocumentContentType());
		
		assertEquals(standardDocumentDetailsWithNewDocumentContentType(),
				customerDocumentRepository.getByCustomerId(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
	}
	
	@Test
	public void updateVerificationStatusAndRemark()
	{
		assertEquals(0, customerDocumentRepository.count().intValue());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.saveCustomerDocument(standardDocumentDetails()));
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.getByCustomerId(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
		
		DocumentDetails updatedDocument=customerDocumentRepository.saveCustomerDocument(standardDocumentDetailsWithNewVerificationStatusAndRemark());
		
		assertEquals(standardDocumentDetailsWithNewVerificationStatusAndRemark(),
				customerDocumentRepository.getByCustomerId(standardDocumentKey()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
	}
	
	@Test
	public void deleteAll()
	{
		assertEquals(0, customerDocumentRepository.count().intValue());
		
		assertEquals(standardDocumentDetails(), customerDocumentRepository.saveCustomerDocument(standardDocumentDetails()));
		
		assertEquals(1, customerDocumentRepository.count().intValue());
		
		customerDocumentRepository.clearTestData();
		
		assertEquals(0, customerDocumentRepository.count().intValue());
	}

}
