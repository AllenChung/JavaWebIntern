package com.allen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JacksonConfig {

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
