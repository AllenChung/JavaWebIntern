package com.allen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.allen.bean.ConfigBean;

@Component
public class RedisConfig {

	@Bean
	public RedisConnectionFactory redisCF() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public RedisTemplate<String, ConfigBean> redisTemplateWx(RedisConnectionFactory cf) {
		RedisTemplate<String, ConfigBean> redis = new RedisTemplate<String, ConfigBean>();
		redis.setConnectionFactory(cf);
		return redis;
	}
}
