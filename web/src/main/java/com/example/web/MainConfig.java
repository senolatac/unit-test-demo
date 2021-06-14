package com.example.web;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sa
 * @date 14.06.2021
 * @time 11:46
 */
@Configuration
@ComponentScan("com.example")
public class MainConfig
{
    @Value("${messaging.consumer.initial-size}")
    private int CONSUMER_SIZE;

    @Value("${messaging.consumer.result.auto-start}")
    private boolean CONSUMER_RESULT_AUTO_START;

    @Value("${messaging.consumer.result.max-size}")
    private int CONSUMER_RESULT_MAX_SIZE;

    @Value("${messaging.consumer.interval}")
    private Long INTERVAL_IN_MS;

    @Value("${messaging.queue.post-request}")
    private String MESSAGING_POST_REQUEST_QUEUE;

    @Bean
    public AmqpTemplate postRequestQueueTemplate(ConnectionFactory rabbitConnectionFactory,
                                                 MessageConverter messageConverter)
    {
        RabbitTemplate template = new RabbitTemplate(rabbitConnectionFactory);
        template.setRoutingKey(MESSAGING_POST_REQUEST_QUEUE);
        template.setMessageConverter(messageConverter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory postResultQueueListener(ConnectionFactory connectionFactory,
                                                                        MessageConverter messageConverter)
    {
        return createContainer(connectionFactory, messageConverter, CONSUMER_RESULT_MAX_SIZE, CONSUMER_SIZE,
                INTERVAL_IN_MS, CONSUMER_RESULT_AUTO_START);
    }

    private SimpleRabbitListenerContainerFactory createContainer(ConnectionFactory connectionFactory,
                                                                 MessageConverter messageConverter,
                                                                 int maxSize, int consumerSize,
                                                                 Long minIntervalInMs, boolean autoStart)

    {
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(messageConverter);
        container.setConcurrentConsumers(consumerSize);
        container.setStartConsumerMinInterval(minIntervalInMs);
        container.setStopConsumerMinInterval(minIntervalInMs);
        container.setPrefetchCount(10);
        container.setMaxConcurrentConsumers(maxSize);
        container.setAutoStartup(autoStart);
        return container;
    }
}
