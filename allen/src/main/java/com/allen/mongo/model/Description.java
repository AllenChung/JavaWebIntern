package com.allen.mongo.model;

import java.util.HashSet;
import java.util.Set;

public class Description {

	private Set<MyAttribute> attributes = new HashSet<MyAttribute>();
	
	private String name;
	
	public Description() {}
	
	public Description(String name, Set<MyAttribute> attributes) {
		this.name = name;
		this.attributes = attributes;
	}

	public Set<MyAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<MyAttribute> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
