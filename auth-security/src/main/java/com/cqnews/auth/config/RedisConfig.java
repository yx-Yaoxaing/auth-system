package com.cqnews.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用 GenericFastJsonRedisSerializer 替换默认序列化

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 设置key和value的序列化规则

        redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Object.class));

        redisTemplate.setValueSerializer(stringRedisSerializer);

        // 设置hashKey和hashValue的序列化规则

        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(Object.class));

        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        return redisTemplate;
    }

}
