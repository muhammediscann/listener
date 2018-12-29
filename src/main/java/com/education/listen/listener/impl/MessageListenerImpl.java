package com.education.listen.listener.impl;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.education.listen.constants.RabbitmqConstants;
import com.education.listen.listener.MessageListener;
import com.education.listen.response.MessageResponse;
import com.education.listen.util.JsonMapper;

@EnableRabbit
@Component
public class MessageListenerImpl implements MessageListener{

	@Override
	@RabbitListener(queues = RabbitmqConstants.FOLLOW_QUEUE)
	public void onMessage(String message) {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(new Date());
		System.out.println("Message Received : " + JsonMapper.toJsonString(new MessageResponse(message)));
	}

}
