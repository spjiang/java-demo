package com.spjiang.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.spjiang.springboot.bean.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

import java.util.*;
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
        while (true) {
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