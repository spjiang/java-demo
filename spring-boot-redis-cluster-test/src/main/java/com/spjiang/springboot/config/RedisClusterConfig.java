package com.spjiang.springboot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.List;

/**
 * Package: com.spjiang.springboot.config
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2020-06-07 00:05
 */
@Configuration
@ConditionalOnClass({JedisCluster.class})
public class RedisClusterConfig {
    @Value("${spring.redis.timeout}")
    private Integer redisTimeout;
    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer poolMaxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer poolMaxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer poolMinIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer poolMaxWait;
    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;
    @Value("${spring.redis.cluster.max-redirects}")
    private Integer clusterMaxRedirects;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 通过反射获取JedisCluster
     *
     * @param factory
     * @return
     */
    @Bean
    public JedisCluster redisCluster(RedisConnectionFactory factory) {
        Object object = null;
        try {
            object = getFieldValueByObject(factory, "cluster");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (JedisCluster) object;
    }

    public Object getFieldValueByObject(Object object, String targetFieldName) throws Exception {
        // 获取该对象的Class
        Class objClass = object.getClass();
        // 获取所有的属性数组
        Field[] fields = objClass.getDeclaredFields();
        for (Field field : fields) {
            // 属性名称
            field.setAccessible(true);
            String currentFieldName = field.getName();
            if (currentFieldName.equals(targetFieldName)) {
                // 通过反射拿到该属性在此对象中的值(也可能是个对象)
                return field.get(object);
            }
        }
        return null;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(poolMaxActive);
        poolConfig.setMaxIdle(poolMaxIdle);
        poolConfig.setMinIdle(poolMinIdle);
        poolConfig.setMaxWaitMillis(poolMaxWait);
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .usePooling().poolConfig(poolConfig).and().readTimeout(Duration.ofMillis(redisTimeout)).build();

        // cluster模式
        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
        redisConfig.setPassword(RedisPassword.of(password));
        redisConfig.setMaxRedirects(clusterMaxRedirects);
        for (String ipPort : clusterNodes) {
            String[] ipPortArr = ipPort.split(":");
            redisConfig.clusterNode(ipPortArr[0], Integer.parseInt(ipPortArr[1]));
        }
        return new JedisConnectionFactory(redisConfig, clientConfig);
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //key使用StringRedisSerializer
        StringRedisSerializer strSerializer = new StringRedisSerializer();
        template.setKeySerializer(strSerializer);
        template.setHashKeySerializer(strSerializer);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        //value使用Jackson2JsonRedisSerializer
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

}
