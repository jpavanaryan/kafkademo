package com.chargecodes.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

@RestController
@EnableBinding(MessageChannel.class)
public class ProducerResource
{

	private MessageChannel channel;

	private final Logger log = LoggerFactory.getLogger(ProducerResource.class);
			
	@Autowired
	public ProducerResource(@Qualifier("customChannel") MessageChannel channel)
	{
		this.channel = channel;
	}

	@RequestMapping("/api/greetings/{count}")
	@Timed
	public void produce(@PathVariable int count) throws Exception
	{
		log.info("Inside produce method ");
		while (count > 0)
		{
			System.out.println("***************************  Message Sequence Number: "+count+"  ************************************** ");
			channel.send(MessageBuilder.withPayload(new Greeting().setMessage("Message Sequence Number: " + count)).build());
			//new SimpleProducer().produce();
			count--;
		}
		
		//new SimpleConsumer().consume();
	}

}