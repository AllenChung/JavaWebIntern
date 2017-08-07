package com.allen.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.allen.mongo.model.Category;

public interface CategoryRepository extends MongoRepository<Category, Integer> {

	Category findCategoryByName(String name);
	
	@Query(value = "{'name':?0 }", fields = "{'partItems' : 1}")
	List<Category> findPartItemsByCategory(String category);
}
