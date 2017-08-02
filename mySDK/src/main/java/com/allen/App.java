package com.allen;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allen.cache.MyCache;
import com.allen.kafka.MyKafka;
import com.allen.quartz.MyQuartz;

/**
 * Hello world!
 * SDK main class
 */
public class App {

	private static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		MyKafka mykafka = new MyKafka();
		MyQuartz myQuartz = new MyQuartz();
		try {
			init();
			
			mykafka.init();
			mykafka.execute();
			
			myQuartz.init();
			myQuartz.execute();
			
			while (true) {
				logger.info("info");
				logger.debug("debug");
				Thread.sleep(7000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			myQuartz.shutdown();
			mykafka.shutdown();
		}
	}
	
	public static void init() throws IOException {
		Properties prop = new Properties();
		prop.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
		MyCache.cacheMap.put(MyCache.KEY, prop.getProperty(MyCache.KEY));
	}

}
