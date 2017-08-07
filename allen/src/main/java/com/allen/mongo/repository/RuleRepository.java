package com.allen.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.allen.mongo.model.Rule;

public interface RuleRepository extends MongoRepository<Rule, Integer>, RuleOperations {

	// get description by category
	@Query("{'isFixed': true, 'category': ?0}")
	List<Rule> findDescriptionByCategory(String Category);

	// get description by id
	Rule findDescriptionById(String Category);

	// get rule by category
	@Query("{'isFixed': false, 'category': ?0}")
	Rule findRuleByCategory(String category);

	// get rule by category
	@Query(value = "{'isFixed': true, 'category': ?0, 'description.name': ?1}", fields = "{'description':1, 'partItem':1}")
	Rule findDescriptionByCategoryAndName(String category, String name);
}
