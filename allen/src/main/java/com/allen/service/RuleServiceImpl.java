package com.allen.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.bean.SearchBean;
import com.allen.dto.GetDescription;
import com.allen.dto.GetRule;
import com.allen.dto.PostDescription;
import com.allen.dto.SearchPartItem;
import com.allen.mongo.model.Description;
import com.allen.mongo.model.MyAttribute;
import com.allen.mongo.model.Rule;
import com.allen.mongo.repository.CategoryRepository;
import com.allen.mongo.repository.RuleRepository;
import com.allen.util.Algorithm;

@Service("RuleService")
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleRepository ruleRepos;

	@Autowired
	private CategoryRepository cateRepos;

	@Autowired
	private Algorithm algorithm;

	@Override
	public boolean checkDescriptionExists(String category) {
		return ruleRepos.existsDescriptionByCategory(category);
	}

	@Override
	public void saveOrUpdate(Rule rule) {
		ruleRepos.save(rule);
	}

	@Override
	public List<String> getDescriptionNames(String category) {
		List<Rule> rules = ruleRepos.findDescriptionByCategory(category);
		List<String> names = new LinkedList<String>();
		if (rules == null) {
			return null;
		}
		for (Rule r : rules) {
			Description des = r.getDescription();
			if (des != null) {
				if (des.getName() != null) {
					names.add(des.getName());
				}
			}
		}
		return names;
	}

	@Override
	public GetRule getRule(String category) {
		Rule rule = ruleRepos.findRuleByCategory(category);
		if (rule == null) {
			return null;
		}
		GetRule getDescription = new GetRule();
		
		// construct the attributes
		List<String> attributes = new LinkedList<String>();
		Description des = rule.getDescription();
		if (des != null) {
			for (MyAttribute att : des.getAttributes()) {
				attributes.add(att.getKey());
			}
		}
		getDescription.setAttribute(attributes);
		
		// construct the part items
		Set<String> partItemsAll = cateRepos.findPartItemsByCategory(category).get(0).getPartItems();
		Set<String> partItems = rule.getPartItem();
		if (partItemsAll != null && partItems != null) {
			partItemsAll.removeAll(partItems); // remove those connected to one description
		}
		getDescription.setPartItem(new LinkedList<String>(partItemsAll));

		return getDescription;
	}

	@Override
	public GetDescription getDescription(String category, String name) {
		Rule rule = ruleRepos.findDescriptionByCategoryAndName(category, name);
		if (rule == null) {
			return null;
		}
		GetDescription getDescription = new GetDescription();
		Description desc = rule.getDescription();
		
		// construct the attributes
		if (desc != null) {
			getDescription.setAttribute(new LinkedList<MyAttribute>(desc.getAttributes()));
		}
		
		//construct the part items
		Set<String> partItemsAll = cateRepos.findPartItemsByCategory(category).get(0).getPartItems();
		partItemsAll.removeAll(ruleRepos.findRuleByCategory(category).getPartItem());
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : partItemsAll) {
			map.put(s, 0);
		}
		for (String s : rule.getPartItem()) {
			map.put(s, 1); // check that connected originally
		}
		getDescription.setPartItem(map);
		
		getDescription.setId(rule.getId());
		return getDescription;
	}

	@Override
	public List<SearchPartItem> search(PostDescription postDescription) throws Exception {
		List<Rule> descriptions = ruleRepos.findDescriptionByCategory(postDescription.getCategory());

		// construct the query
		Map<String, String> query = new HashMap<String, String>();
		for (MyAttribute att : postDescription.getAttribute()) {
			if (att.getKey() != null && att.getValue() != null) {
				query.put(att.getKey(), att.getValue());
			}
		}

		// construct the original data
		Map<String, Map<String, String>> searchResult = new HashMap<String, Map<String, String>>();
		List<SearchBean> atts = new LinkedList<SearchBean>();
		for (Rule r : descriptions) {
			Map<String, String> map = new HashMap<String, String>();
			for (MyAttribute ma : r.getDescription().getAttributes()) {
				map.put(ma.getKey(), ma.getValue());
			}
			for (String s : r.getPartItem()) {
				SearchBean sb = new SearchBean();
				sb.setName(s);
				sb.setAttributes(map);
				atts.add(sb);
				searchResult.put(s, map);
			}
		}

		// construct the result
		List<SearchPartItem> result = new LinkedList<SearchPartItem>();
		for (String s : algorithm.process(atts, query)) {
			result.add(new SearchPartItem(s, searchResult.get(s)));
		}

		return result;
	}

	@Override
	public void saveOrUpdateDescription(Rule rule) {
		Rule onlyRule = ruleRepos.findRuleByCategory(rule.getCategory()); 
		
		// store the part items those have been connected to description in the first rule
		if (rule.getId() != null) {
			Rule originalDescription = ruleRepos.findDescriptionById(rule.getId());
			if (onlyRule != null && originalDescription != null) {
				onlyRule.getPartItem().removeAll(originalDescription.getPartItem());
				onlyRule.getPartItem().addAll(rule.getPartItem());
			}
		} else {
			onlyRule.getPartItem().addAll(rule.getPartItem());
		}
		ruleRepos.save(onlyRule);
		ruleRepos.save(rule);
	}

}
