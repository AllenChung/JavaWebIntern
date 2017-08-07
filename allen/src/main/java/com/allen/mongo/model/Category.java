package com.allen.mongo.model;



import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Category")
public class Category {

	@Id
	private String id;
	
	@Indexed(unique=true)
	private String name;
	
	private Set<String> attributes = new HashSet<String>();
	
	private Set<String> partItems = new HashSet<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<String> attributes) {
		this.attributes = attributes;
	}

	public Set<String> getPartItems() {
		return partItems;
	}

	public void setPartItems(Set<String> partItems) {
		this.partItems = partItems;
	}
}
