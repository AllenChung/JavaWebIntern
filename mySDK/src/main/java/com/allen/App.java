package com.allen;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.allen.cache.MyCache;
import com.allen.kafka.MyKafka;
import com.allen.quartz.MyJob;
import com.allen.quartz.MyQuartz;

/**
 * Hello world!
 * SDK main class
 */
public class App {

	private static Logger logger = LoggerFactory.getLogger(App.class);
	private static MyKafka mykafka = new MyKafka();
	private static MyQuartz myQuartz = new MyQuartz();

	public static void main(String[] args) throws Exception {
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
	
	public static void init() throws Exception {
		Properties prop = new Properties();
		prop.load(App.class.getClassLoader().getResourceAsStream("config.properties"));
		if (prop.getProperty(MyCache.KEY) == null) {
			MyJob job = new MyJob();
			job.execute(null);
		}
		MyCache.cacheMap.put(MyCache.KEY, prop.getProperty(MyCache.KEY));
	}

}
