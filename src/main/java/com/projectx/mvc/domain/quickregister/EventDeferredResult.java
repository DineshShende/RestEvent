package com.projectx.mvc.domain.quickregister;

import org.springframework.web.context.request.async.DeferredResult;


public class EventDeferredResult extends DeferredResult<String>  {

	@Override
	public boolean hasResult() {
		// TODO Auto-generated method stub
		return super.hasResult();
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return super.getResult();
	}

	@Override
	public void onTimeout(Runnable callback) {
		// TODO Auto-generated method stub
		super.onTimeout(callback);
	}

	@Override
	public void onCompletion(Runnable callback) {
		// TODO Auto-generated method stub
		super.onCompletion(callback);
	}

	@Override
	public boolean setResult(String result) {
		// TODO Auto-generated method stub
		return super.setResult(result);
	}

	@Override
	public boolean setErrorResult(Object result) {
		// TODO Auto-generated method stub
		return super.setErrorResult(result);
	}


	
	
	
}
