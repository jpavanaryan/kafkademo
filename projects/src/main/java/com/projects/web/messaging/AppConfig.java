package com.projects.web.messaging;

import org.springframework.context.annotation.*;

@Configuration
public class AppConfig 
{
   @Bean
   public SimpleProducer simpleProducer() 
   {
      return new SimpleProducer();
   }

}