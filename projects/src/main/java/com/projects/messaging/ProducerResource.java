package com.projects.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.projects.domain.Project;

@Service
public class ProducerResource
{

	private MessageChannel channel;

	@Autowired
	public ProducerResource(ProducerChannel channel)
	{
		System.out.println("***************************************** Inside ProducerResource Constructor  *****************************************");
		this.channel = channel.messageChannel();
	}


	public void produce(Project project)
	{
		System.out.println("***************************************** Inside produce method  *****************************************");
		System.out.println("***************************************** project: "+project.getProjectName());
		Integer count=10;

		System.out.println("***************************************** Inside produce method  while loop*****************************************");
		channel.send(MessageBuilder.withPayload("Project Sending: "+project.toString()).build());

	}

}