package com.allen.dto;

import java.util.List;

public class GetRule {

	private List<String> attribute;
	
	private List<String> partItem;

	public List<String> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<String> attribute) {
		this.attribute = attribute;
	}

	public List<String> getPartItem() {
		return partItem;
	}

	public void setPartItem(List<String> partItem) {
		this.partItem = partItem;
	}
}
