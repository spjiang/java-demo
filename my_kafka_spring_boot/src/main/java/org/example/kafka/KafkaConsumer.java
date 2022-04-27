package org.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Package: org.example.kafka
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-09-22 11:34
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "JT809-POSITION-V1")
    public void onMessage(String message) {
        System.out.println("++++++++JT809-POSITION-V1++++++++");
        System.out.println("++++++++JT809-POSITION-V1-message++++++++" + message);
        System.out.println(message);
    }
}
