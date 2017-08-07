package com.allen.mongo.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.allen.dto.PostDescription;
import com.allen.dto.PostRule;

@Document(collection="Rule")
public class Rule {

	@Id
	private String id;
	
	private String category;
	
	private Description description;
	
	private Set<String> partItem = new HashSet<String>();

	private String name;
	
	private boolean isFixed;
	
	public Rule() {}
	
	public Rule(PostRule postRule) {
		this.category = postRule.getCategory();
		this.isFixed = false;
		Set<MyAttribute> atts = new HashSet<MyAttribute>();
		for (String s: postRule.getAttributes()) {
			atts.add(new MyAttribute(s, null));
		}
		this.description = new Description(null, atts);
		this.id = postRule.getId();
	}
	
	public Rule(PostDescription postDescription) {
		this.id = postDescription.getId();
		this.category = postDescription.getCategory();
		this.isFixed = true;
		this.partItem = new HashSet<String>(postDescription.getPartItem());
		this.description = new Description(postDescription.getDescriptionName(), new HashSet<MyAttribute>(postDescription.getAttribute()));
	}

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

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public Set<String> getPartItem() {
		return partItem;
	}

	public void setPartItem(Set<String> partItem) {
		this.partItem = partItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}
}
