package com.projectx.rest.config;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import com.projectx.rest.handlers.quickregister.Receiver;

@Component
@EnableScheduling
public class ScheduleConfig {//implements SchedulingConfigurer {

	
	Trigger tr=new Trigger() {
		
		@Override
		public Date nextExecutionTime(TriggerContext triggerContext) {
			// TODO Auto-generated method stub
			
			
			return null;
		}
	};
	
	
	
/*
	  @Bean()
	  public ThreadPoolTaskScheduler taskScheduler() {
	     ThreadPoolTaskScheduler threadPoolTaskScheduler=new ThreadPoolTaskScheduler();

	     threadPoolTaskScheduler.setPoolSize(5);
	     
	     threadPoolTaskScheduler.setThreadFactory(threadPoolTaskExecutor());
	     
	     
	     return threadPoolTaskScheduler;
	  }

	  @Bean 
	  public Receiver receiver()
	  {
		  return new Receiver();
	  }
	  
	  
	  @Override
	  public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
	  {
	      taskRegistrar.setTaskScheduler(taskScheduler());
	      taskRegistrar.addFixedRateTask(new Runnable()
	      {
	         public void run()
	         {
	        	 receiver().sayHi();
	         }
	      }, 1000);
	  }
	  
	  
	  @Bean 
		ThreadPoolTaskExecutor threadPoolTaskExecutor()
		{
			ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(5);
			executor.setMaxPoolSize(10);
			executor.setQueueCapacity(25);
			//executor.submit(new Receiver());
			
			
			
			return executor;
		}
		
	*/	
}
