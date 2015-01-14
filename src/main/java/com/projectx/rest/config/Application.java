package com.projectx.rest.config;



import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.projectx.rest.handlers.quickregister.Receiver;



@EnableAutoConfiguration
//@EnableAsync
//@EnableScheduling
@ComponentScan(basePackages="com.projectx")
@EnableTransactionManagement
//@ImportResource(value="/schedule-config.xml")

//@Configuration(value="/schedule-config.xml")
public class Application {

    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() throws IOException
    {
       	RestTemplate restTemplate=new RestTemplate();
    
    	return restTemplate;
    }
    
    @Bean
    public JavaMailSenderImpl mailSender()
    {
    	JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("transportdeal@gmail.com");
    	mailSender.setPassword("projectx");
    	
    	
    	Properties prop=new Properties();
    	prop.put("mail.smtp.auth", true);
    	prop.put("mail.smtp.starttls.enable", true);
    	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    	mailSender.setJavaMailProperties(prop);
    	
    	return mailSender;
    	
    }
    
    @Bean
    public SecureRandom secureRandom()
    {
    	return new SecureRandom();
    }
    
    /*
    @Bean PlatformTransactionManager  transactionManager()
    {
    	JtaTransactionManager transactionManager=new JtaTransactionManager(new UserTransaction() {
			
			@Override
			public void setTransactionTimeout(int seconds) throws SystemException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setRollbackOnly() throws IllegalStateException, SystemException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void rollback() throws IllegalStateException, SecurityException,
					SystemException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getStatus() throws SystemException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void commit() throws RollbackException, HeuristicMixedException,
					HeuristicRollbackException, SecurityException,
					IllegalStateException, SystemException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void begin() throws NotSupportedException, SystemException {
				// TODO Auto-generated method stub
				
			}
		});
    	
    	
    	
    	return transactionManager; 
    }
    */
    
    /*------------AMQP
     * 
     * 
     
	@Bean
	public RabbitTemplate rabitTemplate(ConnectionFactory connectionFactory)
	{
		RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		
		rabbitTemplate.setMessageConverter(new SimpleMessageConverter());
				
		return rabbitTemplate;
	}
	
	@Bean
	public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory)
	{
		return new RabbitAdmin(connectionFactory);
	}
	
	@Bean
	Queue queue() {
		return new Queue("spring-boot", false);
		
		}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	CachingConnectionFactory connectionFactory()
	{
		CachingConnectionFactory connectionFactory= new CachingConnectionFactory("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		
		return connectionFactory;
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("spring-boot");
		
	}

	
    @Bean
    Receiver receiver() {
        return new Receiver();
    }

    
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames("spring-boot");
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	*/
	
	
	/*
	 */
	 
    
    
/*    
    @Bean
    public Gson gson()
    {
    	GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        
        return gson;
    }
 */   
}
