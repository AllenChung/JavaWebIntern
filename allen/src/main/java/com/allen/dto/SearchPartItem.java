package com.allen.dto;

import java.util.Map;

public class SearchPartItem {

	private String name;
	
	private Map<String, String> attribute;
	
	public SearchPartItem() {}
	
	public SearchPartItem(String name, Map<String, String> attribute) {
		this.name = name;
		this.attribute = attribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
}
