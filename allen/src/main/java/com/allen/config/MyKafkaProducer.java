package com.allen.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
	
//config Kafka producer
@Configuration
@EnableKafka
public class MyKafkaProducer {
	
	public static String KafkaTopic = "allenLog";

	public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
	
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
	
	public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

}
