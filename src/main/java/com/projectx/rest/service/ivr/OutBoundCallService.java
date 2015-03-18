package com.projectx.rest.service.ivr;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.projectx.rest.domain.ivr.IVRCallInfoDTO;

@Service
public interface OutBoundCallService {

	String makeOutBoundCall(IVRCallInfoDTO ivrCallInfoDTO) ;
	
}
