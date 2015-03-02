package com.projectx.rest.handlers.quickregister;

import java.util.Date;
import java.util.concurrent.CountDownLatch;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component("receiver")
public class Receiver  {

	private CountDownLatch latch = new CountDownLatch(1);

	/*
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	TaskExecutor executor;
	
	
	
	//@Scheduled(fixedDelay=2000)
	
	public void receiveMessage(D message) {
		System.out.println("Received <" + message + ">");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(message.i==1)
			rabbitTemplate.convertAndSend(message);
		
		latch.countDown();
	}
*/
	//-------------------------------------------------------------------
	
	public void sayHi()
	{
		System.out.println("Hi"+new Date());
	}
	
	//--------------------------------------------------------------------
/*
	public CountDownLatch getLatch() {
		return latch;
	}

	  @Scheduled(fixedRate=200)  
	    public void abc()
	    {
	    	D receivedDate=(D)rabbitTemplate.receiveAndConvert("spring-boot");
	    	if(receivedDate!=null)
	    	{
	    		System.out.println("Received date schedule:"+receivedDate);
	    	
	    		receivedDate.count+=1;
	    		if(receivedDate.i%receivedDate.count==0)
	    			rabbitTemplate.convertAndSend("spring-boot",receivedDate);
	    	}	
	    	//System.out.println("Received Object="+rabbitTemplate.receiveAndConvert(queueName).);
	    }

	*/

}
