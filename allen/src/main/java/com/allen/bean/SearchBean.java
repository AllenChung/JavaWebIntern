package com.allen.bean;

import java.util.Map;

// bean for search
public class SearchBean {

	// partItem name
	private String name;
	
	// attribute and value;
	private Map<String, String> attributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
}
