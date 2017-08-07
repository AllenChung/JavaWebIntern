package com.allen.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.allen.mongo.model.Rule;

public class RuleRepositoryImpl implements RuleOperations {
	
	@Autowired
	private MongoOperations mongo;

	// check whether description exists by category
	@Override
	public boolean existsDescriptionByCategory(String category) {
		Criteria criteria = Criteria.where("isFixed").is(true).andOperator(Criteria.where("category").is(category));
		Query query = Query.query(criteria);
		return mongo.exists(query, Rule.class);
	}

	// check whether rule exists by category
	@Override
	public boolean existsRuleByCategory(String category) {
		Criteria criteria = Criteria.where("isFixed").is(false).andOperator(Criteria.where("category").is(category));
		Query query = Query.query(criteria);
		return mongo.exists(query, Rule.class);
	}

}
