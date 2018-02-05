package com.gateway.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import com.gateway.messaging.ConsumerChannel;
import com.gateway.messaging.ProducerChannel;

/**
 * Configures Spring Cloud Stream support.
 *
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for more information.
 */
@EnableBinding(value ={ Source.class, ProducerChannel.class, ConsumerChannel.class })
public class MessagingConfiguration {

}
