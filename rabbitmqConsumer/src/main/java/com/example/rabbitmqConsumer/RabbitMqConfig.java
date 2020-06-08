package com.example.rabbitmqConsumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    private static final String MY_QUEUE = "MyQueue";

    @Bean
    Queue myQueue() {
        return new Queue(MY_QUEUE, true);

    }
    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }

    @Bean
    Binding binding(){
        return BindingBuilder
                .bind(myQueue())
                .to(myExchange())
                .with("topic")
                .noargs();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("neha");
        cachingConnectionFactory.setPassword("neha");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
        return simpleMessageListenerContainer;
}


}
