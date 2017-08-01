package com.allen.multiThread;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.allen.bean.LogBean;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AsyncTask {
	
	//template for send message to Kafka
	@Autowired
	private KafkaTemplate<String, String> kt;
	
	@Autowired
	private ObjectMapper mapper;
	
	//construct the LogBean
	@Async
	public Future<LogBean> getLog(Logger log, ProceedingJoinPoint jp, String uuid, String requestMethod, String uri, Set<Entry<String, String[]>> entries ) throws Exception{
		LogBean logBean = new LogBean();
		logBean.setTraceId(uuid);
		logBean.setRequestMethod(requestMethod);
		logBean.setUrl(uri);

		Map<String, Object> map = new HashMap<>();
		for (Map.Entry<String, String[]> e : entries) { //get the params in the URL
			for (int i = 0; i < e.getValue().length; i++) {
				map.put(e.getKey(), e.getValue());
			}
		}
		List<String> strs = new LinkedList<>();  //store the params in the HTTP body
		for (final Object argument : jp.getArgs()) {  //get the params in the HTTP body
			String str = mapper.writeValueAsString(argument);
			if (str.startsWith("{")) { //params start with { means it's  a json and it had'n been contained above
				strs.add(str);
			}
		}
		if (strs.size() > 0) {
			map.put("responseBody", strs);
		}
		logBean.setParams(map);

		logBean.setMethodName(jp.getSignature().getName());
		return new AsyncResult<>(logBean);
	}
	
	@Async
	public void sendToKafka(String topic, String log) throws InterruptedException{
		kt.send(topic, log);
	}
}
