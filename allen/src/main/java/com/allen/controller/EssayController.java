package com.allen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allen.bean.EssayBean;
import com.allen.bean.ResultBean;
import com.allen.service.EssayService;

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
		return essService.restGetEssayList();
	}
	
	@GetMapping("/essay/{id}")
	public ResultBean getEssay(@PathVariable String id) {
		return essService.restGetEssay(id);
	}
	
	@PutMapping("/essay")
	public ResultBean putEssay(@RequestBody EssayBean essayBean) throws Exception {
		return essService.restPutOrPostEssay(essayBean);
	}
	
	@PostMapping("/essay")
	public ResultBean postEssay(@RequestBody EssayBean essayBean) throws Exception {
		return essService.restPutOrPostEssay(essayBean);
	}
	
	@DeleteMapping("/essay/{id}")
	public ResultBean deleteEssay(@PathVariable String id) {
		return essService.restDeleteEssay(id);
	}
}
