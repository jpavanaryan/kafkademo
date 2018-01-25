package com.projects.web.messaging;

//import util.properties packages
import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

import com.projects.domain.Project;

//Create java class named “SimpleProducer”
public class SimpleProducer
{
	public  Producer<String, String> producer;
	public String topicName;
			
	public SimpleProducer()
	{
		  //Assign topicName to string variable
	      topicName = "project";
	      
	      // create instance for properties to access producer configs   
	      Properties props = new Properties();
	      
	      //Assign localhost id
	      props.put("bootstrap.servers", "localhost:9092");
	      
	      //Set acknowledgements for producer requests.      
	      props.put("acks", "all");
	      
	      //If the request fails, the producer can automatically retry,
	      props.put("retries", 0);
	      
	      //Specify buffer size in config
	      props.put("batch.size", 16384);
	      
	      //Reduce the no of requests less than 0   
	      props.put("linger.ms", 1);
	      
	      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	      props.put("buffer.memory", 33554432);
	      
	      props.put("key.serializer","org.apache.kafka.common.serializa-tion.StringSerializer");
	         
	      props.put("value.serializer","org.apache.kafka.common.serializa-tion.StringSerializer");
	      
	      producer = new KafkaProducer<String, String>(props);
	            
	   
	}
	
	public void send(Project project)
	{

         producer.send(new ProducerRecord<String, String>(topicName,"project", "Project with ID:  created"));
         producer.close();  
	}
	
}
