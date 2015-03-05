package com.projectx.rest.services.completeregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.projectx.rest.config.Constants.SPRING_PROFILE_ACTIVE;
import static com.projectx.rest.fixture.completeregister.DocumentDetailsDataFixture.*;

import com.projectx.rest.config.Application;
import com.projectx.rest.domain.completeregister.DocumentDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(SPRING_PROFILE_ACTIVE)
//@Transactional
public class DocumentDetailsServiceTest {

	@Autowired
	DocumentDetailsService documentDetailsService;
	
	@Before
	public void setUp()
	{
		documentDetailsService.clearTestData();
	}
	
	@Test
	public void environmentTest() {
		
	}
	
	@Test
	public void saveCustomerDocument()
	{
		assertEquals(0,documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveCustomerDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), savedEntity);
		
		assertEquals(1,documentDetailsService.count().intValue());
	}

	@Test
	public void getCustomerDocumentById()
	{
		assertEquals(0,documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveCustomerDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), documentDetailsService.getById(savedEntity.getKey()));
		
		assertEquals(1,documentDetailsService.count().intValue());
	}
	
	@Test
	public void UpdateDocument()
	{
		assertEquals(0,documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveCustomerDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), savedEntity);
		
		DocumentDetails updatedEntity=documentDetailsService.updateDocument(savedEntity.getKey(),
				standardDocumentDetailsWithDummyDocumentNew().getDocument(), standardDocumentDetailsWithDummyDocumentNew().getContentType(),"CUST_ONLINE");
		
		assertEquals(standardDocumentDetailsWithDummyDocumentNew(), updatedEntity);
		
		assertEquals(1,documentDetailsService.count().intValue());
	}
	
	@Test
	public void UpdateVerificationStatusAndRemark()
	{
		assertEquals(0,documentDetailsService.count().intValue());
		
		DocumentDetails savedEntity=documentDetailsService.saveCustomerDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(standardDocumentDetailsWithDummyDocument(), savedEntity);
		
		DocumentDetails updatedEntity=documentDetailsService.updateVerificationStatusAndRemark(savedEntity.getKey(),
				standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationStatus(), 
				standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark().getVerificationRemark(),"CUST_ONLINE");
		
		assertEquals(standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark(), updatedEntity);
		
		assertEquals(1,documentDetailsService.count().intValue());
	}
	
	@Test
	public void count()
	{
		
		assertEquals(0,documentDetailsService.count().intValue());
	}
	
	@Test
	public void clearTestData()
	{
		assertEquals(0,documentDetailsService.count().intValue());
		
		documentDetailsService.saveCustomerDocument(standardDocumentDetailsWithDummyDocument());
		
		assertEquals(1,documentDetailsService.count().intValue());
		
		assertTrue(documentDetailsService.clearTestData());
		
		assertEquals(0,documentDetailsService.count().intValue());
	}
}
