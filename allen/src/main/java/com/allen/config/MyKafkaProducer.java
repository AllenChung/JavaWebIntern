package com.allen.config;

//@Component
//public class MyKafkaProducer {
//
//	@Autowired
//	private Environment env;
//	
//	@Bean
//	public KafkaProducer<byte[], byte[]> getKP() {
//		Properties prop = new Properties();
//		prop.put("bootstrap.servers", env.getRequiredProperty("bootstrap.servers"));
//		prop.put("producer.type", env.getRequiredProperty("producer.type"));
//		prop.put("request.required.acks", env.getRequiredProperty("request.required.acks"));
//		prop.put("serializer.class", env.getRequiredProperty("serializer.class"));
//		prop.put("key.serializer", env.getRequiredProperty("key.serializer"));
//		prop.put("value.serializer", env.getRequiredProperty("value.serializer"));
//		prop.put("bak.partitioner.class", env.getRequiredProperty("bak.partitioner.class"));
//		prop.put("bak.key.serializer", env.getRequiredProperty("bak.key.serializer"));
//		prop.put("bak.value.serializer", env.getRequiredProperty("bak.value.serializer"));
//		
//		return new KafkaProducer<byte[], byte[]>(prop);
//	}
//}
