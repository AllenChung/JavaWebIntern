package com.allen.dto;

import java.util.List;

import com.allen.mongo.model.MyAttribute;

public class PartItem {

	private String name;
	
	private List<MyAttribute> attribute;
	
	public PartItem() {}
	
	public PartItem(String name, List<MyAttribute> attribute) {
		this.name = name;
		this.attribute = attribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MyAttribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<MyAttribute> attribute) {
		this.attribute = attribute;
	}
}
