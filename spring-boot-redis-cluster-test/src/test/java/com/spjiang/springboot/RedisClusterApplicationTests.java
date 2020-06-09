package com.spjiang.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Package: com.spjiang.springboot
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-07 00:09
 */
@SpringBootTest
public class RedisClusterApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test001() {
        for (int i = 0; i < 10; i++) {
            try {
                String key = "test:" + new Date().getTime();
                redisTemplate.opsForValue().set(key, new Date().getTime());
                TimeUnit.MILLISECONDS.sleep(100L);
                System.out.println(redisTemplate.opsForValue().get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test002() {
        while (true) {
            try {
                String key = "test:" + new Date().getTime();
                redisTemplate.opsForValue().set(key, new Date().getTime());
                TimeUnit.MILLISECONDS.sleep(100L);
                System.out.println(key + ":" + redisTemplate.opsForValue().get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}