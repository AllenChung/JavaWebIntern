package com.allen.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.allen.config.ModifyFile;
import com.allen.myThreadPool.MyThreadPool;

public class MyKafka {

	private KafkaConsumer<String, String> consumer;

	//initial kafka
	public void init() {
		Properties props = new Properties();

		props.put("bootstrap.servers", "localhost:9092");
		// consumer's group id
		props.put("group.id", "GroupA");

		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");

		// poll timeout
		props.put("session.timeout.ms", "30000");
		// poll count limit
		// props.put("max.poll.records", "100");

		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer<String, String>(props);

		// subscribe topic list
		consumer.subscribe(Arrays.asList("CRM"));
	}

	//poll the data from kafka and process it
	public void execute() {
		Thread t = new Thread(new Runnable() { //start a new thread to listen kafka
			public void run() {
				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(1000); // poll the message every 1000 milliseconds
					ConsumerRecord<String, String> lastRecord = null;
					for (final ConsumerRecord<String, String> record : records) {
						lastRecord = record;
					}
					if (lastRecord != null) {
						MyThreadPool.myPool.submit(new ModifyFile(lastRecord.key(), lastRecord.value()));
					}
				}
			}
		});
		t.start();
	}

	public void shutdown() {
		if (consumer != null) {
			consumer.close();
		}
	}
}
