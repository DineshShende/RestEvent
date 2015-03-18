package com.projectx.rest.domain.ivr;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TrackKookooResponseDTO {

	private ConcurrentMap<String , KooKooRequestEntity> requestStatus;

	
	public TrackKookooResponseDTO() {
		
		this.requestStatus=new ConcurrentHashMap<String , KooKooRequestEntity>();
	}

	public TrackKookooResponseDTO(
			ConcurrentMap<Long, KooKooRequestEntity> responseStatus) {

		this.requestStatus = new ConcurrentHashMap<String , KooKooRequestEntity>();
	}


	public void add(String sid,KooKooRequestEntity kooKooRequestEntity )
	{
		requestStatus.put(sid, kooKooRequestEntity);
	}

	public KooKooRequestEntity get(String sid)
	{
		KooKooRequestEntity fetchedEntity=requestStatus.get(sid);
		
		return fetchedEntity;
	}
	
	public ConcurrentMap<String, KooKooRequestEntity> getRequestStatus() {
		return requestStatus;
	}

	public void delete(String sid)
	{
		requestStatus.remove(sid);
	}

	@Override
	public String toString() {
		return "TrackKookooResponseDTO [responseStatus=" + requestStatus + "]";
	}
	
	
	
	
}
