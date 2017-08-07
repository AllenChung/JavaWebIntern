package com.allen.service;

import java.util.List;

import com.allen.dto.GetDescription;
import com.allen.dto.GetRule;
import com.allen.dto.PostDescription;
import com.allen.dto.SearchPartItem;
import com.allen.mongo.model.Rule;

public interface RuleService {

	boolean checkDescriptionExists(String category);
	
	void saveOrUpdate(Rule rule);
	
	void saveOrUpdateDescription(Rule rule);
	
	List<String> getDescriptionNames(String category);
	
	GetRule getRule(String category);
	
	GetDescription getDescription(String category, String name);
	
	List<SearchPartItem> search(PostDescription postDescription) throws Exception;
}
