package com.projectx.rest.fixture.completeregister;

import java.io.File;
import java.io.FileInputStream;

import com.google.gson.Gson;
import com.projectx.rest.domain.completeregister.DocumentDetails;
import com.projectx.rest.domain.completeregister.DocumentKey;

import static com.projectx.rest.fixture.completeregister.CustomerDetailsDataFixtures.*;
import static com.projectx.rest.fixture.quickregister.QuickRegisterDataFixture.*;

public class DocumentDetailsDataFixture {

	
	public static String DOCUMENT_NAME="DrivingLicense";
	public static String DOCUMENT_CONTENT_TYPE="image/jpeg";
	public static String DOCUMENT_CONTENT_TYPE_NEW="application/pdf";
	public static String DOCUMENT_CONTENT_TYPE_DUMMY="text/test";
	
	public static Integer DOCUMENT_VERIFICATION_STATUS=1;
	public static String DOCUMENT_VERIFICATION_REMARKS="NOT VERIFIED";
	
	public static String DOCUMENT_VERIFICATION_REMARKS_FAILED="L1_VERIFICATION_FAILED";
	
	public static Integer DOCUMENT_VERIFICATION_STATUS_FAILED=2;
	
	private static Gson gson=new Gson();
	
	public static DocumentKey standardDocumentKey()
	{
		return new DocumentKey(CUST_ID, CUST_TYPE_CUSTOMER, DOCUMENT_NAME);
	}
	
	public static byte[] documentDummy()
	{
		return "abcdefghijklmn".getBytes();
	}

	public static byte[] documentDummyNew()
	{
		return "jhsdjheudhdfj".getBytes();
	}

	/*
	public static byte[] document()
	{
		File file = new File("/home/dinesh/10582917_673092789452549_6548939224392088956_o.jpg");

		
		
	    byte[] bFile = new byte[(int) file.length()];

		try {

		        FileInputStream fileInputStream = new FileInputStream(file);

		        fileInputStream.read(bFile);

		        
		        fileInputStream.close();
	        } catch (Exception e) {

		         e.printStackTrace();

	        }
		
		return bFile;
	}
	
	public static byte[] documentNew()
	{
		File file = new File("/home/dinesh/DomainModel.pdf");

		
		
	    byte[] bFile = new byte[(int) file.length()];

		try {

		        FileInputStream fileInputStream = new FileInputStream(file);

		        fileInputStream.read(bFile);

		        
		        fileInputStream.close();
	        } catch (Exception e) {

		         e.printStackTrace();

	        }
		
		return bFile;
	}
	*/
	public static DocumentDetails standardDocumentDetails()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocument()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE_DUMMY, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentNew()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE_DUMMY, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static DocumentDetails standardDocumentDetailsWithDummyDocumentWithNewVerificationStatusAndRemark()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE_DUMMY, DOCUMENT_VERIFICATION_STATUS_FAILED,
				DOCUMENT_VERIFICATION_REMARKS_FAILED, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static DocumentDetails standardDocumentDetailsWithNewDocumentContentType()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummyNew(), DOCUMENT_CONTENT_TYPE_NEW, DOCUMENT_VERIFICATION_STATUS,
				DOCUMENT_VERIFICATION_REMARKS, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static DocumentDetails standardDocumentDetailsWithNewVerificationStatusAndRemark()
	{
		return new DocumentDetails(standardDocumentKey(), documentDummy(), DOCUMENT_CONTENT_TYPE, DOCUMENT_VERIFICATION_STATUS_FAILED,
				DOCUMENT_VERIFICATION_REMARKS_FAILED, CUST_DATE, CUST_DATE, CUST_UPDATED_BY);
	}
	
	public static String standardJsonDocumentDetails(DocumentDetails details)
	{
		System.out.println(gson.toJson(details));
		
		return gson.toJson(details);
	}
	
	public static String standardJsonDocumentKey()
	{
		System.out.println(gson.toJson(standardDocumentKey()));
		
		return gson.toJson(standardDocumentKey());
	}
	
}
