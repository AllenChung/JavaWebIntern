package com.allen.mongo.repository;

public interface RuleOperations {

	// check whether description exists by category
	boolean existsDescriptionByCategory(String Category);

	// check whether rule exists by category
	boolean existsRuleByCategory(String Category);
}
