package com.projects.messaging;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProducerResource
{

	private MessageChannel channel;

	public ProducerResource(ProducerChannel channel)
	{
		this.channel = channel.messageChannel();
	}


	public void produce()
	{
		Integer count=10;
		while (count > 0)
		{
			channel.send(MessageBuilder.withPayload("Project Sending: ").build());
			count--;
		}
	}

}