package com.allen.bean;

//bean for cache config data
public class ConfigBean {

	private String md5;
	
	private String value;
	
	public ConfigBean(String md5, String value) {
		this.md5 = md5;
		this.value= value;
	}

	public String getMd5() {
		return md5;
	}

	public String getValue() {
		return value;
	}
}
