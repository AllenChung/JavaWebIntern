package com.allen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allen.bean.ResultBean;
import com.allen.model.Essay;
import com.allen.service.EssayService;
import com.allen.util.ResultBeanFactory;

/**
 * 
 * @author cvter
 *controller for restful
 */
@RestController
public class EssayController {

	@Autowired
	private EssayService essService;
	
	@GetMapping("/essay")
	public ResultBean getEssayList() {
		List<Essay> essay = essService.getEssayList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("essayList", essay);
		return ResultBeanFactory.getResult(1, null, map);
	}
	
	@GetMapping("/essay/{id}")
	public ResultBean getEssay(@PathVariable String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("essay", essService.getEssay(id));
		return ResultBeanFactory.getResult(1, null, map);
	}
	
	@PutMapping("/essay")
	public ResultBean putEssay(@RequestBody Essay essay) throws Exception {
		essService.putOrPostEssay(essay);
		return ResultBeanFactory.getResult(1, null, null);
	}
	
	@PostMapping("/essay")
	public ResultBean postEssay(@RequestBody Essay essay) throws Exception {
		essService.putOrPostEssay(essay);
		return ResultBeanFactory.getResult(1, null, null);
	}
	
	@DeleteMapping("/essay/{id}")
	public ResultBean deleteEssay(@PathVariable String id) {
		essService.deleteEssay(id);
		return ResultBeanFactory.getResult(1, null, null);
	}
}
