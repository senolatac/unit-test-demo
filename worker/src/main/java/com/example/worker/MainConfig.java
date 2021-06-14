package com.example.worker;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:41
 */
@Configuration
@ComponentScan("com.example")
public class MainConfig
{
    @Value("${messaging.consumer.initial-size}")
    private int CONSUMER_SIZE;

    @Value("${messaging.consumer.request.auto-start}")
    private boolean CONSUMER_REQUEST_AUTO_START;

    @Value("${messaging.consumer.request.max-size}")
    private int CONSUMER_REQUEST_MAX_SIZE;

    @Value("${messaging.queue.post-request.problem}")
    private String MESSAGING_POST_REQUEST_PROBLEM_QUEUE;

    @Value("${messaging.queue.post-result}")
    private String MESSAGING_POST_RESULT_QUEUE;

    @Bean
    public AmqpTemplate postResultQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_POST_RESULT_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public AmqpTemplate postRequestProblemQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                        MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_POST_REQUEST_PROBLEM_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory postRequestQueueListener(ConnectionFactory connectionFactory,
                                                                         MessageConverter messageConverter)
    {
        return createContainer(connectionFactory, messageConverter, CONSUMER_REQUEST_MAX_SIZE, CONSUMER_SIZE,
                CONSUMER_REQUEST_AUTO_START);
    }

    private SimpleRabbitListenerContainerFactory createContainer(ConnectionFactory connectionFactory,
                                                                 MessageConverter messageConverter,
                                                                 int maxSize, int consumerSize, boolean autoStart)

    {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setConcurrentConsumers(consumerSize);
        container.setMaxConcurrentConsumers(maxSize);
        container.setAutoStartup(autoStart);
        container.setPrefetchCount(10);
        container.setMessageConverter(messageConverter);
        return container;
    }

    @Bean
    public RestOperations restTemplate(RestTemplateBuilder builder)
    {
        return builder.build();
    }
}
