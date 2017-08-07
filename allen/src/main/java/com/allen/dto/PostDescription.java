package com.allen.dto;

import java.util.List;

import com.allen.mongo.model.MyAttribute;

public class PostDescription {

	private String id;
	
	private String category;
	
	private List<String> partItem;
	
	private List<MyAttribute> attribute;
	
	private String descriptionName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getPartItem() {
		return partItem;
	}

	public void setPartItem(List<String> partItem) {
		this.partItem = partItem;
	}

	public List<MyAttribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<MyAttribute> attribute) {
		this.attribute = attribute;
	}

	public String getDescriptionName() {
		return descriptionName;
	}

	public void setDescriptionName(String descriptionName) {
		this.descriptionName = descriptionName;
	}
}
