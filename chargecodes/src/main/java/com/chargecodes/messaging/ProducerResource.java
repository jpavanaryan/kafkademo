package com.chargecodes.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class ProducerResource
{

	private MessageChannel channel;

	private final Logger log = LoggerFactory.getLogger(ProducerResource.class);
			
	public ProducerResource(ProducerChannel channel)
	{
		this.channel = channel.messageChannel();
	}

	@GetMapping("/greetings/{count}")
	@Timed
	public void produce(@PathVariable int count)
	{
		//count=10;
		log.info("Inside produce method ");
		while (count > 0)
		{
			//log.info("Message Number: ",count);
			System.out.println("Message Number: "+count);
			channel.send(MessageBuilder.withPayload(new Greeting().setMessage("Hello world!: " + count)).build());
			count--;
		}
	}

}