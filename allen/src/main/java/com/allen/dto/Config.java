package com.allen.dto;

import com.allen.model.ConfigInfo;

public class Config {

	private String id;
	
	private String key;
	
	private String value;
	
	public Config() {}
	
	public Config(ConfigInfo configInfo) {
		this.id = configInfo.getId();
		this.key = configInfo.getKey();
		this.value = configInfo.getValue();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
