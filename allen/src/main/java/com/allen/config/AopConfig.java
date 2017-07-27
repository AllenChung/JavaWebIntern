package com.allen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.allen.aop.MyLog;

@Configuration
@EnableAspectJAutoProxy
@Component
public class AopConfig {

	@Bean
	public MyLog getMyLog() {
		return new MyLog();
	}
}
