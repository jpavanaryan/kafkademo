package com.mycompany.myapp.messaging;

import com.codahale.metrics.annotation.Timed;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProducerResource{

    private MessageChannel channel;

    public ProducerResource(ProducerChannel channel) 
   {
        this.channel = channel.messageChannel();
        System.out.println("Inside ProducerResource constructor");
    }

    @GetMapping("/greetings/{count}")
    @Timed
    public void produce(@PathVariable int count) 
	{
	System.out.println("Inside produce method, greetings count received: "+count);
        while(count > 0) 
	{
		System.out.println("Inside produce while method");
            channel.send(MessageBuilder.withPayload(new Greeting().setMessage("Hello world!: " + count)).build());
            count--;
        }
    }

}
