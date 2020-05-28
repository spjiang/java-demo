package com.spjiang.springboot.rabbitmq;

import com.spjiang.springboot.rabbitmq.SpringBootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = SpringBootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class SpringTests {
    @Test
    public void test(){
        String secret = "{noop}$2a$10$DTJSzhyXcVaArsAWozdmbO3uom4IzLFsmwRrUDInj/Aq7tzyAzjOm";
        String key = secret.substring(13, 29);
        System.out.println(key);
        String iv = secret.substring(29, 45);
        System.out.println(iv);
    }
}
