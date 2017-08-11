package com.allen.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allen.model.Essay;
import com.allen.service.EssayService;

/**
 * 
 * @author cvter
 *controller for the front end 
 */
@Controller
public class HelloWorld {
	
	@Autowired
	private EssayService essService;
	
	@Autowired
	private HttpSession session;
	
	//controller for return the index page
	@GetMapping(value="/")
	public String hello() {
		return "index";
	}
	
	//controller for return the showEssay page
	@GetMapping(value="/showEssay/{id}")
	public String showEssay(@PathVariable String id) {
		session.setAttribute("id", id);
		return "showEssay";
	}	
	
	//controller for create or update essay
	@PostMapping(value="/postEssay")
	public String postEssay(@RequestBody Essay essay) throws ParseException {
		essService.saveOrUpdate(essay);
		return "redirect:/";
	}
	
	//delete essay by id
	@GetMapping(value="/deleteEssay/{id}")
	public String deleteEssay(@PathVariable String id) {
		essService.deleteEssay(id);
		return "redirect:/";
	}
	
	//controller for return the createEssay page
	@GetMapping(value="/createEssay")
	public String createEssay() {
		return "createEssay";
	}
	
	//controller for essay list
	@GetMapping("/essayList")
	public @ResponseBody List<Essay> getEssayList() {
		return essService.getEssayList();
	}
	
	//get essay by id
	@GetMapping("/essaySingle/{id}")
	public @ResponseBody Essay getEssay(@PathVariable String id) {
		return essService.getEssay(id);
	}
	
	@GetMapping("/partItem") 
	public String getSearchIndex() {
		return "searchIndex";
	}
	
	@GetMapping("/getRule") 
	public String getRule() {
		return "rule";
	}
	
	@GetMapping("/getDescriptionAdd") 
	public String getDescriptionAdd() {
		return "descriptionAdd";
	}
	
	@GetMapping("/getDescriptionModify") 
	public String getDescriptionModify() {
		return "descriptionModify";
	}
	
	@GetMapping("/getSearch") 
	public String getSearch() {
		return "search";
	}
}
