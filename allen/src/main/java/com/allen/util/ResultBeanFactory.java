package com.allen.util;

import java.util.Map;

import com.allen.bean.ResultBean;

public class ResultBeanFactory {

	public static ResultBean getResult(int result, String message, Map<String, Object> data) {
		ResultBean resultBean = new ResultBean();
		resultBean.setResult(result);
		resultBean.setReason(message);
		resultBean.setData(data);
		return resultBean;
	}
}
