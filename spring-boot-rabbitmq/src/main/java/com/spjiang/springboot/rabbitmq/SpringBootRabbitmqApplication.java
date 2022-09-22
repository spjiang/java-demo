package com.spjiang.springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
@RabbitListener
public class SpringBootRabbitmqApplication {

	public static void main(String[] args) {
	}

}


