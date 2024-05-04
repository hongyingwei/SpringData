package com.woniuxy.j2e.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class Application {

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate(){
		StringRedisTemplate redisTemplate=new StringRedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		return redisTemplate;
	}
	
}
