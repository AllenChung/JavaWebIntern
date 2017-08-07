package com.allen.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.mongo.model.Category;
import com.allen.mongo.model.Description;
import com.allen.mongo.model.MyAttribute;
import com.allen.mongo.model.Rule;
import com.allen.mongo.repository.CategoryRepository;
import com.allen.mongo.repository.RuleRepository;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cateRepos;

	@Autowired
	private RuleRepository ruleRepos;

	@Override
	public List<String> getCategoryName() {
		List<String> cateNames = new LinkedList<String>();
		for (Category cate : cateRepos.findAll()) {
			cateNames.add(cate.getName());
		}
		return cateNames;
	}

	@Override
	public Map<String, Object> getCategoryAttributes(String name) {
		// if the description exists, then it can not be changed
		if (ruleRepos.existsDescriptionByCategory(name)) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();

		// add all attributes
		Category cate = cateRepos.findCategoryByName(name);
		if (cate != null) {
			for (String attribute : cate.getAttributes()) {
				map.put(attribute, 0);
			}
		}

		Rule rule = ruleRepos.findRuleByCategory(name);
		// if the rule exists, record the checked attribute
		if (rule != null) {
			Description d = rule.getDescription();
			if (d != null) {
				for (MyAttribute att: d.getAttributes()) {
					map.put(att.getKey(), 1);
				}
				map.put("id", rule.getId());
			}
		}
		return map;
	}

}
