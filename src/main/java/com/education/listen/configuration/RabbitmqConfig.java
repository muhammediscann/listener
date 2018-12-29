package com.education.listen.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.education.listen.constants.RabbitmqConstants;

@Configuration
public class RabbitmqConfig {
	
	@Bean
	Queue queue() {
		return new Queue(RabbitmqConstants.FOLLOW_QUEUE, true);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(RabbitmqConstants.FOLLOW_EXCHANGE);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(RabbitmqConstants.FOLLOW_ROUTINGKEY);
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory factory, MessageListenerAdapter adapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(factory);
		container.setQueues(replyQueue());
		container.setMessageListener(adapter);
		return container;
	}
	
	@Bean
    public Queue replyQueue() {
        return new Queue(RabbitmqConstants.FOLLOW_QUEUE);
    }
	
	@Bean
	MessageListenerAdapter queueListener(MessageListener listener) {
		return new MessageListenerAdapter(listener, "onMessage");
	}

}
