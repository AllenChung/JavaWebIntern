package com.allen.aop;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Future;

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
import com.allen.config.MyKafkaProducer;
import com.allen.multiThread.AsyncTask;
import com.allen.util.ResultBeanFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
public class MyLog {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AsyncTask asyncTask;
	
	@Autowired
	private ObjectMapper mapper;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.allen.controller.*)")
	public void perform() {
	}

	@Around("perform()")
	public Object myLog(ProceedingJoinPoint jp) throws Exception {
		String uuid = null;
		if (request.getAttribute("traceId") == null) {  //test whether the traceId exists
			uuid = UUID.randomUUID().toString().replace("-", "");
			request.setAttribute("traceId", uuid);
		} else {
			uuid = request.getAttribute("traceId").toString();
		}
		
		//get the log asynchronously
		Future<LogBean> logBeanFuture = asyncTask.getLog(log, jp, uuid, request.getMethod(), request.getRequestURI(), request.getParameterMap().entrySet());
		Object obj = null;
		Instant after;
		Instant before = Instant.now();
		try {
			obj = jp.proceed();
			after = Instant.now();
			long time = Duration.between(before, after).toMillis();
			
			LogBean logBean = logBeanFuture.get();
			logBean.setElapsed(time);
			logBean.setResult(obj);
			
			String thisLog = mapper.writeValueAsString(logBean);
//			asyncTask.sendLogToKafka(MyKafkaProducer.KafkaTopic, thisLog); // send the log to Kafka asynchronously
			
			if (time > 500) {
				log.warn(thisLog);
			} else {
				log.info(thisLog);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			after = Instant.now();
			long time = Duration.between(before, after).toMillis();
			
			ResultBean result = ResultBeanFactory.getResult(0, e.getMessage(), null);
			LogBean logBean = logBeanFuture.get();
			logBean.setElapsed(time);
			logBean.setResult(result);
			
			String thisLog = mapper.writeValueAsString(logBean);
//			asyncTask.sendLogToKafka(MyKafkaProducer.KafkaTopic, thisLog); // send the log to Kafka asynchronously
			
			log.error(thisLog);
			return result;
		}
		return obj;
	}
}
