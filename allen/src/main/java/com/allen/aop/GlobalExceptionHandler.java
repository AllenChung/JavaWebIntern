package com.allen.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allen.bean.ResultBean;
import com.allen.util.ResultBeanFactory;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ResultBean handleException(Exception e) {
		e.printStackTrace();
		log.error(e.getMessage());
		return ResultBeanFactory.getResult(0, e.getMessage(), null);
	}
}

