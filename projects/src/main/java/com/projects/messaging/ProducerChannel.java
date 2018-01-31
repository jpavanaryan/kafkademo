package com.projects.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel
{

	String CHANNEL = "messageChannel";
	
	@Output(ProducerChannel.CHANNEL)
	MessageChannel messageChannel();
}
