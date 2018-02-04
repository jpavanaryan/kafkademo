package com.gateway.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

//@Service
@EnableBinding(ConsumerChannel.class)
public class ConsumerService
{

	private final Logger log = LoggerFactory.getLogger(ConsumerService.class);

	@StreamListener(ConsumerChannel.CHANNEL)
	public void consume(Greeting greeting)
	{
		System.out.println("Inside consume method");
		log.info("Received message: {}.", greeting.getMessage());
	}
}
