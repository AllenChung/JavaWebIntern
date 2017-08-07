package com.allen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories("com.allen.mongo")
public class MongoDBConfig extends AbstractMongoConfiguration {

	@Autowired
	private Environment env;
	
	@Override
	protected String getDatabaseName() {
		return env.getProperty("mongodb.name");
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}
}
