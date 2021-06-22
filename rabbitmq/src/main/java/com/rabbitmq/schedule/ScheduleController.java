package com.rabbitmq.schedule;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.UUID;

/**
 * Package: com.rabbitmq.schedule
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-01-14 19:59
 */
@Configuration
@EnableScheduling
@Slf4j
public class ScheduleController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${exchange.name}")
    private String topicExchange;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void sendEmailMessage() {
        String msg = getRandomString(8);
        JSONObject email = new JSONObject();
        email.put("content", msg);
        email.put("to", "duchong@qq.com");
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(topicExchange, "demo.email.x", email.toJSONString(), correlationData);
        log.info("---发送 email 消息---{}---messageId---{}", email, correlationData.getId());
    }

    /**
     * length用户要求产生字符串的长度
     *
     * @param length
     * @return string
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
