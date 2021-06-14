package com.example.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author sa
 * @date 14.06.2021
 * @time 10:43
 */
@SpringBootConfiguration
public class CommonAppConfig
{
    @Value("${messaging.server.url:'abc'}")
    private String MESSAGING_SERVER_URL;

    @Bean
    public ConnectionFactory rabbitConnectionFactory()
    {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setUri(MESSAGING_SERVER_URL);
        return factory;
    }

    @Bean
    public MessageConverter messageConverter()
    {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new Jackson2JsonMessageConverter(mapper);
    }
}
