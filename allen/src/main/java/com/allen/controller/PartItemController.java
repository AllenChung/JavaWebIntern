package com.allen.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allen.bean.ResultBean;
import com.allen.dto.PostDescription;
import com.allen.dto.PostRule;
import com.allen.mongo.model.Rule;
import com.allen.service.CategoryService;
import com.allen.service.RuleService;
import com.allen.util.ResultBeanFactory;

@RestController
public class PartItemController {

	@Autowired
	private CategoryService cateService;

	@Autowired
	private RuleService ruleService;

	@GetMapping("/categoryName")
	public ResultBean getCategoryName() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNameList", cateService.getCategoryName());
		return ResultBeanFactory.getResult(1, null, map);
	}

	@GetMapping("/categoryAttributes/{category}")
	public ResultBean getCategoryAttributes(@PathVariable String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> returnMap = cateService.getCategoryAttributes(category);
		if (returnMap != null) {
			map.put("id", returnMap.remove("id"));
		}
		map.put("categoryAttributeList", returnMap);
		return ResultBeanFactory.getResult(1, null, map);
	}
	
	@PostMapping("/rule")
	public ResultBean postRule(@RequestBody PostRule postRule) {
		ruleService.saveOrUpdate(new Rule(postRule));
		return ResultBeanFactory.getResult(1, null, null);
	}

	@GetMapping("/description/{category}")
	public ResultBean getRule(@PathVariable String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("description", ruleService.getRule(category));
		return ResultBeanFactory.getResult(1, null, map);
	}

	@GetMapping("/description/{category}/{name}")
	public ResultBean getDescription(@PathVariable String category, @PathVariable String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("description", ruleService.getDescription(category, name));
		return ResultBeanFactory.getResult(1, null, map);
	}

	@GetMapping("/descriptionName/{category}")
	public ResultBean getDescriptionName(@PathVariable String category) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("descriptionNameList", ruleService.getDescriptionNames(category));
		return ResultBeanFactory.getResult(1, null, map);
	}

	@PostMapping("/description")
	public ResultBean postDescription(@RequestBody PostDescription postDescription) {
		ruleService.saveOrUpdateDescription(new Rule(postDescription));
		return ResultBeanFactory.getResult(1, null, null);
	}

	@PostMapping("/search")
	public ResultBean postSearch(@RequestBody PostDescription postDescription) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchResultList", ruleService.search(postDescription));
		return ResultBeanFactory.getResult(1, null, map);
	}

	// @GetMapping("/initmy")
	// public void initmy() {
	// Category cate = new Category();
	// cate.setName("电路板");
	// Set<String> atts = new HashSet<String>();
	// atts.add("长度");
	// atts.add("厚度");
	// atts.add("颜色");
	// cate.setAttributes(atts);
	// Set<String> partItem = new HashSet<String>();
	// partItem.add("物料a");
	// partItem.add("物料b");
	// partItem.add("物料c");
	// partItem.add("物料d");
	// partItem.add("物料e");
	// partItem.add("物料f");
	// cate.setPartItems(partItem);
	// cateRespo.save(cate);

	// Category cate = new Category();
	// cate.setName("电阻器");
	// Set<String> atts = new HashSet<String>();
	// atts.add("油印");
	// atts.add("尺寸");
	// cate.setAttributes(atts);
	// Set<String> partItem = new HashSet<String>();
	// partItem.add("物料g");
	// partItem.add("物料h");
	// partItem.add("物料i");
	// partItem.add("物料j");
	// partItem.add("物料k");
	// partItem.add("物料l");
	// cate.setPartItems(partItem);
	// cateRespo.save(cate);
	// }
}
