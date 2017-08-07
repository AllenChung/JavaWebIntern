package com.allen.dto;

import java.util.List;
import java.util.Map;

import com.allen.mongo.model.MyAttribute;


public class GetDescription {

	private List<MyAttribute> attribute;
	
	private Map<String, Integer> partItem;
	
	private String id;
	
	public GetDescription() {}

	public List<MyAttribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<MyAttribute> attribute) {
		this.attribute = attribute;
	}

	public Map<String, Integer> getPartItem() {
		return partItem;
	}

	public void setPartItem(Map<String, Integer> partItem) {
		this.partItem = partItem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
