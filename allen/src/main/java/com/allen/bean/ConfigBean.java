package com.allen.bean;

//bean for cache the config data
public class ConfigBean {

	private String value;
	
	private String md5;
	
	public ConfigBean() {}
	
	public ConfigBean(String value, String md5) {
		this.value = value;
		this.md5 = md5;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
