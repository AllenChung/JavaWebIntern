package com.allen.bean;

import java.util.Map;

//bean for returning result
public class ResultBean {

	//1 for success, 0 for fail
	private int result;
	
	//error message
	private String message;
	
	//returning data
	private Map<String, Object> data;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getReason() {
		return message;
	}

	public void setReason(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
