package com.allen.service;

import java.util.List;
import java.util.Map;

public interface CategoryService {

	public List<String> getCategoryName();
	
	public Map<String, Object> getCategoryAttributes(String name);
}
