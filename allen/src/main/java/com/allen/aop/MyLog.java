package com.allen.aop;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.allen.bean.LogBean;
import com.allen.bean.ResultBean;
import com.allen.util.ResultBeanFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
public class MyLog {

	@Autowired
	private HttpServletRequest request;
	
//	@Autowired
//	private KafkaProducer<byte[], byte[]> kp;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.allen.controller.EssayController)")
	public void perform() {
	}

	@Around("perform()")
	public Object myLog(ProceedingJoinPoint jp) {
		Object obj = null;
		Instant before = Instant.now();
		Instant after;
		try {
			obj = jp.proceed();
			after = Instant.now();
			long time = Duration.between(before, after).toMillis();
			String thisLog = (getLog(time, obj, jp));
			if (time > 500) {
				log.warn(thisLog);
			} else {
				log.info(thisLog);
			}
		} catch (Throwable e) {
			after = Instant.now();
			long time = Duration.between(before, after).toMillis();
			ResultBean result = ResultBeanFactory.getResult(0, e.getMessage(), null);
			String thisLog = getLog(time, result, jp);
			log.error(thisLog);
			return result;
		}
		return obj;
	}

	//construct the string to log
	public String getLog(long time, Object obj, ProceedingJoinPoint jp) {
		LogBean logBean = new LogBean();
		ObjectMapper mapper = new ObjectMapper();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		request.setAttribute("traceId", uuid);
		logBean.setTraceId(uuid);
		logBean.setElapsed(time);
		logBean.setRequestMethod(request.getMethod());
		logBean.setUrl(request.getRequestURI());

		Map<String, Object> map = new HashMap<>();
		for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) { //get the params in the URL
			for (int i = 0; i < e.getValue().length; i++) {
				map.put(e.getKey(), e.getValue());
			}
		}
		List<String> strs = new LinkedList<>();  //store the params in the HTTP body
		for (final Object argument : jp.getArgs()) {  //get the params in the HTTP body
			try {
				String str = mapper.writeValueAsString(argument);
				if (str.startsWith("{")) { //params start with { means it's  a json and it had'n been contained above
					strs.add(str);
				}
			} catch (JsonProcessingException e1) {
				log.error(e1.getMessage());
			}
		}
		if (strs.size() > 0) {
			map.put("responseBody", strs);
		}
		logBean.setParams(map);

		logBean.setResult(obj);

		logBean.setMethodName(jp.getSignature().getName());
		try {
			String thisLog = mapper.writeValueAsString(logBean);
//			send("Log", "log".getBytes(), thisLog.getBytes());
			return thisLog;
		} catch (JsonProcessingException e1) {
			log.error(e1.getMessage());
		}
		return null;
	}
	
	//send the log to kafka
//	public void send(String topic, byte[] key, byte[] value) {
//		//message encapsulation
//		ProducerRecord<byte[], byte[]> pr = new ProducerRecord<byte[], byte[]>(topic, key, value);
//
//		//send the data
//		kp.send(pr, new Callback() {
//
//			//call back function
//			public void onCompletion(RecordMetadata metadata, Exception exception) {
//				if (null != exception) {
//					log.info("offset:" + metadata.offset() + " " + exception.getMessage() + exception);
//				}
//			}
//		});
//
//		//close kp
//		kp.close();
//	}
}
