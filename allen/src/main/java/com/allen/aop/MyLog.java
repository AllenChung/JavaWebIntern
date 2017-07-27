package com.allen.aop;

import java.time.Duration;
import java.time.Instant;
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

import com.allen.bean.ResultBean;
import com.allen.util.ResultBeanFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
public class MyLog {

	@Autowired
	private HttpServletRequest request;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.allen.controller.EssayController)")
	public void perform() {
	}

	@Around("perform()")
	public Object myLog(ProceedingJoinPoint jp) throws Throwable {
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
		StringBuilder sb = new StringBuilder();
		sb.append("traceId: " + UUID.randomUUID());
		sb.append("\nelapsed: " + time);
		sb.append("\nrequestMethod: " + request.getMethod());
		sb.append("\nurl: " + request.getRequestURI());

		sb.append("\nparams: "); 
		for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) { //get the params in the URL
			sb.append("\n" + e.getKey() + ": ");
			for (int i = 0; i < e.getValue().length - 1; i++) {
				sb.append(e.getValue()[i] + ", ");
			}
			sb.append(e.getValue()[e.getValue().length - 1]);
		}
		for (final Object argument : jp.getArgs()) {  //get the params in the HTTP body
			try {
				sb.append("\n" + new ObjectMapper().writeValueAsString(argument));
			} catch (JsonProcessingException e1) {
				log.error(e1.getMessage());
			}
		}

		try {
			sb.append("\nresult: " + new ObjectMapper().writeValueAsString(obj));
		} catch (JsonProcessingException e1) {
			log.error(e1.getMessage());
		}

		sb.append("\nmethodName: " + jp.getSignature().getName());
		return sb.toString();
	}
}
